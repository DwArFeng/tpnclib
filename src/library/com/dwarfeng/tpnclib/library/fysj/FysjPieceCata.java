package com.dwarfeng.tpnclib.library.fysj;

import com.dwarfeng.dutil.basic.str.DefaultName;
import com.dwarfeng.tpnclib.core.model.struct.AbstractPieceCata;
import com.dwarfeng.tpnclib.core.model.struct.PieceCata;
import com.dwarfeng.tpnclib.core.model.struct.Toolkit;

/**
 * 方圆试件。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class FysjPieceCata extends AbstractPieceCata implements PieceCata {

	public static FysjPieceCata newInstance(Toolkit toolkit) {
		return new FysjPieceCata(toolkit);
	}

	protected FysjPieceCata(Toolkit toolkit) {
		super(toolkit, new DefaultName("方圆试件"), null);
	}

}
