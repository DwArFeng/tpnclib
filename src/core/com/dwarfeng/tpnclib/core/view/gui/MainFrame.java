package com.dwarfeng.tpnclib.core.view.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.dwarfeng.dutil.basic.gui.swing.JAdjustableBorderPanel;
import com.dwarfeng.dutil.basic.str.Name;
import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.tpnclib.core.model.eum.LabelStringKey;
import com.dwarfeng.tpnclib.core.model.struct.PieceCata;
import com.dwarfeng.tpnclib.core.view.struct.GuiManager;

/**
 * 主窗口。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class MainFrame extends TpncLibFrame {

	private final JPanel contentPane;
	private final MfMenuBar_01 mfMenuBar_01;
	private final JAdjustableBorderPanel adjustableBorderPanel;
	private final JList<PieceCata> list;
	private final JLineWrapableTextPane lineWrapableTextPane;
	private final JComboBox<Name> comboBox;

	/**
	 * 新实例。
	 */
	public MainFrame() {
		this(null, null);
	}

	/**
	 * 
	 * @param guiManager
	 * @param i18nHandler
	 */
	public MainFrame(GuiManager guiManager, I18nHandler i18nHandler) {
		super(guiManager, i18nHandler);

		setTitle(label(LabelStringKey.MAINFRAME_1));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 724, 560);

		mfMenuBar_01 = new MfMenuBar_01(guiManager, i18nHandler);
		setJMenuBar(mfMenuBar_01);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		adjustableBorderPanel = new JAdjustableBorderPanel();
		adjustableBorderPanel.setSeperatorThickness(5);
		adjustableBorderPanel.setWestEnabled(true);
		contentPane.add(adjustableBorderPanel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		adjustableBorderPanel.add(scrollPane, BorderLayout.WEST);

		list = new JList<PieceCata>();
		scrollPane.setViewportView(list);

		JPanel panel_1 = new JPanel();
		adjustableBorderPanel.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 8, 0 };
		gbl_panel_1.rowHeights = new int[] { 23, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel_1.add(btnNewButton, gbc_btnNewButton);

		comboBox = new JComboBox<Name>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel_1.add(comboBox, gbc_comboBox);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		panel_1.add(scrollPane_1, gbc_scrollPane_1);

		lineWrapableTextPane = new JLineWrapableTextPane();
		lineWrapableTextPane.setEditable(false);
		scrollPane_1.setViewportView(lineWrapableTextPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

	}

	/**
	 * 获取西方面板的首选大小。
	 * 
	 * @return 西方面板的首选大小。
	 */
	public int getWestPreferredValue() {
		return adjustableBorderPanel.getWestPreferredValue();
	}

	/**
	 * 设置西方面板的首选大小。
	 * 
	 * @param value
	 *            指定的首选大小
	 */
	public void setWestPreferredValue(int value) {
		adjustableBorderPanel.setWestPreferredValue(value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshLabels() {
		setTitle(label(LabelStringKey.MAINFRAME_1));

	}
}
