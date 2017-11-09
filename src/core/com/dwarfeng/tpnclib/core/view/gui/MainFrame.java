package com.dwarfeng.tpnclib.core.view.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledDocument;

import com.dwarfeng.dutil.basic.cna.model.SyncListModel;
import com.dwarfeng.dutil.basic.cna.model.SyncReferenceModel;
import com.dwarfeng.dutil.basic.gui.swing.JAdjustableBorderPanel;
import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.tpnclib.core.model.eum.LabelStringKey;
import com.dwarfeng.tpnclib.core.model.struct.PieceCata;
import com.dwarfeng.tpnclib.core.view.struct.GuiManager;
import com.dwarfeng.tpnclib.core.view.struct.GuiManager.ExecType;

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
	private final MfPanel_01 mfPanel_01;
	private final JPanel panel_1;
	private final MfPanel_02 mfPanel_02;
	private final MfPanel_03 mfPanel_03;

	private SyncListModel<PieceCata> pieceCataModel;
	private SyncReferenceModel<PieceCata> anchorPieceCataModel;
	private SyncReferenceModel<StyledDocument> instrDocModel;

	/**
	 * 新实例。
	 */
	public MainFrame() {
		this(null, null, null, null, null);
	}

	/**
	 * 
	 * @param guiManager
	 * @param i18nHandler
	 * @param pieceCataModel
	 * @param anchorPieceCataModel
	 * @param instrDocModel
	 */
	public MainFrame(GuiManager guiManager, I18nHandler i18nHandler, SyncListModel<PieceCata> pieceCataModel,
			SyncReferenceModel<PieceCata> anchorPieceCataModel, SyncReferenceModel<StyledDocument> instrDocModel) {
		super(guiManager, i18nHandler);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				guiManager.exit(ExecType.CONCURRENT);
			}
		});

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

		mfPanel_01 = new MfPanel_01(guiManager, i18nHandler, pieceCataModel, anchorPieceCataModel);
		adjustableBorderPanel.add(mfPanel_01, BorderLayout.WEST);

		panel_1 = new JPanel();
		adjustableBorderPanel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		mfPanel_02 = new MfPanel_02(guiManager, i18nHandler);
		panel_1.add(mfPanel_02, BorderLayout.NORTH);

		mfPanel_03 = new MfPanel_03(guiManager, i18nHandler, instrDocModel);
		panel_1.add(mfPanel_03, BorderLayout.CENTER);

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

		this.pieceCataModel = pieceCataModel;
		this.anchorPieceCataModel = anchorPieceCataModel;
		this.instrDocModel = instrDocModel;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {

		super.dispose();
	}

	/**
	 * @return the anchorPieceCataModel
	 */
	public SyncReferenceModel<PieceCata> getAnchorPieceCataModel() {
		return anchorPieceCataModel;
	}

	/**
	 * @return the instrDocModel
	 */
	public SyncReferenceModel<StyledDocument> getInstrDocModel() {
		return instrDocModel;
	}

	/**
	 * @return the pieceCataModel
	 */
	public SyncListModel<PieceCata> getPieceCataModel() {
		return pieceCataModel;
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
	 * @param anchorPieceCataModel
	 *            the anchorPieceCataModel to set
	 */
	public void setAnchorPieceCataModel(SyncReferenceModel<PieceCata> anchorPieceCataModel) {
		this.mfPanel_01.setAnchorPieceCataModel(anchorPieceCataModel);

		this.anchorPieceCataModel = anchorPieceCataModel;
	}

	/**
	 * @param instrDocModel
	 *            the instrDocModel to set
	 */
	public void setInstrDocModel(SyncReferenceModel<StyledDocument> instrDocModel) {
		this.mfPanel_03.setInstrDocModel(instrDocModel);

		this.instrDocModel = instrDocModel;
	}

	/**
	 * @param pieceCataModel
	 *            the pieceCataModel to set
	 */
	public void setPieceCataModel(SyncListModel<PieceCata> pieceCataModel) {
		this.mfPanel_01.setPieceCataModel(pieceCataModel);

		this.pieceCataModel = pieceCataModel;
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
