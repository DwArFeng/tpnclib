package com.dwarfeng.tpnclib.core.view.gui;

import com.dwarfeng.dutil.develop.i18n.I18nHandler;
import com.dwarfeng.tpnclib.core.view.struct.GuiManager;

final class MfMenuBar_01 extends TpncLibMenuBar {
	private MfMenu_01 mfMenu_01;
	private MfMenu_02 mfMenu_02;

	/**
	 * 新实例。
	 */
	public MfMenuBar_01() {
		this(null, null);
	}

	public MfMenuBar_01(GuiManager guiManager, I18nHandler i18nHandler) {
		super(guiManager, i18nHandler);

		mfMenu_01 = new MfMenu_01(guiManager, i18nHandler);
		add(mfMenu_01);

		mfMenu_02 = new MfMenu_02(guiManager, i18nHandler);
		add(mfMenu_02);
	}

	@Override
	protected void refreshLabels() {
		// TODO Auto-generated method stub

	}

}
