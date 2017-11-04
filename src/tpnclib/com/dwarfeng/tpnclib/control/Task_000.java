package com.dwarfeng.tpnclib.control;

import java.util.Objects;

import com.dwarfeng.dutil.develop.backgr.AbstractTask;
import com.dwarfeng.tpnclib.model.eum.LabelStringKey;
import com.dwarfeng.tpnclib.model.eum.LoggerStringKey;
import com.dwarfeng.tpnclib.util.I18nUtil;

/**
 * 抽象 TpncLib 用任务。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
abstract class TpncLibTask extends AbstractTask {

	/** TpncLib实例。 */
	protected final TpncLib tpncLib;

	/**
	 * 新实例。
	 * 
	 * @param tpncLib
	 *            指定的NoteWizard实例。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public TpncLibTask(TpncLib tpncLib) {
		Objects.requireNonNull(tpncLib, "入口参数 tpncLib 不能为 null。");
		this.tpncLib = tpncLib;
	}

	/**
	 * 向记录器中输入一条信息。
	 * 
	 * @param loggerStringKey
	 *            指定的文本键。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	protected void info(LoggerStringKey loggerStringKey) {
		Objects.requireNonNull(loggerStringKey, "入口参数 loggerStringKey 不能为 null。");

		tpncLib.getToolkit().info(I18nUtil.getLoggerString(tpncLib.getToolkit().getLoggerI18nHandler().getCurrentI18n(),
				loggerStringKey));
	}

	/**
	 * 向记录器中格式化输入一条信息。
	 * 
	 * @param loggerStringKey
	 *            指定的文本键。
	 * @param args
	 *            参数。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	protected void formatInfo(LoggerStringKey loggerStringKey, Object... args) {
		Objects.requireNonNull(loggerStringKey, "入口参数 loggerStringKey 不能为 null。");
		Objects.requireNonNull(args, "入口参数 args 不能为 null。");

		tpncLib.getToolkit().info(String.format(
				I18nUtil.getLoggerString(tpncLib.getToolkit().getLoggerI18nHandler().getCurrentI18n(), loggerStringKey),
				args));
	}

	/**
	 * 向记录器中输入一条警告。
	 * 
	 * @param loggerStringKey
	 *            指定的文本键。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	protected void warn(LoggerStringKey loggerStringKey) {
		Objects.requireNonNull(loggerStringKey, "入口参数 loggerStringKey 不能为 null。");

		tpncLib.getToolkit().warn(I18nUtil.getLoggerString(tpncLib.getToolkit().getLoggerI18nHandler().getCurrentI18n(),
				loggerStringKey));
	}

	/**
	 * 向记录器中格式化输入一条警告。
	 * 
	 * @param loggerStringKey
	 *            指定的文本键。
	 * @param args
	 *            参数。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	protected void formatWarn(LoggerStringKey loggerStringKey, Object... args) {
		Objects.requireNonNull(loggerStringKey, "入口参数 loggerStringKey 不能为 null。");
		Objects.requireNonNull(args, "入口参数 args 不能为 null。");

		tpncLib.getToolkit().warn(String.format(
				I18nUtil.getLoggerString(tpncLib.getToolkit().getLoggerI18nHandler().getCurrentI18n(), loggerStringKey),
				args));
	}

	/**
	 * 向记录器中输入一条警告。
	 * 
	 * @param loggerStringKey
	 *            指定的文本键。
	 * @param e
	 *            指定的可抛出对象。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	protected void warn(LoggerStringKey loggerStringKey, Throwable e) {
		Objects.requireNonNull(loggerStringKey, "入口参数 loggerStringKey 不能为 null。");
		Objects.requireNonNull(e, "入口参数 e 不能为 null。");

		tpncLib.getToolkit().warn(
				I18nUtil.getLoggerString(tpncLib.getToolkit().getLoggerI18nHandler().getCurrentI18n(), loggerStringKey),
				e);
	}

	/**
	 * 获取标签文本。
	 * 
	 * @param labelStringKey
	 *            指定的标签文本键。
	 * @return 标签文本。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	protected String label(LabelStringKey labelStringKey) {
		Objects.requireNonNull(labelStringKey, "入口参数 labelStringKey 不能为 null。");

		return I18nUtil.getLabelString(tpncLib.getToolkit().getLabelI18nHandler().getCurrentI18n(), labelStringKey);
	}

	/**
	 * 获取格式化标签文本。
	 * 
	 * @param labelStringKey
	 *            指定的标签文本键。
	 * @param args
	 *            参数。
	 * @return 格式化标签文本。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	protected String formatLabel(LabelStringKey labelStringKey, Object... args) {
		Objects.requireNonNull(labelStringKey, "入口参数 labelStringKey 不能为 null。");
		Objects.requireNonNull(args, "入口参数 args 不能为 null。");

		return String.format(
				I18nUtil.getLabelString(tpncLib.getToolkit().getLabelI18nHandler().getCurrentI18n(), labelStringKey),
				args);
	}

}
