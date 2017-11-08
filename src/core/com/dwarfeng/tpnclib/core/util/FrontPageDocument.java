package com.dwarfeng.tpnclib.core.util;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.DefaultStyledDocument;

import com.dwarfeng.dutil.basic.gui.awt.ImageUtil;
import com.dwarfeng.tpnclib.core.control.TpncLib;
import com.dwarfeng.tpnclib.core.model.eum.ImageKey;
import com.dwarfeng.tpnclib.core.util.DocumentUtil.BlockStyle;
import com.dwarfeng.tpnclib.core.util.DocumentUtil.ParagraphStyle;
import com.dwarfeng.tpnclib.core.view.struct.DocumentBlock;
import com.dwarfeng.tpnclib.core.view.struct.DocumentParagraph;
import com.dwarfeng.tpnclib.test.DocumentTester;

/**
 * 首页文档。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
final class FrontPageDocument extends DefaultStyledDocument {

	/**
	 * 测试该文档。
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					DocumentTester frame = new DocumentTester(new FrontPageDocument());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrontPageDocument() {
		super();
		init();
	}

	private void init() {
		DocumentUtil.addDocumentParagraph(this, new DocumentParagraph(DocumentUtil.quickStyle(ParagraphStyle.TITLE),
				new DocumentBlock[] { new DocumentBlock(DocumentUtil.quickStyle(BlockStyle.NONE), "试切程序库") }));
		DocumentUtil.addDocumentParagraph(this,
				new DocumentParagraph(DocumentUtil.quickStyle(ParagraphStyle.HEADING),
						new DocumentBlock[] { new DocumentBlock(
								DocumentUtil.quickStyle(ImageUtil.getInternalImage(ImageKey.FROUNTPAGE_DOCUMENT_TITLE)),
								" ") }));
		DocumentUtil.addDocumentParagraph(this,
				new DocumentParagraph(DocumentUtil.quickStyle(ParagraphStyle.AUTHOR), new DocumentBlock[] {
						new DocumentBlock(DocumentUtil.quickStyle(BlockStyle.NONE), "济南二机床 - 数控机床公司工艺技术室") }));
		DocumentUtil.addDocumentParagraph(this,
				new DocumentParagraph(DocumentUtil.quickStyle(ParagraphStyle.EDITION), new DocumentBlock[] {
						new DocumentBlock(DocumentUtil.quickStyle(BlockStyle.NONE), TpncLib.VERSION.toString()) }));
		DocumentUtil.addDocumentParagraph(this,
				new DocumentParagraph(DocumentUtil.quickStyle(ParagraphStyle.NORMAL), new DocumentBlock[] {
						new DocumentBlock(DocumentUtil.quickStyle(BlockStyle.NONE), "该程序总结整理了多种试切程序，可以为机床试切提供标准化程序。") }));

	}

}
