package com.dwarfeng.tpnclib.core.util;

import com.dwarfeng.dutil.develop.cfg.SyncExconfigModel;
import com.dwarfeng.tpnclib.core.model.eum.ModalConfiguration;
import com.dwarfeng.tpnclib.core.view.gui.MainFrame;

/**
 * 与视图有关的工具包。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class ViewUtil {

	/**
	 * 将模态设置导入应用到主界面外观中。
	 * 
	 * <p>
	 * 该方法应该在 <b>AWT线程</b> 中执行。
	 * 
	 * @param modalConfig
	 *            指定的模态设置。
	 * @param mainFrame
	 *            指定的主界面。
	 * @return 是否全部导入成功。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public static boolean importMainFrameAppearance(SyncExconfigModel modalConfig, MainFrame mainFrame) {
		try {
			mainFrame.setWestPreferredValue(modalConfig
					.getParsedValue(ModalConfiguration.GUI_SIZE_MAINFRAME_WEST.getConfigKey(), Integer.class));

			int width = modalConfig.getParsedValue(ModalConfiguration.GUI_SIZE_MAINFRAME_WIDTH.getConfigKey(),
					Integer.class);
			int height = modalConfig.getParsedValue(ModalConfiguration.GUI_SIZE_MAINFRAME_HEIGHT.getConfigKey(),
					Integer.class);
			mainFrame.setSize(width, height);

			mainFrame.setExtendedState(modalConfig
					.getParsedValue(ModalConfiguration.GUI_STATE_MAINFRAME_EXTENDED.getConfigKey(), Integer.class));

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 将主界面的外观导出到模态设置中。
	 * 
	 * <p>
	 * 该方法应该在 <b>AWT线程</b> 中执行。
	 * 
	 * @param modalConfig
	 *            指定的模态设置。
	 * @param mainFrame
	 *            指定的主界面。
	 * @return 是否全部导出成功。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public static boolean exportMainFrameAppearance(SyncExconfigModel modalConfig, MainFrame mainFrame) {
		try {
			modalConfig.setParsedValue(ModalConfiguration.GUI_SIZE_MAINFRAME_WEST.getConfigKey(),
					mainFrame.getWestPreferredValue());

			modalConfig.setParsedValue(ModalConfiguration.GUI_SIZE_MAINFRAME_WIDTH.getConfigKey(),
					mainFrame.getSize().width);
			modalConfig.setParsedValue(ModalConfiguration.GUI_SIZE_MAINFRAME_HEIGHT.getConfigKey(),
					mainFrame.getSize().height);

			modalConfig.setParsedValue(ModalConfiguration.GUI_STATE_MAINFRAME_EXTENDED.getConfigKey(),
					mainFrame.getExtendedState());

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 禁止外部实例化。
	private ViewUtil() {
	}

}
