package com.dwarfeng.tpnclib.core.view.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * NC设置窗口。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class NcSettingsFrame extends JFrame {

	private final JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public NcSettingsFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
