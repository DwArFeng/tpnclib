package com.dwarfeng.tpnclib.core.view.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.tpnclib.core.view.struct.GuiManager;

final class MfPanel_02 extends TpncLibPanel {

	/**
	 * 新实例。
	 */
	public MfPanel_02() {
		this(null, null);
	}

	/**
	 * 
	 * @param guiManager
	 * @param i18nHandler
	 */
	public MfPanel_02(GuiManager guiManager, I18nHandler i18nHandler) {
		super(guiManager, i18nHandler);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);

		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void refreshLabels() {
		// TODO Auto-generated method stub

	}

}
