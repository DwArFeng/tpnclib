package com.dwarfeng.tpnclib.core.model.struct;

import java.awt.Component;

import com.dwarfeng.dutil.basic.prog.ObverserSet;
import com.dwarfeng.dutil.basic.str.Name;
import com.dwarfeng.tpnclib.core.model.obv.ProcessParamObverser;

/**
 * 加工参数。
 * 
 * <p>
 * 重要接口。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface ProcessParam extends Name , ObverserSet<ProcessParamObverser>{

	/**
	 * 返回加工参数值。
	 * 
	 * @return 加工参数值。
	 */
	public Object getValue();

	/**
	 * 设置当前的加工参数值。
	 * 
	 * @param value
	 *            加工参数值。
	 * @return 该设置是否对参数值造成了改变。
	 */
	public boolean setValue(Object value);

	/**
	 * 返回渲染的编辑器组件。
	 * 
	 * @param value
	 *            指定的当前值。
	 * @param isSelected
	 *            是否被选中。
	 * @return 渲染的组件。
	 */
	public Component getTableCellEditorComponent(Object value, boolean isSelected);

	/**
	 * 返回渲染的渲染器组件。
	 * 
	 * @param value
	 *            指定的当前值。
	 * @param isSelected
	 *            是否被选中。
	 * @param hasFocus
	 *            是否拥有焦点。
	 * @return 渲染的组件。
	 */
	public Component getTableCellRendererComponent(Object value, boolean isSelected, boolean hasFocus);

}
