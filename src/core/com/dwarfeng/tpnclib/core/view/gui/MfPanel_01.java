package com.dwarfeng.tpnclib.core.view.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.dwarfeng.dutil.basic.cna.model.SyncListModel;
import com.dwarfeng.dutil.basic.cna.model.SyncReferenceModel;
import com.dwarfeng.dutil.basic.cna.model.obv.ListAdapter;
import com.dwarfeng.dutil.basic.cna.model.obv.ListObverser;
import com.dwarfeng.dutil.basic.cna.model.obv.ReferenceAdapter;
import com.dwarfeng.dutil.basic.cna.model.obv.ReferenceObverser;
import com.dwarfeng.dutil.basic.gui.awt.CommonIconLib;
import com.dwarfeng.dutil.basic.gui.awt.ImageSize;
import com.dwarfeng.dutil.basic.gui.awt.ImageUtil;
import com.dwarfeng.dutil.basic.gui.swing.MuaListModel;
import com.dwarfeng.dutil.basic.gui.swing.SwingUtil;
import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.tpnclib.core.model.struct.PieceCata;
import com.dwarfeng.tpnclib.core.view.struct.GuiManager;
import com.dwarfeng.tpnclib.core.view.struct.GuiManager.ExecType;

final class MfPanel_01 extends TpncLibPanel {

	private static final String DF_ANCHORPIECECATAMODEL_FIRESET = "A";
	private static final String DF_ANCHORPIECECATAMODEL_FIRECLEARED = "B";

	private final JList<PieceCata> list;

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

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fireRemoved(int index, PieceCata element) {
			SwingUtil.invokeInEventQueue(() -> {
				listModel.remove(index);
			});
		}

	};
	private final ReferenceObverser<PieceCata> anchorPieceCataObverser = new ReferenceAdapter<PieceCata>() {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fireSet(PieceCata oldValue, PieceCata newValue) {
			SwingUtil.invokeInEventQueue(() -> {
				if (checkDuplexingForecast(new Object[] { DF_ANCHORPIECECATAMODEL_FIRESET, oldValue, newValue })) {
					return;
				}

				listSelectionModel.setValueIsAdjusting(true);
				if (Objects.nonNull(newValue)) {
					int index = listModel.indexOf(newValue);
					listSelectionModel.setSelectionInterval(index, index);
				} else {
					listSelectionModel.clearSelection();
				}
				listSelectionModel.setValueIsAdjusting(false);
			});
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fireCleared() {
			SwingUtil.invokeInEventQueue(() -> {
				if (checkDuplexingForecast(new Object[] { DF_ANCHORPIECECATAMODEL_FIRECLEARED })) {
					return;
				}
				listSelectionModel.setValueIsAdjusting(true);
				listSelectionModel.clearSelection();
				listSelectionModel.setValueIsAdjusting(false);
			});
		}

	};

	/** 双工通信预测 */
	private final Queue<Object[]> duplexingForecast = new ArrayDeque<>();

	/**
	 * 新实例。
	 */
	public MfPanel_01() {
		this(null, null, null, null);
	}

	public MfPanel_01(GuiManager guiManager, I18nHandler i18nHandler, SyncListModel<PieceCata> pieceCataModel,
			SyncReferenceModel<PieceCata> anchorPieceCataModel) {
		super(guiManager, i18nHandler);

		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		list = new JList<PieceCata>();
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (Objects.isNull(MfPanel_01.this.anchorPieceCataModel.get())) {
					return;
				}

				if (e.getValueIsAdjusting()) {
					return;
				}
				PieceCata oldValue = MfPanel_01.this.anchorPieceCataModel.get();
				PieceCata newValue = listModel.get(e.getLastIndex());
				duplexingForecast.offer(new Object[] { DF_ANCHORPIECECATAMODEL_FIRESET, oldValue, newValue });

				MfPanel_01.this.guiManager.setAnchorPieceCata(newValue, ExecType.CONCURRENT);
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(listModel);
		list.setSelectionModel(listSelectionModel);
		list.setCellRenderer(listRenderer);
		scrollPane.setViewportView(list);

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
	 * @return the anchorPieceCataModel
	 */
	public SyncReferenceModel<PieceCata> getAnchorPieceCataModel() {
		return anchorPieceCataModel;
	}

	/**
	 * @return the pieceCataModel
	 */
	public SyncListModel<PieceCata> getPieceCataModel() {
		return pieceCataModel;
	}

	/**
	 * @param anchorPieceCataModel
	 *            the anchorPieceCataModel to set
	 */
	public void setAnchorPieceCataModel(SyncReferenceModel<PieceCata> anchorPieceCataModel) {
		if (Objects.nonNull(this.anchorPieceCataModel)) {
			this.anchorPieceCataModel.removeObverser(anchorPieceCataObverser);
		}

		if (Objects.nonNull(anchorPieceCataModel)) {
			anchorPieceCataModel.addObverser(anchorPieceCataObverser);
		}

		this.anchorPieceCataModel = anchorPieceCataModel;
		syncPieceCataModel();
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

	@Override
	protected void refreshLabels() {
		// TODO Auto-generated method stub

	}

	private void syncAnchorPieceCataModel() {
		listSelectionModel.setValueIsAdjusting(true);
		listSelectionModel.clearSelection();
		listSelectionModel.setValueIsAdjusting(false);

		if (Objects.isNull(anchorPieceCataModel)) {
			return;
		}

		anchorPieceCataModel.getLock().readLock().lock();
		try {
			listSelectionModel.setValueIsAdjusting(true);
			if (Objects.nonNull(anchorPieceCataModel.get())) {
				int index = listModel.indexOf(anchorPieceCataModel.get());
				listSelectionModel.setSelectionInterval(index, index);
			}
			listSelectionModel.setValueIsAdjusting(false);
		} finally {
			anchorPieceCataModel.getLock().readLock().unlock();
		}
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

	private boolean checkDuplexingForecast(Object[] objs) {
		if (duplexingForecast.isEmpty()) {
			return false;
		}

		if (Arrays.equals(duplexingForecast.peek(), objs)) {
			duplexingForecast.poll();
			return true;
		} else {
			syncAnchorPieceCataModel();
			duplexingForecast.clear();
			return false;
		}
	}

}
