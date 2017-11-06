package com.dwarfeng.tpnclib.core.model.obv;

/**
 * 加工参数观察器适配器。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public abstract class ProcessParamAdapter implements ProcessParamObverser {

	@Override
	public void fireValueChanged(Object oldValue, Object newValue) {
	}

}
