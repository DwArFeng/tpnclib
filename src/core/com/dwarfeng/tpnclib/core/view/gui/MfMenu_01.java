package com.dwarfeng.tpnclib.core.view.gui;

import javax.swing.JMenuItem;

import com.dwarfeng.dutil.basic.gui.swing.JMenuItemAction;
import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.tpnclib.core.model.eum.LabelStringKey;
import com.dwarfeng.tpnclib.core.view.struct.GuiManager;

/**
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
final class MfMenu_01 extends TpncLibMenu {

	private final JMenuItem mi_01;

	/**
	 * 主程序。
	 */
	public MfMenu_01() {
		this(null, null);
	}

	public MfMenu_01(GuiManager guiManager, I18nHandler i18nHandler) {
		super(guiManager, i18nHandler);

		setText(label(LabelStringKey.MFMENU_01_1));
		setMnemonic('P');

		mi_01 = add(new JMenuItemAction.Builder().name(label(LabelStringKey.MFMENU_01_2)).mnemonic('S').listener(e -> {

		}).build());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshLabels() {
		setText(label(LabelStringKey.MFMENU_01_1));

		mi_01.setName(label(LabelStringKey.MFMENU_01_2));

	}

}
