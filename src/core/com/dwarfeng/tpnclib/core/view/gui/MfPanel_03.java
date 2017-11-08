package com.dwarfeng.tpnclib.core.view.gui;

import java.awt.BorderLayout;
import java.util.Objects;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import com.dwarfeng.dutil.basic.cna.model.SyncReferenceModel;
import com.dwarfeng.dutil.basic.cna.model.obv.ReferenceAdapter;
import com.dwarfeng.dutil.basic.cna.model.obv.ReferenceObverser;
import com.dwarfeng.dutil.basic.gui.swing.SwingUtil;
import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.tpnclib.core.view.struct.GuiManager;

public class MfPanel_03 extends TpncLibPanel {

	private final JTextPane textPane;

	private SyncReferenceModel<StyledDocument> instrDocModel;

	private final ReferenceObverser<StyledDocument> instrDocObverser = new ReferenceAdapter<StyledDocument>() {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fireSet(StyledDocument oldValue, StyledDocument newValue) {
			SwingUtil.invokeInEventQueue(() -> {
				textPane.setDocument(newValue);
			});
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fireCleared() {
			SwingUtil.invokeInEventQueue(() -> {
				textPane.setDocument(null);
			});
		}

	};

	/**
	 * 新实例。
	 */
	public MfPanel_03() {
		this(null, null, null);
	}

	/**
	 * 
	 * @param guiManager
	 * @param i18nHandler
	 * @param instrDocModel
	 */
	public MfPanel_03(GuiManager guiManager, I18nHandler i18nHandler,
			SyncReferenceModel<StyledDocument> instrDocModel) {
		super(guiManager, i18nHandler);

		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);

		if (Objects.nonNull(instrDocModel)) {
			instrDocModel.addObverser(instrDocObverser);
		}

		this.instrDocModel = instrDocModel;

		syncInstrDocModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		if (Objects.nonNull(this.instrDocModel)) {
			this.instrDocModel.removeObverser(instrDocObverser);
		}

		super.dispose();
	}

	private void syncInstrDocModel() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void refreshLabels() {
		// TODO Auto-generated method stub

	}

}
