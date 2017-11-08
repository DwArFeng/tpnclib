package com.dwarfeng.tpnclib.library.wmsj;

import com.dwarfeng.dutil.basic.str.DefaultName;
import com.dwarfeng.tpnclib.core.model.struct.AbstractPieceCata;
import com.dwarfeng.tpnclib.core.model.struct.PieceCata;
import com.dwarfeng.tpnclib.core.model.struct.Toolkit;

/**
 * 五面试件。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class WmsjPieceCata extends AbstractPieceCata implements PieceCata {

	public static WmsjPieceCata newInstance(Toolkit toolkit) {
		return new WmsjPieceCata(toolkit);
	}

	protected WmsjPieceCata(Toolkit toolkit) {
		super(toolkit, new DefaultName("五面试件"), null);
	}

}
