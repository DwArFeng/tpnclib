package com.dwarfeng.tpnclib.core.model.struct;

import java.io.IOException;
import java.io.InputStream;

/**
 * NC代码。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface NcCode {

	/**
	 * 获取该NC代码的标准名称。
	 * 
	 * @return 该NC代码的标准名称。
	 */
	public String getDefaultFileName();

	/**
	 * 开启该NC代码的输出流。
	 * 
	 * @return 该NC代码的输出流。
	 * @throws IOException
	 *             IO异常。
	 */
	public InputStream openInputStream() throws IOException;
	
	

}
