package com.dwarfeng.tpnclib.core.util;

import java.net.URLClassLoader;

import javax.swing.Icon;

import com.dwarfeng.dutil.basic.prog.ProcessException;
import com.dwarfeng.dutil.basic.prog.ProgramObverser;
import com.dwarfeng.dutil.basic.prog.RuntimeState;
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
import com.dwarfeng.tpnclib.core.model.eum.TpncLibProperty;
import com.dwarfeng.tpnclib.core.model.struct.Toolkit;
import com.dwarfeng.tpnclib.core.view.gui.MainFrame;
import com.dwarfeng.tpnclib.core.view.gui.NcSettingsFrame;
import com.dwarfeng.tpnclib.core.view.struct.GuiManager;
import com.dwarfeng.tpnclib.core.view.struct.WindowSuppiler;

/**
 * 记录程序中的各种常量。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class Constants {

	/**
	 * 无动作GUI管理器。
	 * 
	 * <p>
	 * 不执行任何动作的GUI管理器。
	 * 
	 * @author DwArFeng
	 * @since 0.0.1-alpha
	 */
	private static final class InnerGuiManager implements GuiManager {

	}

	/**
	 * 无权限工具包。
	 * 
	 * <p>
	 * 该工具包中的任何方法均无权限运行。
	 * 
	 * @author DwArFeng
	 * @since 0.0.1-alpha
	 */
	private static final class NonPermissionToolkit implements Toolkit {

		@Override
		public boolean addCoreConfigObverser(ExconfigObverser coreConfigObverser) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: addCoreConfigObverser");
		}

		@Override
		public boolean addProgramObverser(ProgramObverser obverser) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: addProgramObverser");
		}

		@Override
		public boolean disposeMainFrame() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: disposeMainFrame");
		}

		@Override
		public boolean disposeNcSettingsFrame() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: disposeNcSettingsFrame");
		}

		@Override
		public void error(String message, Throwable t) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: error");
		}

		@Override
		public void exit() throws IllegalStateException, ProcessException {
			throw new IllegalStateException("没有权限运行方法: exit");
		}

		@Override
		public void fatal(String message, Throwable t) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: fatal");
		}

		@Override
		public Background getBackground(BackgroundType type) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getBackground");
		}

		@Override
		public SyncExconfigModel getCoreConfigModel() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getCoreConfigModel");
		}

		@Override
		public ExconfigModel getCoreConfigModelReadOnly() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getCoreConfigModelReadOnly");
		}

		@Override
		public int getExitCode() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getExitCode");
		}

		@Override
		public SyncI18nHandler getLabelI18nHandler() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getLabelI18nHandler");
		}

		@Override
		public I18nHandler getLabelI18nHandlerReadOnly() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getLabelI18nHandlerReadOnly");
		}

		@Override
		public URLClassLoader getLibraryClassLoader() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getLibraryClassLoader");
		}

		@Override
		public SyncLoggerHandler getLoggerHandler() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getLoggerHandler");
		}

		@Override
		public LoggerHandler getLoggerHandlerReadOnly() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getLoggerHandlerReadOnly");
		}

		@Override
		public SyncI18nHandler getLoggerI18nHandler() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getLoggerI18nHandler");
		}

		@Override
		public I18nHandler getLoggerI18nHandlerReadOnly() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getLoggerI18nHandlerReadOnly");
		}

		@Override
		public MainFrame getMainFrame() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getMainFrame");
		}

		@Override
		public SyncExconfigModel getModalConfigModel() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getModalConfigModel");
		}

		@Override
		public ExconfigModel getModalConfigModelReadOnly() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getModalConfigModelReadOnly");
		}

		@Override
		public NcSettingsFrame getNcSettingsFrame() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getNcSettingsFrame");
		}

		@Override
		public String getProperty(TpncLibProperty property) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getProperty");
		}

		@Override
		public SyncResourceHandler getResourceHandler() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getResourceHandler");
		}

		@Override
		public ResourceHandler getResourceHandlerReadOnly() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getResourceHandlerReadOnly");
		}

		@Override
		public RuntimeState getRuntimeState() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: getRuntimeState");
		}

		@Override
		public void info(String message) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: info");
		}

		@Override
		public boolean isMainFrameVisible() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: isMainFrameVisible");
		}

		@Override
		public boolean newMainFrame() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: newMainFrame");
		}

		@Override
		public boolean newNcSettingsFrame() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: newNcSettingsFrame");
		}

		@Override
		public void setExitCode(int exitCode) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: setExitCode");
		}

		@Override
		public void setRuntimeState(RuntimeState runtimeState) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: setRuntimeState");
		}

		@Override
		public DialogOption showConfirmDialog(Object message) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: showConfirmDialog");
		}

		@Override
		public DialogOption showConfirmDialog(Object message, String title, DialogOptionCombo dialogOptionCombo)
				throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: showConfirmDialog");
		}

		@Override
		public DialogOption showConfirmDialog(Object message, String title, DialogOptionCombo dialogOptionCombo,
				DialogMessage dialogMessage) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: showConfirmDialog");
		}

		@Override
		public DialogOption showConfirmDialog(Object message, String title, DialogOptionCombo dialogOptionCombo,
				DialogMessage dialogMessage, Icon icon) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: showConfirmDialog");
		}

		@Override
		public void showExternalWindow(String key) {
			throw new IllegalStateException("没有权限运行方法: showExternalWindow");
		}

		@Override
		public void showExternalWindow(WindowSuppiler suppiler) {
			throw new IllegalStateException("没有权限运行方法: showExternalWindow");
		}

		@Override
		public String showInputDialog(Object message) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: showInputDialog");
		}

		@Override
		public String showInputDialog(Object message, Object initialSelectionValue) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: showInputDialog");
		}

		@Override
		public String showInputDialog(Object message, String title, DialogMessage dialogMessage)
				throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: showInputDialog");
		}

		@Override
		public Object showInputDialog(Object message, String title, DialogMessage dialogMessage, Icon icon,
				Object[] selectionValues, Object initialSelectionValue) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: showInputDialog");
		}

		@Override
		public void showMessageDialog(Object message) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: showMessageDialog");
		}

		@Override
		public void showMessageDialog(Object message, String title, DialogMessage dialogMessage)
				throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: showMessageDialog");
		}

		@Override
		public void showMessageDialog(Object message, String title, DialogMessage dialogMessage, Icon icon)
				throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: showMessageDialog");
		}

		@Override
		public int showOptionDialog(Object message, String title, DialogOptionCombo dialogOptionCombo,
				DialogMessage dialogMessage, Icon icon, Object[] options, Object initialValue)
				throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: showOptionDialog");
		}

		@Override
		public void start() throws IllegalStateException, ProcessException {
			throw new IllegalStateException("没有权限运行方法: start");
		}

		@Override
		public void submitTask(Task task, BackgroundType type) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: submitTask");
		}

		@Override
		public void trace(String message) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: trace");
		}

		@Override
		public void tryExit() throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: tryExit");
		}

		@Override
		public void warn(String message) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: warn");
		}

		@Override
		public void warn(String message, Throwable t) throws IllegalStateException {
			throw new IllegalStateException("没有权限运行方法: warn");
		}

	}

	/** 默认的，不执行任何动作的 GUI 管理器 */
	public final static GuiManager DEFAULT_GUIMANAGER = new InnerGuiManager();
	/** 默认的记录器信息的键 */
	public final static String LOGGER_DEFAULT = "std.all";
	/** 默认的丢失文本字段 */
	public final static String MISSING_LABEL = "！文本丢失";
	/** 处理器默认的，没有权限的工具包。 */
	public final static Toolkit NON_PERMISSION_TOOLKIT = new NonPermissionToolkit();
	/** 默认标签多语言文件所在的位置 */
	public final static String RESOURCE_I18N_LABEL_PATH = "/com/dwarfeng/tpnclib/core/resource/defaultres/i18n/label/default.properties";
	/** 默认记录器多语言文件所在的位置 */
	public final static String RESOURCE_I18N_LOGGER_PATH = "/com/dwarfeng/tpnclib/core/resource/defaultres/i18n/logger/default.properties";
	/** 图片根所在的位置 */
	public final static String RESOURCE_IMAGE_ROOT_PATH = "/com/dwarfeng/tpnclib/core/resource/image/";
	/** 记录器的默认设置所在的资源位置。 */
	public final static String RESOURCE_LOGGER_SETTING_PATH = "/com/dwarfeng/tpnclib/core/resource/defaultres/logger/setting.xml";
	/** 路径配置文件所在的位置。 */
	public final static String RESOURCE_PATH = "/com/dwarfeng/tpnclib/core/resource/paths.xml";

	// 禁止外部实例化
	private Constants() {
	}

}
