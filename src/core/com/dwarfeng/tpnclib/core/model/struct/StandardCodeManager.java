package com.dwarfeng.tpnclib.core.model.struct;

import java.util.List;

import com.dwarfeng.dutil.basic.str.Name;

/**
 * 标准代码管理器。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface StandardCodeManager {

	/**
	 * 获取所有条目。
	 * 
	 * @return 所有条目组成的列表。
	 */
	public List<Name> getItems();

	/**
	 * 获取指定的条目对应的NC代码。
	 * 
	 * @param item
	 *            指定的条目。
	 * @return 指定的条目对应的NC代码。
	 */
	public NcCode getNcCode(Name item);

}
