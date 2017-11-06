package com.dwarfeng.tpnclib.core.model.obv;

import com.dwarfeng.dutil.basic.prog.Obverser;

/**
 * 加工参数观察器。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface ProcessParamObverser extends Obverser {

	/**
	 * 通知观察器当前值发生了改变。
	 * 
	 * @param oldValue
	 *            指定的旧值。
	 * @param newValue
	 *            指定的新值。
	 */
	public void fireValueChanged(Object oldValue, Object newValue);

}
