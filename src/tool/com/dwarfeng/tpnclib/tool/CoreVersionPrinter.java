package com.dwarfeng.tpnclib.tool;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.basic.io.CT.OutputType;
import com.dwarfeng.tpnclib.core.control.TpncLib;

/**
 * 在控制台中输出程序的当前主程序的版本。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
class CoreVersionPrinter {

	public static void main(String[] args) {
		CT.setOutputType(OutputType.NO_DATE);
		CT.trace("当前的程序版本是 :");
		CT.trace(TpncLib.VERSION);
	}

}
