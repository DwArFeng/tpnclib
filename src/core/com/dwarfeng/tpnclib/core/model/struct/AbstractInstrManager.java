package com.dwarfeng.tpnclib.core.model.struct;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.text.StyledDocument;

import com.dwarfeng.dutil.basic.prog.Buildable;
import com.dwarfeng.dutil.basic.str.Name;

/**
 * 抽象指导管理器。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public abstract class AbstractInstrManager implements InstrManager {

	/** 指导条目列表。 */
	protected final List<Name> instrItems;
	/** 首页指导项目。 */
	protected final Name frontpageInstrItems;
	/** 指导书构造器映射。 */
	protected final Map<Name, Buildable<StyledDocument>> instrDocBuilderMap;

	/**
	 * 
	 * @param instrItems
	 *            指定的指导条目列表，不能为 <code>null</code>。
	 * @param frontpageInstrItems
	 *            指定的首页指导条目。
	 * @param instrDocBuilderMap
	 *            指导书构造器映射，不能为 <code>null</code>。
	 */
	public AbstractInstrManager(List<Name> instrItems, Name frontpageInstrItems,
			Map<Name, Buildable<StyledDocument>> instrDocBuilderMap) {
		Objects.requireNonNull(instrItems, "入口参数 instrItems 不能为 null。");
		Objects.requireNonNull(instrDocBuilderMap, "入口参数 instrDocBuilderMap 不能为 null。");

		this.instrItems = instrItems;
		this.frontpageInstrItems = frontpageInstrItems;
		this.instrDocBuilderMap = instrDocBuilderMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Name getFrontPageInstrItem() {
		return frontpageInstrItems;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Name> getInstrItems() {
		return Collections.unmodifiableList(instrItems);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StyledDocument newInstrDoc(Name item) {
		if (!instrDocBuilderMap.containsKey(item)) {
			return null;
		}

		return instrDocBuilderMap.get(item).build();
	}

}
