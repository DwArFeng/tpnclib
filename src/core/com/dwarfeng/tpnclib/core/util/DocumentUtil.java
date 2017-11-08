package com.dwarfeng.tpnclib.core.util;

import java.awt.Image;
import java.util.Enumeration;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import com.dwarfeng.dutil.basic.str.Name;
import com.dwarfeng.tpnclib.core.view.struct.DocumentBlock;
import com.dwarfeng.tpnclib.core.view.struct.DocumentParagraph;

/**
 * 有关文档的工具包。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class DocumentUtil {

	/**
	 * 区块类型。
	 * 
	 * @author DwArFeng
	 * @since 0.0.1-alpha
	 */
	public enum BlockStyle implements Name {
		/** 无类型 */
		NONE("none"), //

		;

		private final String name;

		private BlockStyle(String name) {
			this.name = name;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getName() {
			return name;
		}

	}

	/**
	 * 文档段落类型。
	 * 
	 * @author DwArFeng
	 * @since 0.0.1-alpha
	 */
	public enum ParagraphStyle implements Name {
		/** 作者 */
		AUTHOR("author"), //
		/** 默认类型 */
		DEFAULT("defalut"), //
		/** 版本 */
		EDITION("edition"), //
		/** 标题 */
		HEADING("heading"), //
		/** 正文文本 */
		NORMAL("normal"), //
		/** 子标题 */
		SUBTITLE("subtitle"), //
		/** 标题 */
		TITLE("title"),//

		;

		private final String name;

		private ParagraphStyle(String name) {
			this.name = name;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getName() {
			return name;
		}

	}

	private static final class UnmodifiableStyle implements Style {

		private final Style delegate;

		public UnmodifiableStyle(Style delegate) {
			this.delegate = delegate;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void addAttribute(Object name, Object value) {
			throw new UnsupportedOperationException("addAttribute");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void addAttributes(AttributeSet attributes) {
			throw new UnsupportedOperationException("addAttributes");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void removeAttribute(Object name) {
			throw new UnsupportedOperationException("removeAttribute");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void removeAttributes(Enumeration<?> names) {
			throw new UnsupportedOperationException("removeAttributes");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void removeAttributes(AttributeSet attributes) {
			throw new UnsupportedOperationException("removeAttributes");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void setResolveParent(AttributeSet parent) {
			throw new UnsupportedOperationException("setResolveParent");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int getAttributeCount() {
			return delegate.getAttributeCount();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isDefined(Object attrName) {
			return delegate.isDefined(attrName);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isEqual(AttributeSet attr) {
			return delegate.isEqual(attr);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public AttributeSet copyAttributes() {
			return delegate.copyAttributes();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object getAttribute(Object key) {
			return delegate.getAttribute(key);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Enumeration<?> getAttributeNames() {
			return delegate.getAttributeNames();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsAttribute(Object name, Object value) {
			return delegate.containsAttribute(name, value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsAttributes(AttributeSet attributes) {
			return delegate.containsAttributes(attributes);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public AttributeSet getResolveParent() {
			return delegate.getResolveParent();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getName() {
			return delegate.getName();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void addChangeListener(ChangeListener l) {
			throw new UnsupportedOperationException("addChangeListener");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void removeChangeListener(ChangeListener l) {
			throw new UnsupportedOperationException("removeChangeListener");
		}

	}

	private static final class MyStyleContext extends StyleContext {

		/**
		 * 新实例。
		 */
		public MyStyleContext() {
			super();
			init();
		}

		private void init() {
			addStyle(BlockStyle.NONE.getName(), null);

			Style def = addStyle(ParagraphStyle.DEFAULT.getName(), getStyle(StyleContext.DEFAULT_STYLE));

			Style heading = addStyle(ParagraphStyle.HEADING.getName(), def);
			StyleConstants.setFontFamily(heading, "SansSerif");
			StyleConstants.setBold(heading, true);
			StyleConstants.setAlignment(heading, StyleConstants.ALIGN_CENTER);
			StyleConstants.setSpaceAbove(heading, 10);
			StyleConstants.setSpaceBelow(heading, 10);
			StyleConstants.setFontSize(heading, 18);

			// Title
			Style sty = addStyle(ParagraphStyle.TITLE.getName(), heading);
			StyleConstants.setFontSize(sty, 32);

			// edition
			sty = addStyle(ParagraphStyle.EDITION.getName(), heading);
			StyleConstants.setFontSize(sty, 16);

			// author
			sty = addStyle(ParagraphStyle.AUTHOR.getName(), heading);
			StyleConstants.setItalic(sty, true);
			StyleConstants.setSpaceBelow(sty, 25);

			// subtitle
			sty = addStyle(ParagraphStyle.SUBTITLE.getName(), heading);
			StyleConstants.setSpaceBelow(sty, 35);

			// normal
			sty = addStyle(ParagraphStyle.NORMAL.getName(), def);
			StyleConstants.setLeftIndent(sty, 10);
			StyleConstants.setRightIndent(sty, 10);
			StyleConstants.setFontFamily(sty, "SansSerif");
			StyleConstants.setFontSize(sty, 14);
			StyleConstants.setSpaceAbove(sty, 4);
			StyleConstants.setSpaceBelow(sty, 4);
		}

	}

	private static final StyleContext STYLE_CONTEXT = new MyStyleContext();

	/**
	 * 向指定的文档中添加段落。
	 * 
	 * @param document
	 *            指定的文档。
	 * @param paragraph
	 *            指定的段落。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public static void addDocumentParagraph(StyledDocument document, DocumentParagraph paragraph) {
		Objects.requireNonNull(document, "入口参数 document 不能为 null。");
		Objects.requireNonNull(paragraph, "入口参数 paragraph 不能为 null。");

		try {
			Style s = null;
			for (int i = 0; i < paragraph.getDocumentBlocks().length; i++) {
				DocumentBlock documentBlock = paragraph.getDocumentBlocks()[i];
				s = documentBlock.getStyle();
				document.insertString(document.getLength(), documentBlock.getContent(), s);
			}

			// set logical style
			Style ls = paragraph.getLogicStyle();
			document.setLogicalStyle(document.getLength() - 1, ls);
			document.insertString(document.getLength(), "\n", null);
		} catch (BadLocationException e) {
			System.err.println("Internal error: " + e);
		}

	}

	/**
	 * 获取快速样式。
	 * 
	 * @param style
	 *            指定的区块样式。
	 * @return 快速样式。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public static Style quickStyle(BlockStyle style) {
		Objects.requireNonNull(style, "入口参数 style 不能为 null。");

		return new UnmodifiableStyle(STYLE_CONTEXT.getStyle(style.getName()));
	}

	/**
	 * 获取由指定图片形成的快速样式。
	 * 
	 * @param image
	 *            指定的图片。
	 * @return 由指定的图片形成的快速样式。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public static Style quickStyle(Image image) {
		Objects.requireNonNull(image, "入口参数 image 不能为 null。");

		Style s = STYLE_CONTEXT.addStyle(null, quickStyle(BlockStyle.NONE));
		StyleConstants.setIcon(s, new ImageIcon(image));
		return new UnmodifiableStyle(s);
	}

	/**
	 * 获取快速样式。
	 * 
	 * @param style
	 *            指定的段落样式。
	 * @return 快速样式。
	 * @throws NullPointerException
	 *             指定的入口参数为 <code> null </code>。
	 */
	public static Style quickStyle(ParagraphStyle style) {
		Objects.requireNonNull(style, "入口参数 style 不能为 null。");

		return new UnmodifiableStyle(STYLE_CONTEXT.getStyle(style.getName()));
	}

	// 禁止外部实例化。
	private DocumentUtil() {

	}

}
