package com.dwarfeng.tpnclib.core.control;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.gui.swing.SwingUtil;
import com.dwarfeng.dutil.basic.io.SaveFailedException;
import com.dwarfeng.dutil.basic.prog.ProcessException;
import com.dwarfeng.dutil.develop.cfg.io.PropConfigSaver;
import com.dwarfeng.dutil.develop.resource.Resource;
import com.dwarfeng.tpnclib.core.model.eum.LoggerStringKey;
import com.dwarfeng.tpnclib.core.model.eum.ResourceKey;
import com.dwarfeng.tpnclib.core.model.struct.Toolkit.BackgroundType;
import com.dwarfeng.tpnclib.core.util.ViewUtil;

final class DisposeTask extends TpncLibTask {

	private static final int BACKGROUND_MASK = 1;
	private static final int MODAL_CONFIG_TASK = 2;

	private int backgroundMask = 0;
	private int modalConfigMask = 0;

	public DisposeTask(TpncLib tpncLib) {
		super(tpncLib);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void todo() throws Exception {
		// 试图停止后台。
		info(LoggerStringKey.TASK_DISPOSE_0);
		tpncLib.getToolkit().getBackground(BackgroundType.CONCURRENT).shutdown();
		tpncLib.getToolkit().getBackground(BackgroundType.FIFO).shutdown();

		// 当后台没有停止时执行的调度。
		if (!tpncLib.getToolkit().getBackground(BackgroundType.CONCURRENT).isTerminated()) {
			backgroundMask = BACKGROUND_MASK;
			formatWarn(LoggerStringKey.TASK_DISPOSE_1,
					tpncLib.getToolkit().getBackground(BackgroundType.CONCURRENT).tasks().size());
			tpncLib.getToolkit().getBackground(BackgroundType.CONCURRENT).getLock().readLock().lock();
			try {
				tpncLib.getToolkit().getBackground(BackgroundType.CONCURRENT).tasks().forEach(task -> {
					formatWarn(LoggerStringKey.TASK_DISPOSE_2, task.toString(), task.getClass().toString());
				});
			} finally {
				tpncLib.getToolkit().getBackground(BackgroundType.CONCURRENT).getLock().readLock().unlock();
			}
		}
		if (!tpncLib.getToolkit().getBackground(BackgroundType.FIFO).isTerminated()) {
			backgroundMask = BACKGROUND_MASK;
			formatWarn(LoggerStringKey.TASK_DISPOSE_1,
					tpncLib.getToolkit().getBackground(BackgroundType.FIFO).tasks().size());
			tpncLib.getToolkit().getBackground(BackgroundType.FIFO).getLock().readLock().lock();
			try {
				tpncLib.getToolkit().getBackground(BackgroundType.FIFO).tasks().forEach(task -> {
					formatWarn(LoggerStringKey.TASK_DISPOSE_2, task.toString(), task.getClass().toString());
				});
			} finally {
				tpncLib.getToolkit().getBackground(BackgroundType.FIFO).getLock().readLock().unlock();
			}
		}

		// 导出外观属性到模态配置。
		try {
			SwingUtil.invokeAndWaitInEventQueue(() -> {
				exportMainFrameAppearance();
			});
		} catch (InterruptedException | InvocationTargetException ignore) {
			// 抛异常也要按照基本法。
		}

		// 保存模态配置。
		info(LoggerStringKey.TASK_DISPOSE_3);
		PropConfigSaver modalConfigSaver = null;
		try {
			modalConfigSaver = new PropConfigSaver(
					forceOpenOutputStream(ResourceKey.CONFIGURATION_MODAL, LoggerStringKey.TASK_DISPOSE_4));
			Set<SaveFailedException> saveFailedExceptions = modalConfigSaver
					.countinuousSave(tpncLib.getToolkit().getModalConfigModel());
			for (SaveFailedException e : saveFailedExceptions) {
				warn(LoggerStringKey.TASK_DISPOSE_5, e);
			}
			if (!saveFailedExceptions.isEmpty()) {
				modalConfigMask = MODAL_CONFIG_TASK;
			}
		} finally {
			if (Objects.nonNull(modalConfigSaver)) {
				modalConfigSaver.close();
			}
		}

		// 释放界面。
		try {
			SwingUtil.invokeAndWaitInEventQueue(() -> {
				disposeMainFrame();
			});
		} catch (InterruptedException | InvocationTargetException ignore) {
			// 抛异常也要按照基本法。
		}

		// 设置退出代码。
		tpncLib.getToolkit().setExitCode(0 | backgroundMask | modalConfigMask);
	}

	private void disposeMainFrame() {
		try {
			tpncLib.getToolkit().disposeMainFrame();
		} catch (ProcessException e) {
			fatal(LoggerStringKey.TASK_DISPOSE_8, e);
		}
	}

	private void exportMainFrameAppearance() {
		if (!ViewUtil.exportMainFrameAppearance(tpncLib.getToolkit().getModalConfigModel(),
				tpncLib.getToolkit().getMainFrame())) {
			warn(LoggerStringKey.TASK_DISPOSE_6);
			return;
		}

	}

	private OutputStream forceOpenOutputStream(ResourceKey resourceKey, LoggerStringKey loggerStringKey)
			throws IOException {
		Resource resource = tpncLib.getToolkit().getResourceHandler().get(resourceKey.getName());
		try {
			return resource.openOutputStream();
		} catch (IOException e) {
			warn(loggerStringKey, e);
			resource.reset();
			return resource.openOutputStream();
		}
	}

}