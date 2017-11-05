package com.dwarfeng.tpnclib.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.junit.Test;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.dutil.basic.io.CT.OutputType;
import com.dwarfeng.dutil.basic.io.IoUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.tpnclib.core.control.TpncLib;
import com.dwarfeng.tpnclib.core.util.Constants;

public class PathXmlTest {

	@Test
	public void test() throws IOException {
		StringOutputStream out = new StringOutputStream(Charset.forName("UTF-8"));
		InputStream in = TpncLib.class.getResourceAsStream(Constants.RESOURCE_PATH);
		try{
			IoUtil.trans(in, out, 4096);
			CT.setOutputType(OutputType.NO_DATE);
			CT.trace(out.toString());
		}finally {
			out.close();
			in.close();
		}
	}

}
