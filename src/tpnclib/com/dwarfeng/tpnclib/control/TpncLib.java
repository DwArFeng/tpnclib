package com.dwarfeng.tpnclib.control;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;

import javax.swing.Icon;

import com.dwarfeng.dutil.basic.prog.DefaultVersion;
import com.dwarfeng.dutil.basic.prog.ProcessException;
import com.dwarfeng.dutil.basic.prog.ProgramObverser;
import com.dwarfeng.dutil.basic.prog.RuntimeState;
import com.dwarfeng.dutil.basic.prog.Version;
import com.dwarfeng.dutil.basic.prog.VersionType;
import com.dwarfeng.dutil.develop.backgr.Background;
import com.dwarfeng.dutil.develop.backgr.ExecutorServiceBackground;
import com.dwarfeng.dutil.develop.backgr.Task;
import com.dwarfeng.dutil.develop.backgr.obv.BackgroundObverser;
import com.dwarfeng.dutil.develop.cfg.ConfigUtil;
import com.dwarfeng.dutil.develop.cfg.DefaultExconfigModel;
import com.dwarfeng.dutil.develop.cfg.ExconfigModel;
import com.dwarfeng.dutil.develop.cfg.SyncExconfigModel;
import com.dwarfeng.dutil.develop.cfg.obv.ExconfigObverser;
import com.dwarfeng.dutil.develop.i18n.DelegateI18nHandler;
import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.dutil.develop.i18n.I18nUtil;
import com.dwarfeng.dutil.develop.i18n.SyncI18nHandler;
import com.dwarfeng.dutil.develop.resource.DelegateResourceHandler;
import com.dwarfeng.dutil.develop.resource.ResourceUtil;
import com.dwarfeng.dutil.develop.resource.SyncResourceHandler;
import com.dwarfeng.notewiz.control.DisposeTask;
import com.dwarfeng.notewiz.control.NoteWizard;
import com.dwarfeng.tpnclib.model.cm.DelegateLoggerHandler;
import com.dwarfeng.tpnclib.model.cm.LoggerHandler;
import com.dwarfeng.tpnclib.model.cm.SyncLoggerHandler;
import com.dwarfeng.tpnclib.model.eum.CoreConfiguration;
import com.dwarfeng.tpnclib.model.eum.DialogMessage;
import com.dwarfeng.tpnclib.model.eum.DialogOption;
import com.dwarfeng.tpnclib.model.eum.DialogOptionCombo;
import com.dwarfeng.tpnclib.model.eum.ModalConfiguration;
import com.dwarfeng.tpnclib.model.eum.TpncLibProperty;
import com.dwarfeng.tpnclib.model.struct.Toolkit;
import com.dwarfeng.tpnclib.util.ModelUtil;
import com.dwarfeng.tpnclib.view.gui.MainFrame;
import com.dwarfeng.tpnclib.view.struct.GuiManager;
import com.dwarfeng.tpnclib.view.struct.WindowSuppiler;

/**
 * TODO 详细的介绍。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class TpncLib {

	public final class TpncLibToolkit implements Toolkit {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addCoreConfigObverser(ExconfigObverser coreConfigObverser) throws IllegalStateException {
			return coreConfigModel.addObverser(coreConfigObverser);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean disposeMainFrame() throws IllegalStateException {
			synchronized (mainFrameLock) {
				if (Objects.isNull(mainFrame)) {
					return false;
				}

				mainFrame.dispose();
				mainFrame = null;
				return true;
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void exit() throws IllegalStateException, ProcessException {
			synchronized (startExitLock) {

				if (isDisposeFlag()) {
					if (getRuntimeState() == RuntimeState.RUNNING) {
						throw new IllegalStateException("程序正在运行");
					}
					if (getRuntimeState() == RuntimeState.NOT_START) {
						throw new IllegalStateException("程序还未启动");
					}
					throw new IllegalStateException("由于释放标志为 true，程序无法释放");
				}
				setDisposeFlag(true);

				// 开启释放过程
				final Task task = new DisposeTask(NoteWizard.this);
				task.run();

				// 解除自身引用
				INSTANCES.remove(this);

				// 反编织观察网络。
				coreConfigModel.removeObverser(coreConfigObverser);
				concurrentBackground.removeObverser(backgroundObverser);
				queueBackground.removeObverser(backgroundObverser);
				

				// 判断是否出现异常。
				if (Objects.nonNull(task.getThrowable())) {
					throw new ProcessException("释放过程失败", task.getThrowable());
				}

				// 设置程序的运行状态为已经结束
				setRuntimeState(RuntimeState.ENDED);

			}
		}

		@Override
		public void fatal(String message, Throwable t) throws IllegalStateException {
			// TODO Auto-generated method stub

		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SyncExconfigModel getCoreConfigModel() throws IllegalStateException {
			return coreConfigModel;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ExconfigModel getCoreConfigModelReadOnly() throws IllegalStateException {
			return ConfigUtil.unmodifiableExconfigModel(coreConfigModel);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SyncI18nHandler getLabelI18nHandler() throws IllegalStateException {
			return labelI18nHandler;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public I18nHandler getLabelI18nHandlerReadOnly() throws IllegalStateException {
			return I18nUtil.readOnlyI18nHandler(labelI18nHandler);
		}

		@Override
		public SyncLoggerHandler getLoggerHandler() throws IllegalStateException {
			return loggerHandler;
		}

		@Override
		public LoggerHandler getLoggerHandlerReadOnly() throws IllegalStateException {
			return ModelUtil.unmodifiableLoggerHandler(loggerHandler);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SyncI18nHandler getLoggerI18nHandler() throws IllegalStateException {
			return loggerI18nHandler;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public I18nHandler getLoggerI18nHandlerReadOnly() throws IllegalStateException {
			return I18nUtil.readOnlyI18nHandler(loggerI18nHandler);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public SyncExconfigModel getModalConfigModel() throws IllegalStateException {
			return modalConfigModel;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ExconfigModel getModalConfigModelReadOnly() throws IllegalStateException {
			return ConfigUtil.unmodifiableExconfigModel(modalConfigModel);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public RuntimeState getRuntimeState() throws IllegalStateException {
			synchronized (runtimeStateLock) {
				return runtimeState;
			}
		}

		@Override
		public void info(String message) throws IllegalStateException {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isMainFrameVisible() throws IllegalStateException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean newMainFrame() throws IllegalStateException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setExitCode(int exitCode) throws IllegalStateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setRuntimeState(RuntimeState runtimeState) throws IllegalStateException {
			// TODO Auto-generated method stub

		}

		@Override
		public DialogOption showConfirmDialog(Object message) throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DialogOption showConfirmDialog(Object message, String title, DialogOptionCombo dialogOptionCombo)
				throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DialogOption showConfirmDialog(Object message, String title, DialogOptionCombo dialogOptionCombo,
				DialogMessage dialogMessage) throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DialogOption showConfirmDialog(Object message, String title, DialogOptionCombo dialogOptionCombo,
				DialogMessage dialogMessage, Icon icon) throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void showExternalWindow(String key) {
			// TODO Auto-generated method stub

		}

		@Override
		public void showExternalWindow(WindowSuppiler suppiler) {
			// TODO Auto-generated method stub

		}

		@Override
		public String showInputDialog(Object message) throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String showInputDialog(Object message, Object initialSelectionValue) throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String showInputDialog(Object message, String title, DialogMessage dialogMessage)
				throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object showInputDialog(Object message, String title, DialogMessage dialogMessage, Icon icon,
				Object[] selectionValues, Object initialSelectionValue) throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void showMessageDialog(Object message) throws IllegalStateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void showMessageDialog(Object message, String title, DialogMessage dialogMessage)
				throws IllegalStateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void showMessageDialog(Object message, String title, DialogMessage dialogMessage, Icon icon)
				throws IllegalStateException {
			// TODO Auto-generated method stub

		}

		@Override
		public int showOptionDialog(Object message, String title, DialogOptionCombo dialogOptionCombo,
				DialogMessage dialogMessage, Icon icon, Object[] options, Object initialValue)
				throws IllegalStateException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void start() throws IllegalStateException, ProcessException {
			// TODO Auto-generated method stub

		}

		@Override
		public void submitTask(Task task, BackgroundType type) throws IllegalStateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void trace(String message) throws IllegalStateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void tryExit() throws IllegalStateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void warn(String message) throws IllegalStateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void warn(String message, Throwable t) throws IllegalStateException {
			// TODO Auto-generated method stub

		}

		private boolean isDisposeFlag() {
			synchronized (disposeFlagLock) {
				return disposeFlag;
			}
		}

		private boolean isStartFlag() {
			synchronized (startFlagLock) {
				return startFlag;
			}
		}

		private void setDisposeFlag(boolean disposeFlag) {
			synchronized (disposeFlagLock) {
				TpncLib.this.disposeFlag = disposeFlag;
			}
		}

		private void setStartFlag(boolean startFlag) {
			synchronized (startFlagLock) {
				TpncLib.this.startFlag = startFlag;
			}
		}

	}

	private final class TpncLibGuiManager implements GuiManager {

	}

	/** 程序的版本 */
	public final static Version VERSION = new DefaultVersion.Builder().type(VersionType.ALPHA).firstVersion((byte) 0)
			.secondVersion((byte) 0).thirdVersion((byte) 3).buildDate("20171027").buildVersion('A').build();
	private final static Map<TpncLibProperty, String> DEFAULT_PROPERTIES = new EnumMap<>(TpncLibProperty.class);

	/** 程序的实例列表，用于持有引用 */
	private static final Set<TpncLib> INSTANCES = Collections.synchronizedSet(new HashSet<>());

	static {
		DEFAULT_PROPERTIES.put(TpncLibProperty.NC_LIBRARY_PATH, "library/");
	}

	// --------------------------------------------模型--------------------------------------------
	// 并发后台
	private final Background concurrentBackground = new ExecutorServiceBackground(
			Executors.newFixedThreadPool(4, ExecutorServiceBackground.THREAD_FACTORY),
			Collections.newSetFromMap(new WeakHashMap<>()));
	private final Background queueBackground = new ExecutorServiceBackground(
			Executors.newSingleThreadExecutor(ExecutorServiceBackground.THREAD_FACTORY),
			Collections.newSetFromMap(new WeakHashMap<>()));
	// 资源处理器
	private final SyncResourceHandler resourceHandler = ResourceUtil.syncResourceHandler(new DelegateResourceHandler());
	// 核心配置模型
	private final SyncExconfigModel coreConfigModel = ConfigUtil
			.syncExconfigModel(new DefaultExconfigModel(Arrays.asList(CoreConfiguration.values())));
	// 模态配置模型
	private final SyncExconfigModel modalConfigModel = ConfigUtil
			.syncExconfigModel(new DefaultExconfigModel(Arrays.asList(ModalConfiguration.values())));
	// 标签国际化处理器
	private final SyncI18nHandler labelI18nHandler = I18nUtil.syncI18nHandler(new DelegateI18nHandler());
	// 记录器国际化处理器
	private final SyncI18nHandler loggerI18nHandler = I18nUtil.syncI18nHandler(new DelegateI18nHandler());
	// 记录器接口
	private final SyncLoggerHandler loggerHandler = ModelUtil.syncLoggerHandler(new DelegateLoggerHandler());

	// --------------------------------------------控制--------------------------------------------
	/** 程序的侦听器集合 */
	private final Set<ProgramObverser> programObversers = Collections.newSetFromMap(new WeakHashMap<>());
	/** 程序的属性集合。 */
	private final Map<String, String> properties = new HashMap<>();
	/** 启动标志 */
	private boolean startFlag = false;
	/** 启动标志同步锁 */
	private final Object startFlagLock = new Object();
	/** 释放标志 */
	private boolean disposeFlag = false;
	/** 释放标识同步锁 */
	private final Object disposeFlagLock = new Object();
	/** 程序的状态 */
	private RuntimeState runtimeState;
	/** 程序的状态的同步锁 */
	private final Object runtimeStateLock = new Object();
	/** 程序的退出代码 */
	private int exitCode = Integer.MIN_VALUE;
	/** 程序的退出代码的同步锁 */
	private final Object exitCodeLock = new Object();
	/** 程序的工具包 */
	private final Toolkit toolkit = new TpncLibToolkit();
	/** 程序的启动-退出同步锁 */
	private final Object startExitLock = new Object();

	// --------------------------------------------视图--------------------------------------------
	// 界面管理器。
	private final GuiManager guiManager = new TpncLibGuiManager();
	// 主界面。
	private MainFrame mainFrame;
	private final Object mainFrameLock = new Object();

	// --------------------------------------------观察器--------------------------------------------
	/** 配置观察器。 */
	private final ExconfigObverser coreConfigObverser = new CoreConfigObverserImpl(this);
	/** 后台模型侦听器 */
	private final BackgroundObverser backgroundObverser = new BackgroundObverserImpl(this);

	/**
	 * 新实例。
	 */
	public TpncLib() {
		this(new String[0]);
	}

	/**
	 * 新实例。
	 * 
	 * @param args
	 *            参数名称与值。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws IllegalArgumentException
	 *             参数<code>args</code> 不合法。
	 */
	public TpncLib(String... args) {
		Objects.requireNonNull(args, "入口参数 args 不能为 null。");

		// 加载系统参数
		for (String string : args) {
			StringTokenizer st = new StringTokenizer(string, "=");
			String key;
			String value;
			try {
				key = st.nextToken();
				value = st.nextToken();
			} catch (NoSuchElementException e) {
				throw new IllegalArgumentException("非法的参数：" + string);
			}
			if (!DEFAULT_PROPERTIES.containsKey(key)) {
				throw new IllegalArgumentException("不存在的键：" + key);
			}

			properties.put(key, value);
		}

		// 为自己保留引用。
		INSTANCES.add(this);
	}

	/**
	 * 获取NoteWizard中的工具包。
	 * 
	 * @return NoteWizard中的工具包。
	 */
	public Toolkit getToolkit() {
		return toolkit;
	}

	private void fireRuntimeStateChanged(RuntimeState oldState, RuntimeState newState) {
		for (ProgramObverser obverser : programObversers) {
			if (Objects.nonNull(obverser))
				obverser.fireRuntimeStateChanged(oldState, newState);
		}
	}

}
