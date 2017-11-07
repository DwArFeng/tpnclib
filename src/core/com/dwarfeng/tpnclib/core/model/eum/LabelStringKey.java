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

	MAINFRAME_1(new DefaultName("mainframe.1")), //

	MFMENU_01_1(new DefaultName("mfmenu_01.1")), //
	MFMENU_01_2(new DefaultName("mfmenu_01.2")), //

	MFMENU_02_1(new DefaultName("mfmenu_02.1")), //

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
