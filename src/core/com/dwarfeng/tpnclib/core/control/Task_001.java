package com.dwarfeng.tpnclib.core.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.gui.swing.SwingUtil;
import com.dwarfeng.dutil.basic.io.FileUtil;
import com.dwarfeng.dutil.basic.io.LoadFailedException;
import com.dwarfeng.dutil.basic.prog.ProcessException;
import com.dwarfeng.dutil.develop.cfg.io.PropConfigLoader;
import com.dwarfeng.dutil.develop.i18n.io.XmlPropFileI18nLoader;
import com.dwarfeng.dutil.develop.resource.Resource;
import com.dwarfeng.dutil.develop.resource.io.XmlJar2FileResourceLoader;
import com.dwarfeng.tpnclib.core.model.eum.LoggerStringKey;
import com.dwarfeng.tpnclib.core.model.eum.ResourceKey;
import com.dwarfeng.tpnclib.core.model.eum.TpncLibProperty;
import com.dwarfeng.tpnclib.core.model.io.Log4jLoggerLoader;
import com.dwarfeng.tpnclib.core.util.Constants;
import com.dwarfeng.tpnclib.core.util.ViewUtil;

/**
 * 用于程序初始化的任务。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
final class PoseTask extends TpncLibTask {

	public PoseTask(TpncLib tpncLib) {
		super(tpncLib);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void todo() throws Exception {
		// 输出初始化信息
		info(LoggerStringKey.TASK_POSE_6);

		// 加载所有资源。
		info(LoggerStringKey.TASK_POSE_7);
		XmlJar2FileResourceLoader resourceLoader = null;
		try {
			resourceLoader = new XmlJar2FileResourceLoader(
					this.getClass().getResourceAsStream(Constants.RESOURCE_PATH));
			resourceLoader.load(tpncLib.getToolkit().getResourceHandler());

		} finally {
			if (Objects.nonNull(resourceLoader)) {
				resourceLoader.close();
			}
		}

		// 加载记录器资源。
		info(LoggerStringKey.TASK_POSE_8);
		Log4jLoggerLoader loggerLoader = null;
		try {
			loggerLoader = new Log4jLoggerLoader(
					forceOpenInputStream(ResourceKey.LOGGER_SETTING, LoggerStringKey.TASK_POSE_0));
			Set<LoadFailedException> loadFailedExceptions = loggerLoader
					.countinuousLoad(tpncLib.getToolkit().getLoggerHandler());
			tpncLib.getToolkit().getLoggerHandler().useAll();
			for (LoadFailedException e : loadFailedExceptions) {
				warn(LoggerStringKey.TASK_POSE_1, e);
			}

		} finally {
			if (Objects.nonNull(loggerLoader)) {
				loggerLoader.close();
			}
		}

		// 加载记录器国际化资源。
		info(LoggerStringKey.TASK_POSE_9);
		XmlPropFileI18nLoader loggerI18nLoader = null;
		try {
			loggerI18nLoader = new XmlPropFileI18nLoader(
					forceOpenInputStream(ResourceKey.I18N_LOGGER_SETTING, LoggerStringKey.TASK_POSE_0));
			Set<LoadFailedException> loadFailedExceptions = loggerI18nLoader
					.countinuousLoad(tpncLib.getToolkit().getLoggerI18nHandler());
			for (LoadFailedException e : loadFailedExceptions) {
				warn(LoggerStringKey.TASK_POSE_2, e);
			}
		} finally {
			if (Objects.nonNull(loggerI18nLoader)) {
				loggerI18nLoader.close();
			}
		}

		// 加载标签国际化资源。
		info(LoggerStringKey.TASK_POSE_10);
		XmlPropFileI18nLoader labelI18nLoader = null;
		try {
			labelI18nLoader = new XmlPropFileI18nLoader(
					forceOpenInputStream(ResourceKey.I18N_LABEL_SETTING, LoggerStringKey.TASK_POSE_0));
			Set<LoadFailedException> loadFailedExceptions = labelI18nLoader
					.countinuousLoad(tpncLib.getToolkit().getLoggerI18nHandler());
			for (LoadFailedException e : loadFailedExceptions) {
				warn(LoggerStringKey.TASK_POSE_5, e);
			}
		} finally {
			if (Objects.nonNull(labelI18nLoader)) {
				labelI18nLoader.close();
			}
		}

		// 加载核心配置。
		info(LoggerStringKey.TASK_POSE_11);
		PropConfigLoader coreConfigLoader = null;
		try {
			coreConfigLoader = new PropConfigLoader(
					forceOpenInputStream(ResourceKey.CONFIGURATION_CORE, LoggerStringKey.TASK_POSE_0));
			Set<LoadFailedException> loadFailedExceptions = coreConfigLoader
					.countinuousLoad(tpncLib.getToolkit().getCoreConfigModel());
			for (LoadFailedException e : loadFailedExceptions) {
				warn(LoggerStringKey.TASK_POSE_3, e);
			}
		} finally {
			if (Objects.nonNull(coreConfigLoader)) {
				coreConfigLoader.close();
			}
		}

		// 加载模态配置。
		info(LoggerStringKey.TASK_POSE_12);
		PropConfigLoader modalConfigLoader = null;
		try {
			modalConfigLoader = new PropConfigLoader(
					forceOpenInputStream(ResourceKey.CONFIGURATION_MODAL, LoggerStringKey.TASK_POSE_0));
			Set<LoadFailedException> loadFailedExceptions = modalConfigLoader
					.countinuousLoad(tpncLib.getToolkit().getModalConfigModel());
			for (LoadFailedException e : loadFailedExceptions) {
				warn(LoggerStringKey.TASK_POSE_4, e);
			}
		} finally {
			if (Objects.nonNull(modalConfigLoader)) {
				modalConfigLoader.close();
			}
		}

		// 加载外部jar包。
		formatInfo(LoggerStringKey.TASK_POSE_13, tpncLib.getToolkit().getProperty(TpncLibProperty.NC_LIBRARY_PATH));
		File plugins_dir = new File(tpncLib.getToolkit().getProperty(TpncLibProperty.NC_LIBRARY_PATH));
		File[] plugin_jars = FileUtil.listAllFile(plugins_dir, file -> {
			if (file.getName().endsWith(".jar"))
				return true;
			return false;
		});

		formatInfo(LoggerStringKey.TASK_POSE_15, plugin_jars.length);
		for (File plugin_jar : plugin_jars) {
			formatInfo(LoggerStringKey.TASK_POSE_14, plugin_jar.getPath());
			tpncLib.getToolkit().getLibraryClassLoader().addURL(plugin_jar.toURI().toURL());
		}

		// 加载试件类。

		// 生成界面
		try {
			SwingUtil.invokeAndWaitInEventQueue(() -> {
				initMainFrame();
			});
		} catch (InterruptedException | InvocationTargetException ignore) {
			// 抛异常也要按照基本法。
		}

	}

	private InputStream forceOpenInputStream(ResourceKey resourceKey, LoggerStringKey loggerStringKey)
			throws IOException {
		Resource resource = tpncLib.getToolkit().getResourceHandler().get(resourceKey.getName());
		try {
			return resource.openInputStream();
		} catch (IOException e) {
			warn(loggerStringKey, e);
			resource.reset();
			return resource.openInputStream();
		}
	}

	private void initMainFrame() {
		try {
			tpncLib.getToolkit().newMainFrame();
		} catch (ProcessException e) {
			fatal(LoggerStringKey.TASK_POSE_16, e);
		}

		if (!ViewUtil.importMainFrameAppearance(tpncLib.getToolkit().getModalConfigModel(),
				tpncLib.getToolkit().getMainFrame())) {
			warn(LoggerStringKey.TASK_POSE_17);
		}

		tpncLib.getToolkit().getMainFrame().setVisible(true);
		tpncLib.getToolkit().getMainFrame().setLocationRelativeTo(null);

	}
}