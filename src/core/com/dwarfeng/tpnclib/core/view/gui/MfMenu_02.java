package com.dwarfeng.tpnclib.core.view.gui;

import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.tpnclib.core.model.eum.LabelStringKey;
import com.dwarfeng.tpnclib.core.view.struct.GuiManager;

/**
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
final class MfMenu_02 extends TpncLibMenu {

	/**
	 * 新实例。
	 */
	public MfMenu_02() {
		this(null, null);
	}

	public MfMenu_02(GuiManager guiManager, I18nHandler i18nHandler) {
		super(guiManager, i18nHandler);

		setText(label(LabelStringKey.MFMENU_02_1));
		setMnemonic('I');
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshLabels() {
		setText(label(LabelStringKey.MFMENU_02_1));

	}

}
