package com.dwarfeng.tpnclib.library.yzsj;

import com.dwarfeng.dutil.basic.str.DefaultName;
import com.dwarfeng.tpnclib.core.model.struct.AbstractPieceCata;
import com.dwarfeng.tpnclib.core.model.struct.PieceCata;
import com.dwarfeng.tpnclib.core.model.struct.Toolkit;

/**
 * 圆锥试件。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class YzsjPieceCata extends AbstractPieceCata implements PieceCata {

	public static YzsjPieceCata newInstance(Toolkit toolkit) {
		return new YzsjPieceCata(toolkit);
	}

	protected YzsjPieceCata(Toolkit toolkit) {
		super(toolkit, new DefaultName("圆锥试件"), null);
	}

}
