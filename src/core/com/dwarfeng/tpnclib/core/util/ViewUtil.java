package com.dwarfeng.tpnclib.core.util;

import com.dwarfeng.dutil.develop.cfg.SyncExconfigModel;
import com.dwarfeng.tpnclib.core.view.gui.MainFrame;
import com.dwarfeng.tpnclib.core.view.gui.NcSettingsFrame;

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
			// TODO Auto-generated Method Stub.
			return false;
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
			// TODO Auto-generated Method Stub.
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 将模态设置导入到NC设置界面外观中。
	 * 
	 * <p>
	 * 该方法应该在 <b>AWT线程</b> 中执行。
	 * 
	 * @param modalConfig
	 *            指定的模态设置。
	 * @param ncSettingsFrame
	 *            指定的NC设置界面。
	 * @return 是否全部导入成功。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public static boolean importNcSettingsFrameAppearence(SyncExconfigModel modalConfig,
			NcSettingsFrame ncSettingsFrame) {
		try {
			// TODO Auto-generated Method Stub.
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 将NC设置界面的外观导出到模态设置中。
	 * 
	 * @param modalConfig
	 *            指定的模态设置。
	 * @param ncSettingsFrame
	 *            指定的的NC设置界面。
	 * @return 是否全部导出成功。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public static boolean exportNcSettingsFrameApperence(SyncExconfigModel modalConfig,
			NcSettingsFrame ncSettingsFrame) {
		try {
			// TODO Auto-generated Method Stub.
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	// 禁止外部实例化。
	private ViewUtil() {
	}

}
