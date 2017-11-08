package com.dwarfeng.tpnclib.core.model.struct;

import java.awt.Image;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.swing.text.StyledDocument;

import com.dwarfeng.dutil.basic.str.Name;
import com.dwarfeng.tpnclib.core.util.Constants;

/**
 * 抽象工件类型。
 * 
 * <p>
 * 工件类型接口的抽象实现。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public abstract class AbstractPieceCata implements PieceCata {

	/** 指定的工具包。 */
	protected final Toolkit toolkit;
	/** 指定的名称 */
	protected final Name name;
	/** 指定的图标图片。 */
	protected final Image iconImage;
	/** 指导条目列表。 */
	protected final List<Name> instrItems;

	/**
	 * 新实例。
	 * 
	 * @param toolkit
	 *            指定的工具包。
	 * @param name
	 *            指定的试件名称，不能为 <code>null</code>。
	 * @param iconImage
	 *            指定的图标图片。
	 * @param instrItems
	 *            指定的指导条目列表，不能为 <code>null</code>。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	protected AbstractPieceCata(Toolkit toolkit, Name name, Image iconImage, List<Name> instrItems) {
		Objects.requireNonNull(name, "入口参数 name 不能为 null。");
		Objects.requireNonNull(instrItems, "入口参数 instrItems 不能为 null。");

		this.toolkit = Objects.isNull(toolkit) ? Constants.NON_PERMISSION_TOOLKIT : toolkit;
		this.name = name;
		this.iconImage = iconImage;
		this.instrItems = instrItems;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getIconImage() {
		return iconImage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Name> getInstructionItems() {
		return Collections.unmodifiableList(instrItems);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCustomCodeAvaliable() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isStandardCodeAvaliable() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomCodeManager newCustomCodeManager() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StyledDocument newInstruction(Name item) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StandardCodeManager newStandardCodeManager() {
		return null;
	}

}
