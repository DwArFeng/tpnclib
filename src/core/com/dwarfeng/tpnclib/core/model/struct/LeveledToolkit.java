package com.dwarfeng.tpnclib.core.model.struct;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.Icon;
import javax.swing.text.StyledDocument;

import com.dwarfeng.dutil.basic.cna.model.ListModel;
import com.dwarfeng.dutil.basic.cna.model.ReferenceModel;
import com.dwarfeng.dutil.basic.cna.model.SyncListModel;
import com.dwarfeng.dutil.basic.cna.model.SyncReferenceModel;
import com.dwarfeng.dutil.basic.prog.ProcessException;
import com.dwarfeng.dutil.basic.prog.ProgramObverser;
import com.dwarfeng.dutil.basic.prog.RuntimeState;
import com.dwarfeng.dutil.basic.str.Name;
import com.dwarfeng.dutil.develop.backgr.Background;
import com.dwarfeng.dutil.develop.backgr.Task;
import com.dwarfeng.dutil.develop.cfg.ExconfigModel;
import com.dwarfeng.dutil.develop.cfg.SyncExconfigModel;
import com.dwarfeng.dutil.develop.cfg.obv.ExconfigObverser;
import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.dutil.develop.i18n.SyncI18nHandler;
import com.dwarfeng.dutil.develop.resource.ResourceHandler;
import com.dwarfeng.dutil.develop.resource.SyncResourceHandler;
import com.dwarfeng.tpnclib.core.model.cm.LoggerHandler;
import com.dwarfeng.tpnclib.core.model.cm.SyncLoggerHandler;
import com.dwarfeng.tpnclib.core.model.eum.DialogMessage;
import com.dwarfeng.tpnclib.core.model.eum.DialogOption;
import com.dwarfeng.tpnclib.core.model.eum.DialogOptionCombo;
import com.dwarfeng.tpnclib.core.model.eum.ToolkitLevel;
import com.dwarfeng.tpnclib.core.model.eum.TpncLibProperty;
import com.dwarfeng.tpnclib.core.model.io.PluginClassLoader;
import com.dwarfeng.tpnclib.core.view.gui.MainFrame;
import com.dwarfeng.tpnclib.core.view.struct.WindowSuppiler;

/**
 * 分级工具包。
 * 
 * <p>
 * 根据其中的分级确定什么方法是有权限的。
 * <p>
 * 该工具包代理一个标准工具，如果当前的权限小于指定方法运行所需的最小权限，则抛出权限不足异常；
 * 如果大于运行指定方法所需的最小权限，则代理标准工具的相应方法。 <br>
 * 要求标准工具应该能够执行所有方法，每个方法都不会抛出权限异常。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class LeveledToolkit implements Toolkit {

	private static final Map<Method, ToolkitLevel> MIN_LEVEL_MAP = new EnumMap<>(Method.class);

	static {
		MIN_LEVEL_MAP.put(Method.ADDCORECONFIGOBVERSER, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.ADDPROGRAMOBVERSER, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.DISPOSEMAINFRAME, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.ERROR, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.EXIT, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.FATAL, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETANCHORINSTRITEMMODEL, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETANCHORINSTRITEMMODELREADONLY, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETANCHORPIECECATAMODEL, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETANCHORPIECECATAMODELREADONLY, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETBACKGROUND, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETCORECONFIGMODEL, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETCORECONFIGMODELREADONLY, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETEXITCODE, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETINSTRDOCMODEL, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETINSTRITEMMODEL, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETINSTRITEMMODELREADONLY, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETLABELI18NHANDLER, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETLABELI18NHANDLERREADONLY, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETLIBRARYCLASSLOADER, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETLOGGERHANDLER, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETLOGGERHANDLERREADONLY, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETLOGGERI18NHANDLER, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETLOGGERI18NHANDLERREADONLY, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETMAINFRAME, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETMODALCONFIGMODEL, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETMODALCONFIGMODELREADONLY, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETPIECECATAMODEL, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETPIECECATAMODELREADONLY, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETPROPERTY, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETRESOURCEHANDLER, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETRESOURCEHANDLERREADONLY, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.GETRUNTIMESTATE, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.HASPERMISSION, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.INFO, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.NEWMAINFRAME, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.NOTHASPERMISSION, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.SETEXITCODE, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.SETRUNTIMESTATE, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.SHOWCONFIRMDIALOG, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.SHOWEXTERNALWINDOW, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.SHOWINPUTDIALOG, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.SHOWMESSAGEDIALOG, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.SHOWOPTIONDIALOG, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.START, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.SUBMITTASK, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.TRACE, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.TRYEXIT, ToolkitLevel.FULL);
		MIN_LEVEL_MAP.put(Method.WARN, ToolkitLevel.FULL);
	}

	private final ToolkitLevel currentLevel;
	private final Toolkit standardToolkit;

	private boolean stopFlag = false;
	private final Object stopFlagLock = new Object();

	/**
	 * 新建一个分级工具包。
	 * 
	 * @param standardToolkit
	 *            指定的标准工具包。
	 * @param currentLevel
	 *            当前的分级。
	 */
	public LeveledToolkit(Toolkit standardToolkit, ToolkitLevel currentLevel) {
		Objects.requireNonNull(standardToolkit, "入口参数 standardToolkit 不能为 null。");
		Objects.requireNonNull(currentLevel, "入口参数 currentLevel 不能为 null。");

		this.standardToolkit = standardToolkit;
		this.currentLevel = currentLevel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addCoreConfigObverser(ExconfigObverser coreConfigObverser) throws IllegalStateException {
		checkPermissionAndState(Method.ADDCORECONFIGOBVERSER);
		return standardToolkit.addCoreConfigObverser(coreConfigObverser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addProgramObverser(ProgramObverser obverser) throws IllegalStateException {
		checkPermissionAndState(Method.ADDPROGRAMOBVERSER);
		return standardToolkit.addProgramObverser(obverser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disposeMainFrame() throws IllegalStateException, ProcessException {
		checkPermissionAndState(Method.DISPOSEMAINFRAME);
		standardToolkit.disposeMainFrame();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void error(String message, Throwable t) throws IllegalStateException {
		checkPermissionAndState(Method.ERROR);
		standardToolkit.error(message, t);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void exit() throws IllegalStateException, ProcessException {
		checkPermissionAndState(Method.EXIT);
		standardToolkit.exit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fatal(String message, Throwable t) throws IllegalStateException {
		checkPermissionAndState(Method.FATAL);
		standardToolkit.fatal(message, t);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SyncReferenceModel<Name> getAnchorInstrItemModel() throws IllegalArgumentException {
		checkPermissionAndState(Method.GETANCHORINSTRITEMMODEL);
		return standardToolkit.getAnchorInstrItemModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReferenceModel<Name> getAnchorInstrItemModelReadOnly() throws IllegalArgumentException {
		checkPermissionAndState(Method.GETANCHORINSTRITEMMODELREADONLY);
		return standardToolkit.getAnchorInstrItemModelReadOnly();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SyncReferenceModel<PieceCata> getAnchorPieceCataModel() {
		checkPermissionAndState(Method.GETANCHORPIECECATAMODEL);
		return standardToolkit.getAnchorPieceCataModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReferenceModel<PieceCata> getAnchorPieceCataModelReadOnly() {
		checkPermissionAndState(Method.GETANCHORPIECECATAMODELREADONLY);
		return standardToolkit.getAnchorPieceCataModelReadOnly();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Background getBackground(BackgroundType type) throws IllegalStateException {
		checkPermissionAndState(Method.GETBACKGROUND);
		return standardToolkit.getBackground(type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SyncExconfigModel getCoreConfigModel() throws IllegalStateException {
		checkPermissionAndState(Method.GETCORECONFIGMODEL);
		return standardToolkit.getCoreConfigModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExconfigModel getCoreConfigModelReadOnly() throws IllegalStateException {
		checkPermissionAndState(Method.GETCORECONFIGMODELREADONLY);
		return standardToolkit.getCoreConfigModelReadOnly();
	}

	/**
	 * 获取当前的权限等级。
	 * 
	 * @return 当前的权限等级。
	 */
	public ToolkitLevel getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getExitCode() throws IllegalStateException {
		checkPermissionAndState(Method.GETEXITCODE);
		return standardToolkit.getExitCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SyncReferenceModel<StyledDocument> getInstrDocModel() throws IllegalArgumentException {
		checkPermissionAndState(Method.GETINSTRDOCMODEL);
		return standardToolkit.getInstrDocModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SyncListModel<Name> getInstrItemModel() throws IllegalArgumentException {
		checkPermissionAndState(Method.GETINSTRITEMMODEL);
		return standardToolkit.getInstrItemModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListModel<Name> getInstrItemModelReadOnly() throws IllegalArgumentException {
		checkPermissionAndState(Method.GETINSTRITEMMODELREADONLY);
		return standardToolkit.getInstrItemModelReadOnly();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SyncI18nHandler getLabelI18nHandler() throws IllegalStateException {
		checkPermissionAndState(Method.GETLABELI18NHANDLER);
		return standardToolkit.getLabelI18nHandler();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public I18nHandler getLabelI18nHandlerReadOnly() throws IllegalStateException {
		checkPermissionAndState(Method.GETLABELI18NHANDLERREADONLY);
		return standardToolkit.getLabelI18nHandlerReadOnly();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PluginClassLoader getLibraryClassLoader() throws IllegalStateException {
		checkPermissionAndState(Method.GETLIBRARYCLASSLOADER);
		return standardToolkit.getLibraryClassLoader();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SyncLoggerHandler getLoggerHandler() throws IllegalStateException {
		checkPermissionAndState(Method.GETLOGGERHANDLER);
		return standardToolkit.getLoggerHandler();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoggerHandler getLoggerHandlerReadOnly() throws IllegalStateException {
		checkPermissionAndState(Method.GETLOGGERHANDLERREADONLY);
		return standardToolkit.getLoggerHandlerReadOnly();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SyncI18nHandler getLoggerI18nHandler() throws IllegalStateException {
		checkPermissionAndState(Method.GETLOGGERI18NHANDLER);
		return standardToolkit.getLoggerI18nHandler();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public I18nHandler getLoggerI18nHandlerReadOnly() throws IllegalStateException {
		checkPermissionAndState(Method.GETLOGGERI18NHANDLERREADONLY);
		return standardToolkit.getLoggerI18nHandlerReadOnly();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MainFrame getMainFrame() throws IllegalStateException {
		checkPermissionAndState(Method.GETMAINFRAME);
		return standardToolkit.getMainFrame();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SyncExconfigModel getModalConfigModel() throws IllegalStateException {
		checkPermissionAndState(Method.GETMODALCONFIGMODEL);
		return standardToolkit.getModalConfigModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExconfigModel getModalConfigModelReadOnly() throws IllegalStateException {
		checkPermissionAndState(Method.GETMODALCONFIGMODELREADONLY);
		return standardToolkit.getModalConfigModelReadOnly();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SyncListModel<PieceCata> getPieceCataModel() {
		checkPermissionAndState(Method.GETPIECECATAMODEL);
		return standardToolkit.getPieceCataModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListModel<PieceCata> getPieceCataModelReadOnly() {
		checkPermissionAndState(Method.GETPIECECATAMODELREADONLY);
		return standardToolkit.getPieceCataModelReadOnly();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getProperty(TpncLibProperty property) throws IllegalStateException {
		checkPermissionAndState(Method.GETPROPERTY);
		return standardToolkit.getProperty(property);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SyncResourceHandler getResourceHandler() throws IllegalStateException {
		checkPermissionAndState(Method.GETRESOURCEHANDLER);
		return standardToolkit.getResourceHandler();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResourceHandler getResourceHandlerReadOnly() throws IllegalStateException {
		checkPermissionAndState(Method.GETRESOURCEHANDLERREADONLY);
		return standardToolkit.getResourceHandlerReadOnly();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RuntimeState getRuntimeState() throws IllegalStateException {
		checkPermissionAndState(Method.GETRUNTIMESTATE);
		return standardToolkit.getRuntimeState();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasPermission(Method method) {
		Objects.requireNonNull(method, "入口参数 method 不能为 null。");
		return currentLevel.getLevelValue() >= needLevel(method).getLevelValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void info(String message) throws IllegalStateException {
		checkPermissionAndState(Method.INFO);
		standardToolkit.info(message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void newMainFrame() throws IllegalStateException, ProcessException {
		checkPermissionAndState(Method.GETPROPERTY);
		standardToolkit.newMainFrame();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setExitCode(int exitCode) throws IllegalStateException {
		checkPermissionAndState(Method.SETEXITCODE);
		standardToolkit.setExitCode(exitCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRuntimeState(RuntimeState runtimeState) throws IllegalStateException {
		checkPermissionAndState(Method.SETRUNTIMESTATE);
		standardToolkit.setRuntimeState(runtimeState);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DialogOption showConfirmDialog(Object message) throws IllegalStateException {
		checkPermissionAndState(Method.SHOWCONFIRMDIALOG);
		return standardToolkit.showConfirmDialog(message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DialogOption showConfirmDialog(Object message, String title, DialogOptionCombo dialogOptionCombo)
			throws IllegalStateException {
		checkPermissionAndState(Method.SHOWCONFIRMDIALOG);
		return standardToolkit.showConfirmDialog(message, title, dialogOptionCombo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DialogOption showConfirmDialog(Object message, String title, DialogOptionCombo dialogOptionCombo,
			DialogMessage dialogMessage) throws IllegalStateException {
		checkPermissionAndState(Method.SHOWCONFIRMDIALOG);
		return standardToolkit.showConfirmDialog(message, title, dialogOptionCombo, dialogMessage);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DialogOption showConfirmDialog(Object message, String title, DialogOptionCombo dialogOptionCombo,
			DialogMessage dialogMessage, Icon icon) throws IllegalStateException {
		checkPermissionAndState(Method.SHOWCONFIRMDIALOG);
		return standardToolkit.showConfirmDialog(message, title, dialogOptionCombo, dialogMessage, icon);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void showExternalWindow(String key) {
		checkPermissionAndState(Method.SHOWEXTERNALWINDOW);
		standardToolkit.showExternalWindow(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void showExternalWindow(WindowSuppiler suppiler) {
		checkPermissionAndState(Method.SHOWEXTERNALWINDOW);
		standardToolkit.showExternalWindow(suppiler);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String showInputDialog(Object message) throws IllegalStateException {
		checkPermissionAndState(Method.SHOWINPUTDIALOG);
		return standardToolkit.showInputDialog(message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String showInputDialog(Object message, Object initialSelectionValue) throws IllegalStateException {
		checkPermissionAndState(Method.SHOWINPUTDIALOG);
		return standardToolkit.showInputDialog(message, initialSelectionValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String showInputDialog(Object message, String title, DialogMessage dialogMessage)
			throws IllegalStateException {
		checkPermissionAndState(Method.SHOWINPUTDIALOG);
		return standardToolkit.showInputDialog(message, title, dialogMessage);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object showInputDialog(Object message, String title, DialogMessage dialogMessage, Icon icon,
			Object[] selectionValues, Object initialSelectionValue) throws IllegalStateException {
		checkPermissionAndState(Method.SHOWINPUTDIALOG);
		return standardToolkit.showInputDialog(message, title, dialogMessage, icon, selectionValues,
				initialSelectionValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void showMessageDialog(Object message) throws IllegalStateException {
		checkPermissionAndState(Method.SHOWMESSAGEDIALOG);
		standardToolkit.showMessageDialog(message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void showMessageDialog(Object message, String title, DialogMessage dialogMessage)
			throws IllegalStateException {
		checkPermissionAndState(Method.SHOWMESSAGEDIALOG);
		standardToolkit.showMessageDialog(message, title, dialogMessage);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void showMessageDialog(Object message, String title, DialogMessage dialogMessage, Icon icon)
			throws IllegalStateException {
		checkPermissionAndState(Method.SHOWMESSAGEDIALOG);
		standardToolkit.showMessageDialog(message, title, dialogMessage, icon);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int showOptionDialog(Object message, String title, DialogOptionCombo dialogOptionCombo,
			DialogMessage dialogMessage, Icon icon, Object[] options, Object initialValue)
			throws IllegalStateException {
		checkPermissionAndState(Method.SHOWOPTIONDIALOG);
		return standardToolkit.showOptionDialog(message, title, dialogOptionCombo, dialogMessage, icon, options,
				initialValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start() throws IllegalStateException, ProcessException {
		checkPermissionAndState(Method.START);
		standardToolkit.start();
	}

	public void stop() {
		synchronized (stopFlagLock) {
			stopFlag = true;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void submitTask(Task task, BackgroundType type) throws IllegalStateException {
		checkPermissionAndState(Method.SUBMITTASK);
		standardToolkit.submitTask(task, type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "LeveledToolkit [standardToolkit=" + standardToolkit + ", currentLevel=" + currentLevel + ", stopFlag="
				+ stopFlag + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void trace(String message) throws IllegalStateException {
		checkPermissionAndState(Method.TRACE);
		standardToolkit.trace(message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void tryExit() throws IllegalStateException {
		checkPermissionAndState(Method.TRYEXIT);
		standardToolkit.tryExit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void warn(String message) throws IllegalStateException {
		checkPermissionAndState(Method.WARN);
		standardToolkit.warn(message);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void warn(String message, Throwable t) throws IllegalStateException {
		checkPermissionAndState(Method.WARN);
		standardToolkit.warn(message, t);
	}

	private void checkPermissionAndState(Method method) throws IllegalStateException {
		Objects.requireNonNull(method, "入口参数 method 不能为 null。");

		synchronized (stopFlagLock) {
			if (stopFlag)
				throw new IllegalStateException("这个工具包已经被停用。");
		}
		if (currentLevel.getLevelValue() < needLevel(method).getLevelValue())
			throw new IllegalStateException(String.format("方法 %s 需要的最小权限为 %s，而当前的权限为 %s。", method,
					needLevel(method).toString(), currentLevel.toString()));
	}

	private ToolkitLevel needLevel(Method method) {
		Objects.requireNonNull(method, "入口参数 method 不能为 null。");

		if (!MIN_LEVEL_MAP.containsKey(method)) {
			throw new IllegalArgumentException(String.format("权限表中没有指定的方法：%s。", method));
		}

		return MIN_LEVEL_MAP.get(method);
	}

}
