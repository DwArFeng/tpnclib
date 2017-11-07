package com.dwarfeng.tpnclib.core.model.struct;

import java.util.List;

/**
 * 定制代码管理器。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface CustomCodeManager {

	/**
	 * 获取该试件类别的根加工参数。
	 * 
	 * @return 该试件类别的根加工参数。
	 */
	public ProcessParam getRootProcessParam();

	/**
	 * 获取该试件类别接下来的加工参数。
	 * 
	 * @param processParams
	 *            已经确定的加工参数。
	 * @return 该试件类别接下来的加工参数。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public ProcessParam getNextProcessParam(List<ProcessParam> processParams);

	/**
	 * 获取指定的已确定加工参数能否生成NC代码。
	 * 
	 * @return 指定的已确定加工参数能否生成NC代码。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public boolean isNcCodeAvaliable(List<ProcessParam> processParams);

	/**
	 * 获取指定的已确定加工参数生成的NC代码。
	 * 
	 * @param processParams
	 *            指定的已确定加工参数生成的NC代码。
	 * @return 指定的已确定加工参数生成的NC代码。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public NcCode getNcCode(List<ProcessParam> processParams);

}
