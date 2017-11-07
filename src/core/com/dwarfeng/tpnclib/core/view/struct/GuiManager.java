package com.dwarfeng.tpnclib.core.view.struct;

/**
 * GUI管理器。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface GuiManager {

	/**
	 * 执行方式。
	 * 
	 * @author DwArFeng
	 * @since 0.0.1-alpha
	 */
	public enum ExecType {
		/** 并发执行 */
		CONCURRENT,
		/** 先进先出执行 */
		FIFO,
	}

	/**
	 * 退出程序。
	 * 
	 * @param type
	 *            以何种方式执行。
	 */
	public void exit(ExecType type);

}
