package com.dwarfeng.tpnclib.library.cfssj;

import com.dwarfeng.dutil.basic.str.DefaultName;
import com.dwarfeng.tpnclib.core.model.struct.AbstractPieceCata;
import com.dwarfeng.tpnclib.core.model.struct.PieceCata;
import com.dwarfeng.tpnclib.core.model.struct.Toolkit;

/**
 * 成飞S试件。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class CfssjPieceCata extends AbstractPieceCata implements PieceCata {

	public static CfssjPieceCata newInstance(Toolkit toolkit) {
		return new CfssjPieceCata(toolkit);
	}

	protected CfssjPieceCata(Toolkit toolkit) {
		super(toolkit, new DefaultName("成飞S试件"), null);
	}

}
