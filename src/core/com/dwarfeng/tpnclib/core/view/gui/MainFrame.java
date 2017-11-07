package com.dwarfeng.tpnclib.core.view.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.dwarfeng.dutil.basic.cna.model.SyncListModel;
import com.dwarfeng.dutil.basic.cna.model.SyncReferenceModel;
import com.dwarfeng.dutil.basic.cna.model.obv.ListAdapter;
import com.dwarfeng.dutil.basic.cna.model.obv.ListObverser;
import com.dwarfeng.dutil.basic.cna.model.obv.ReferenceAdapter;
import com.dwarfeng.dutil.basic.cna.model.obv.ReferenceObverser;
import com.dwarfeng.dutil.basic.gui.awt.CommonIconLib;
import com.dwarfeng.dutil.basic.gui.awt.ImageSize;
import com.dwarfeng.dutil.basic.gui.awt.ImageUtil;
import com.dwarfeng.dutil.basic.gui.swing.JAdjustableBorderPanel;
import com.dwarfeng.dutil.basic.gui.swing.MuaListModel;
import com.dwarfeng.dutil.basic.gui.swing.SwingUtil;
import com.dwarfeng.dutil.basic.str.Name;
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
	private final JList<PieceCata> list;
	private final JLineWrapableTextPane textPane;
	private final JComboBox<Name> comboBox;

	private SyncListModel<PieceCata> pieceCataModel;
	private SyncReferenceModel<PieceCata> anchorPieceCataModel;

	private final MuaListModel<PieceCata> listModel = new MuaListModel<>();
	private final ListSelectionModel listSelectionModel = new DefaultListSelectionModel();

	private final ListCellRenderer<Object> listRenderer = new DefaultListCellRenderer() {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			PieceCata pieceCata = (PieceCata) value;

			setText(pieceCata.getName());

			Image image = pieceCata.getIconImage();
			if (Objects.isNull(image)) {
				image = ImageUtil.getInternalImage(CommonIconLib.UNKNOWN_BLUE);
			}

			setIcon(new ImageIcon(ImageUtil.scaleImage(image, ImageSize.ICON_MEDIUM)));

			return this;
		}

	};

	private final ListObverser<PieceCata> pieceCataObverser = new ListAdapter<PieceCata>() {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fireAdded(int index, PieceCata element) {
			SwingUtil.invokeInEventQueue(() -> {
				listModel.add(index, element);
			});
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fireRemoved(int index, PieceCata element) {
			SwingUtil.invokeInEventQueue(() -> {
				listModel.remove(index);
			});
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fireChanged(int index, PieceCata oldElement, PieceCata newElement) {
			SwingUtil.invokeInEventQueue(() -> {
				listModel.set(index, newElement);
			});
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fireCleared() {
			SwingUtil.invokeInEventQueue(() -> {
				listModel.clear();
			});
		}

	};
	private final ReferenceObverser<PieceCata> anchorPieceCataObverser = new ReferenceAdapter<PieceCata>() {

	};

	/**
	 * 新实例。
	 */
	public MainFrame() {
		this(null, null, null, null);
	}

	/**
	 * 
	 * @param guiManager
	 * @param i18nHandler
	 * @param pieceCataModel
	 * @param anchorPieceCataModel
	 */
	public MainFrame(GuiManager guiManager, I18nHandler i18nHandler, SyncListModel<PieceCata> pieceCataModel,
			SyncReferenceModel<PieceCata> anchorPieceCataModel) {
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

		JScrollPane scrollPane = new JScrollPane();
		adjustableBorderPanel.add(scrollPane, BorderLayout.WEST);

		list = new JList<PieceCata>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(listModel);
		list.setSelectionModel(listSelectionModel);
		list.setCellRenderer(listRenderer);
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

		textPane = new JLineWrapableTextPane();
		textPane.setEditable(false);
		scrollPane_1.setViewportView(textPane);

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

		if (Objects.nonNull(pieceCataModel)) {
			pieceCataModel.addObverser(pieceCataObverser);
		}

		if (Objects.nonNull(anchorPieceCataModel)) {
			anchorPieceCataModel.addObverser(anchorPieceCataObverser);
		}

		this.pieceCataModel = pieceCataModel;
		this.anchorPieceCataModel = anchorPieceCataModel;

		syncPieceCataModel();
		syncAnchorPieceCataModel();

	}

	private void syncAnchorPieceCataModel() {
		// TODO Auto-generated method stub
	}

	private void syncPieceCataModel() {
		listModel.clear();

		if (Objects.isNull(pieceCataModel)) {
			return;
		}

		pieceCataModel.getLock().readLock().lock();
		try {
			for (PieceCata pieceCata : pieceCataModel) {
				listModel.add(pieceCata);
			}
		} finally {
			pieceCataModel.getLock().readLock().unlock();
		}

	}

	/**
	 * @return the pieceCataModel
	 */
	public SyncListModel<PieceCata> getPieceCataModel() {
		return pieceCataModel;
	}

	/**
	 * @param pieceCataModel
	 *            the pieceCataModel to set
	 */
	public void setPieceCataModel(SyncListModel<PieceCata> pieceCataModel) {
		if (Objects.nonNull(this.pieceCataModel)) {
			this.pieceCataModel.removeObverser(pieceCataObverser);
		}

		if (Objects.nonNull(pieceCataModel)) {
			pieceCataModel.addObverser(pieceCataObverser);
		}

		this.pieceCataModel = pieceCataModel;
		syncPieceCataModel();
	}

	/**
	 * @return the anchorPieceCataModel
	 */
	public SyncReferenceModel<PieceCata> getAnchorPieceCataModel() {
		return anchorPieceCataModel;
	}

	/**
	 * @param anchorPieceCataModel
	 *            the anchorPieceCataModel to set
	 */
	public void setAnchorPieceCataModel(SyncReferenceModel<PieceCata> anchorPieceCataModel) {

		this.anchorPieceCataModel = anchorPieceCataModel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		if (Objects.nonNull(pieceCataModel)) {
			pieceCataModel.removeObverser(pieceCataObverser);
		}
		if (Objects.nonNull(anchorPieceCataModel)) {
			anchorPieceCataModel.removeObverser(anchorPieceCataObverser);
		}

		super.dispose();
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
