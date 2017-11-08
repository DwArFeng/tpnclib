package com.dwarfeng.tpnclib.core.view.struct;

import com.dwarfeng.tpnclib.core.model.struct.PieceCata;

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
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public void exit(ExecType type);

	/**
	 * 设置锚点工件类型。
	 * 
	 * @param newValue
	 *            指定的新锚点工件类型。
	 * @param type
	 *            以何种方式执行。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public void setAnchorPieceCata(PieceCata newValue, ExecType type);

}
