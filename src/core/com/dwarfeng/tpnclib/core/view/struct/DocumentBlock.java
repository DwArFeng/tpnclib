package com.dwarfeng.tpnclib.core.view.struct;

import javax.swing.text.Style;

/**
 * 文档区块。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class DocumentBlock {

	/** 区块格式 */
	private final Style style;
	/** 区块内容 */
	private final String content;

	/**
	 * 新实例。
	 * 
	 * @param style
	 *            指定的区块格式。
	 * @param content
	 *            指定的区块内容。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public DocumentBlock(Style style, String content) {
		this.style = style;
		this.content = content;
	}

	/**
	 * @return the style
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((style == null) ? 0 : style.hashCode());
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
		DocumentBlock other = (DocumentBlock) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (style == null) {
			if (other.style != null)
				return false;
		} else if (!style.equals(other.style))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "DocumentBlock [style=" + style + ", content=" + content + "]";
	}

}
