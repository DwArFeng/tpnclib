package com.dwarfeng.tpnclib.core.model.struct;

import java.util.List;
import java.util.Map;

import com.dwarfeng.dutil.basic.str.Name;

/**
 * 抽象标准代码管理器。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public abstract class AbstractStandardCodeManager implements StandardCodeManager {

	/** 该管理器的工具包。 */
	protected final Toolkit toolkit;
	/** 该管理器的条目列表。 */
	protected final List<Name> items;
	/** 该管理器的NC代码映射。 */
	protected final Map<Name, NcCode> codeMap;

	protected AbstractStandardCodeManager(Toolkit toolkit, List<Name> items, Map<Name, NcCode> codeMap) {
		this.toolkit = toolkit;
		this.items = items;
		this.codeMap = codeMap;
	}

}
