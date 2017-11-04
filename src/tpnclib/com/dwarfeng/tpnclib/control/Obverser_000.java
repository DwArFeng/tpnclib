package com.dwarfeng.tpnclib.control;

import java.util.Objects;

import com.dwarfeng.dutil.basic.prog.Obverser;

/**
 * 抽象 TpncLib 用观察器。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
abstract class TpncLibObverser implements Obverser {

	/** TpncLib实例。 */
	protected final TpncLib tpncLib;

	/**
	 * 新实例。
	 * 
	 * @param tpncLib
	 *            指定的TpncLib实例。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public TpncLibObverser(TpncLib tpncLib) {
		Objects.requireNonNull(tpncLib, "入口参数 noteWizard 不能为 null。");
		this.tpncLib = tpncLib;
	}
}