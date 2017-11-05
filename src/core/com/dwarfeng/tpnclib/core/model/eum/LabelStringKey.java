package com.dwarfeng.tpnclib.core.model.eum;

import com.dwarfeng.dutil.basic.str.DefaultName;
import com.dwarfeng.dutil.basic.str.Name;

/**
 * 记录器文本键。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public enum LabelStringKey implements Name {

	// 仅做示例。
	MFMENU_1_1(new DefaultName("mfmenu.1.1")), //

	;

	private Name name;

	private LabelStringKey(Name name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return name.getName();
	}

}
