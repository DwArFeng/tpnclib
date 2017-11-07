package com.dwarfeng.tpnclib.core.model.struct;

import javax.swing.Icon;

import com.dwarfeng.dutil.basic.cna.model.ListModel;
import com.dwarfeng.dutil.basic.cna.model.ReferenceModel;
import com.dwarfeng.dutil.basic.cna.model.SyncListModel;
import com.dwarfeng.dutil.basic.cna.model.SyncReferenceModel;
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
import com.dwarfeng.tpnclib.core.model.io.PluginClassLoader;
import com.dwarfeng.tpnclib.core.view.gui.MainFrame;
import com.dwarfeng.tpnclib.core.view.struct.WindowSuppiler;

/**
 * 工具包。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface Toolkit {

	/**
	 * 后台的类型。
	 * 
	 * @author DwArFeng
	 * @since 0.0.1-alpha
	 */
	public enum BackgroundType {
		/** 并发后台。 */
		CONCURRENT,
		/** 先进先出后台。 */
		FIFO,
	}

	/**
	 * 工具包的所有方法。
	 * 
	 * @author DwArFeng
	 * @since 0.0.1-alpha
	 */
	public enum Method {
		ADDCORECONFIGOBVERSER, //
		ADDPROGRAMOBVERSER, //
		DISPOSEMAINFRAME, //
		ERROR, //
		EXIT, //
		FATAL, //
		GETANCHORPIECECATAMODEL, //
		GETANCHORPIECECATAMODELREADONLY, //
		GETBACKGROUND, //
		GETCORECONFIGMODEL, //
		GETCORECONFIGMODELREADONLY, //
		GETEXITCODE, //
		GETLABELI18NHANDLER, //
		GETLABELI18NHANDLERREADONLY, //
		GETLIBRARYCLASSLOADER, //
		GETLOGGERHANDLER, //
		GETLOGGERHANDLERREADONLY, //
		GETLOGGERI18NHANDLER, //
		GETLOGGERI18NHANDLERREADONLY, //
		GETMAINFRAME, //
		GETMODALCONFIGMODEL, //
		GETMODALCONFIGMODELREADONLY, //
		GETPIECECATAMODEL, //
		GETPIECECATAMODELREADONLY, //
		GETPROPERTY, //
		GETRESOURCEHANDLER, //
		GETRESOURCEHANDLERREADONLY, //
		GETRUNTIMESTATE, //
		HASPERMISSION, //
		INFO, //
		NEWMAINFRAME, //
		NOTHASPERMISSION, //
		SETEXITCODE, //
		SETRUNTIMESTATE, //
		SHOWCONFIRMDIALOG, //
		SHOWEXTERNALWINDOW, //
		SHOWINPUTDIALOG, //
		SHOWMESSAGEDIALOG, //
		SHOWOPTIONDIALOG, //
		START, //
		SUBMITTASK, //
		TRACE, //
		TRYEXIT, //
		WARN,//
	}

	/**
	 * 向程序中添加一个核心配置观察器。
	 * 
	 * @param coreConfigObverser
	 *            指定的核心配置观察器。
	 * @return 是否添加成功。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public boolean addCoreConfigObverser(ExconfigObverser coreConfigObverser) throws IllegalStateException;

	/**
	 * 移除指定的程序观察器。
	 * 
	 * @param obverser
	 *            指定的程序观察器。
	 * @return 是否添加成功。
	 */
	public boolean addProgramObverser(ProgramObverser obverser) throws IllegalStateException;

	/**
	 * 释放主界面。
	 * 
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 * @throws ProcessException
	 *             过程异常。
	 */
	public void disposeMainFrame() throws IllegalStateException, ProcessException;

	/**
	 * 使用指定的记录器处理器 <code>error</code> 一条信息。
	 * 
	 * @param message
	 *            指定的信息。
	 * @param t
	 *            指定的可抛出对象。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public void error(String message, Throwable t) throws IllegalStateException;

	/**
	 * 退出程序。
	 * 
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 * @throws ProcessException
	 *             过程异常。
	 */
	public void exit() throws IllegalStateException, ProcessException;

	/**
	 * 使用指定的记录器处理器 <code>fatal</code> 一条信息。
	 * 
	 * @param message
	 *            指定的信息。
	 * @param t
	 *            指定的可抛出对象。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public void fatal(String message, Throwable t) throws IllegalStateException;

	/**
	 * 获取锚点试件模型。
	 * 
	 * @return 锚点试件模型。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public SyncReferenceModel<PieceCata> getAnchorPieceCataModel();

	/**
	 * 获取锚点试件模型。
	 * <p>
	 * 该锚点试件模型是只读的。
	 * 
	 * @return 锚点试件模型。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public ReferenceModel<PieceCata> getAnchorPieceCataModelReadOnly();

	/**
	 * 获取程序中的后台。
	 * 
	 * @param type
	 *            后台的类型。
	 * @return 程序中的后台。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public Background getBackground(BackgroundType type) throws IllegalStateException;

	/**
	 * 获取程序中的核心配置模型。
	 * 
	 * @return 程序中的核心配置模型。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public SyncExconfigModel getCoreConfigModel() throws IllegalStateException;

	/**
	 * 获取程序中的核心配置模型。
	 * <p>
	 * 配置模型是只读的。
	 * 
	 * @return 程序中的核心配置模型。
	 */
	public ExconfigModel getCoreConfigModelReadOnly() throws IllegalStateException;

	/**
	 * 获取程序的退出代码。
	 * <p>
	 * 只有当程序退出时，该代码才有效。 虽然在程序正在运行或还未运行的时候仍能获取该代码，但是此时该代码是无效的。
	 * 
	 * @return 程序的退出代码。
	 */
	public int getExitCode() throws IllegalStateException;

	/**
	 * 获取标签国际化处理器。
	 * 
	 * @return 标签国际化处理器。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public SyncI18nHandler getLabelI18nHandler() throws IllegalStateException;

	/**
	 * 获取标签国际化处理器。
	 * 
	 * <p>
	 * 标签国际化处理器是只读的。
	 * 
	 * @return 标签国际化处理器。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public I18nHandler getLabelI18nHandlerReadOnly() throws IllegalStateException;

	/**
	 * 获取程序中的NC库类加载器。
	 * 
	 * @return 程序中的NC库类加载器。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public PluginClassLoader getLibraryClassLoader() throws IllegalStateException;

	/**
	 * 获取程序中的记录器处理器。
	 * 
	 * @return 程序中的记录器处理器。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public SyncLoggerHandler getLoggerHandler() throws IllegalStateException;

	/**
	 * 获取程序中的记录器处理器。
	 * <p>
	 * 获取的记录器是只读的。
	 * 
	 * @return 程序中的记录器处理器。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public LoggerHandler getLoggerHandlerReadOnly() throws IllegalStateException;

	/**
	 * 获取记录器国际化处理器。
	 * 
	 * @return 记录器国际化处理器。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public SyncI18nHandler getLoggerI18nHandler() throws IllegalStateException;

	/**
	 * 获取记录器国际化处理器。
	 * 
	 * <p>
	 * 记录器国际化处理器是只读的。
	 * 
	 * @return 记录器国际化处理器。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public I18nHandler getLoggerI18nHandlerReadOnly() throws IllegalStateException;

	/**
	 * 获取程序中的主界面。
	 * 
	 * @return 程序中的主界面。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public MainFrame getMainFrame() throws IllegalStateException;

	/**
	 * 获取模态配置模型。
	 * 
	 * @return 模态配置模型。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public SyncExconfigModel getModalConfigModel() throws IllegalStateException;

	/**
	 * 获取程序中的模态配置模型。
	 * 
	 * <p>
	 * 模态配置模型是只读的。
	 * 
	 * @return 程序中的模态配置模型。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public ExconfigModel getModalConfigModelReadOnly() throws IllegalStateException;

	/**
	 * 获取试件类型模型。
	 * 
	 * @return 试件类型模型。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public SyncListModel<PieceCata> getPieceCataModel();

	/**
	 * 获取试件类型模型。
	 * <p>
	 * 该试件类型模型是只读的。
	 * 
	 * @return 试件类型模型。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public ListModel<PieceCata> getPieceCataModelReadOnly();

	/**
	 * 获取程序属性。
	 * 
	 * @param property
	 *            程序属性键。
	 * @return 属性键对应的属性。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public String getProperty(TpncLibProperty property) throws IllegalStateException;

	/**
	 * 获取资源处理器。
	 * 
	 * @return 资源处理器。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public SyncResourceHandler getResourceHandler() throws IllegalStateException;

	/**
	 * 获取资源处理器。
	 * <p>
	 * 该资源处理器是只读的。
	 * 
	 * @return 资源处理器。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public ResourceHandler getResourceHandlerReadOnly() throws IllegalStateException;

	/**
	 * 获取程序的运行状态。
	 * 
	 * @return 程序的运行状态。
	 */
	public RuntimeState getRuntimeState() throws IllegalStateException;

	/**
	 * 返回工具包是否拥有权限执行指定的方法。
	 * 
	 * @param method
	 *            指定的方法。
	 * @return 工具是否拥有权限执行指定的方法。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public boolean hasPermission(Method method);

	/**
	 * 使用指定的记录器处理器 <code>info</code> 一条信息。
	 * 
	 * @param message
	 *            指定的信息。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public void info(String message) throws IllegalStateException;

	/**
	 * 新建主界面。
	 * 
	 * @return 是否新建成功。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 * @throws ProcessException
	 *             过程异常。
	 */
	public void newMainFrame() throws IllegalStateException, ProcessException;

	/**
	 * 判断该工具包是否没有权限指定指定的方法。
	 * 
	 * @param method
	 *            指定的方法。
	 * @return 该工具包是否没有权限执行指定的方法。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public default boolean notHasPermission(Method method) {
		return !hasPermission(method);
	}

	/**
	 * 设置程序的退出代码。
	 * 
	 * @param exitCode
	 *            指定的退出代码。
	 */
	public void setExitCode(int exitCode) throws IllegalStateException;

	/**
	 * 设置程序中的运行状态。
	 * <p>
	 * 参数不能为 <code>null</code>。
	 * 
	 * @param runtimeState
	 *            程序的运行状态。
	 */
	public void setRuntimeState(RuntimeState runtimeState) throws IllegalStateException;

	/**
	 * 在前台显示一个确认对话框。
	 * 
	 * @param message
	 *            指定的信息。
	 * @return 用户选择的选项。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public DialogOption showConfirmDialog(Object message) throws IllegalStateException;

	/**
	 * 在前台显示一个确认对话框。
	 * 
	 * @param message
	 *            指定的信息。
	 * @param title
	 *            指定的标题。
	 * @param dialogOptionCombo
	 *            指定的组合选项。
	 * @return 用户选择的选项。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public DialogOption showConfirmDialog(Object message, String title, DialogOptionCombo dialogOptionCombo)
			throws IllegalStateException;

	/**
	 * 在前台显示一个确认对话框。
	 * 
	 * @param message
	 *            指定的信息。
	 * @param title
	 *            指定的标题。
	 * @param dialogOptionCobo
	 *            指定的组合选项。
	 * @param dialogMessage
	 *            指定的信息类型。
	 * @return 用户选择的选项。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public DialogOption showConfirmDialog(Object message, String title, DialogOptionCombo dialogOptionCombo,
			DialogMessage dialogMessage) throws IllegalStateException;

	/**
	 * 在前台显示一个确认对话框。
	 * 
	 * @param message
	 *            指定的信息。
	 * @param title
	 *            指定的标题。
	 * @param dialogOptionCobo
	 *            指定的组合选项。
	 * @param dialogMessage
	 *            指定的信息类型。
	 * @param icon
	 *            指定的图标。
	 * @return 用户选择的选项。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public DialogOption showConfirmDialog(Object message, String title, DialogOptionCombo dialogOptionCombo,
			DialogMessage dialogMessage, Icon icon) throws IllegalStateException;

	/**
	 * 展示一个外部窗口。
	 * 
	 * <p>
	 * 该方法会在程序的外部窗口模型中查找具有指定键的窗口提供器， 如果找到了， 则将该提供器设为可见的；
	 * 如果在外部窗口模型中找不到指定的键，则什么也不做。
	 * 
	 * @param key
	 *            指定的键。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public void showExternalWindow(String key);

	/**
	 * 展示一个外部的窗口。
	 * 
	 * <p>
	 * 该方法会在程序的外部窗口模型中查找具有与指定的窗口提供器键相同的窗口提供器， 如果找不到，则按照流程向模型中添加该提供器，并显示提供器中的窗口；
	 * 如果在模型中找到了相同的键的提供器，则对比两个提供器是否严格相等， 如果严格相等，则显示该提供器的窗口；
	 * 如果不是严格相等，则用该窗口提供器替换掉眼线的窗口提供器。
	 * 
	 * @param suppiler
	 *            窗口提供器。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public void showExternalWindow(WindowSuppiler suppiler);

	/**
	 * 在前台显示一个输入对话框。
	 * <p>
	 * 该方法返回用户输入的文本，如果用户没有点击确定，而是关闭了对话框，则返回 <code>null</code>。
	 * 
	 * @param message
	 *            指定的提示文本。
	 * @return 用户的输入的信息。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public String showInputDialog(Object message) throws IllegalStateException;

	/**
	 * 在前台显示一个输入对话框。
	 * <p>
	 * 该方法返回用户输入的文本，如果用户没有点击确定，而是关闭了对话框，则返回 <code>null</code>。
	 * 
	 * @param message
	 *            指定的提示文本。
	 * @param initialSelectionValue
	 *            初始的默认值。
	 * @return 用户的输入的信息。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public String showInputDialog(Object message, Object initialSelectionValue) throws IllegalStateException;

	/**
	 * 在前台显示一个输入对话框。
	 * <p>
	 * 该方法返回用户输入的文本，如果用户没有点击确定，而是关闭了对话框，则返回 <code>null</code>。
	 * 
	 * @param message
	 *            指定的提示文本。
	 * @param title
	 *            指定的标题。
	 * @param dialogMessage
	 *            对话框的信息类型。
	 * @return 用户的输入的信息。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public String showInputDialog(Object message, String title, DialogMessage dialogMessage)
			throws IllegalStateException;

	/**
	 * 在前台显示一个输入对话框。
	 * <p>
	 * 该方法返回用户输入的文本，如果用户没有点击确定，而是关闭了对话框，则返回 <code>null</code>。
	 * 
	 * @param message
	 *            指定的提示文本。
	 * @param title
	 *            指定的标题。
	 * @param dialogMessage
	 *            对话框的信息类型。
	 * @param icon
	 *            指定的显示图标。
	 * @param selectionValues
	 *            有可能的值组成的数组。
	 * @param initialSelectionValue
	 *            初始的默认值。
	 * @return 用户的输入的信息。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public Object showInputDialog(Object message, String title, DialogMessage dialogMessage, Icon icon,
			Object[] selectionValues, Object initialSelectionValue) throws IllegalStateException;

	/**
	 * 在前台显示一个信息对话框。
	 * 
	 * @param message
	 *            指定的信息。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public void showMessageDialog(Object message) throws IllegalStateException;

	/**
	 * 在前台显示一个信息对话框。
	 * 
	 * @param message
	 *            指定的信息。
	 * @param title
	 *            指定的标题。
	 * @param dialogMessage
	 *            指定的信息类型。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public void showMessageDialog(Object message, String title, DialogMessage dialogMessage)
			throws IllegalStateException;

	/**
	 * 在前台显示一个信息对话框。
	 * 
	 * @param message
	 *            指定的信息。
	 * @param title
	 *            指定的标题。
	 * @param dialogMessage
	 *            指定的信息类型。
	 * @param icon
	 *            指定的图标。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public void showMessageDialog(Object message, String title, DialogMessage dialogMessage, Icon icon)
			throws IllegalStateException;

	/**
	 * 在前台显示一个选项对话框。
	 * 
	 * @param message
	 *            指定的信息。
	 * @param title
	 *            指定的标题。
	 * @param dialogOptionCobo
	 *            指定的组合选项。
	 * @param dialogMessage
	 *            指定的信息类型。
	 * @param icon
	 *            指定的图标。
	 * @param options
	 *            指定的选项组成的数组。
	 * @param initialValue
	 *            指定的初始值。
	 * @return 用户选择的选项的序号。
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 */
	public int showOptionDialog(Object message, String title, DialogOptionCombo dialogOptionCombo,
			DialogMessage dialogMessage, Icon icon, Object[] options, Object initialValue) throws IllegalStateException;

	/**
	 * 启动程序。
	 * 
	 * @throws IllegalStateException
	 *             因为没有权限而抛出的异常。
	 * @throws ProcessException
	 *             过程异常。
	 */
	public void start() throws IllegalStateException, ProcessException;

	/**
	 * 向程序的后台提交一个任务。
	 * 
	 * @param task
	 *            指定的任务。
	 * @param type
	 *            向何种后台提交任务。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public void submitTask(Task task, BackgroundType type) throws IllegalStateException;

	/**
	 * 使用指定的记录器处理器 <code>trace</code> 一条信息。
	 * 
	 * @param message
	 *            指定的信息。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public void trace(String message) throws IllegalStateException;

	/**
	 * 尝试退出程序。
	 * 
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public void tryExit() throws IllegalStateException;

	/**
	 * 使用指定的记录器处理器 <code>warn</code> 一条信息。
	 * 
	 * @param message
	 *            指定的信息。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public void warn(String message) throws IllegalStateException;

	/**
	 * 使用指定的记录器处理器 <code>warn</code> 一条信息。
	 * 
	 * @param message
	 *            指定的信息。
	 * @param t
	 *            指定的可抛出对象。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 * @throws IllegalStateException
	 *             因为没有执行权限而抛出的异常。
	 */
	public void warn(String message, Throwable t) throws IllegalStateException;

}
