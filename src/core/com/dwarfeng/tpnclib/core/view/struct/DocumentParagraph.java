package com.dwarfeng.tpnclib.core.view.struct;

import java.util.Arrays;
import java.util.Objects;

import javax.swing.text.Style;

/**
 * 文档段落。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class DocumentParagraph {

	/** 段落的逻辑样式。 */
	private final Style logicStyle;
	/** 段落的区块组成 */
	private final DocumentBlock[] documentBlocks;

	/**
	 * 新实例。
	 * 
	 * @param logicStyle
	 *            指定的逻辑样式。
	 * @param documentBlocks
	 *            指定的区块组成。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public DocumentParagraph(Style logicStyle, DocumentBlock[] documentBlocks) {
		Objects.requireNonNull(logicStyle, "入口参数 logicStyle 不能为 null。");
		Objects.requireNonNull(documentBlocks, "入口参数 documentBlocks 不能为 null。");

		this.logicStyle = logicStyle;
		this.documentBlocks = documentBlocks;
	}

	/**
	 * @return the logicStyle
	 */
	public Style getLogicStyle() {
		return logicStyle;
	}

	/**
	 * @return the documentBlocks
	 */
	public DocumentBlock[] getDocumentBlocks() {
		return documentBlocks;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(documentBlocks);
		result = prime * result + ((logicStyle == null) ? 0 : logicStyle.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentParagraph other = (DocumentParagraph) obj;
		if (!Arrays.equals(documentBlocks, other.documentBlocks))
			return false;
		if (logicStyle == null) {
			if (other.logicStyle != null)
				return false;
		} else if (!logicStyle.equals(other.logicStyle))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "DocumentParagraph [logicStyle=" + logicStyle + ", documentBlocks=" + Arrays.toString(documentBlocks)
				+ "]";
	}

}
