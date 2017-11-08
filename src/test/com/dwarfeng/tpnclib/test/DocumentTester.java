package com.dwarfeng.tpnclib.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledDocument;

/**
 * 文档测试器。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class DocumentTester extends JFrame {

	private static final long serialVersionUID = 6315599784264715448L;
	
	private JPanel contentPane;

	/**
	 * 新实例。
	 */
	public DocumentTester() {
		this(null);
	}

	/**
	 * 
	 * @param docuement
	 */
	public DocumentTester(StyledDocument docuement) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JTextPane lineWrapableTextPane = new JTextPane();
		lineWrapableTextPane.setDocument(docuement);
		lineWrapableTextPane.setEditable(false);
		scrollPane.setViewportView(lineWrapableTextPane);
	}

}
