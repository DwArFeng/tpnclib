package com.dwarfeng.tpnclib.core.model.eum;

import com.dwarfeng.dutil.develop.cfg.ConfigChecker;
import com.dwarfeng.dutil.develop.cfg.ConfigFirmProps;
import com.dwarfeng.dutil.develop.cfg.ConfigKey;
import com.dwarfeng.dutil.develop.cfg.DefaultConfigFirmProps;
import com.dwarfeng.dutil.develop.cfg.checker.BooleanConfigChecker;
import com.dwarfeng.dutil.develop.cfg.checker.IntegerConfigChecker;
import com.dwarfeng.dutil.develop.cfg.parser.BooleanValueParser;
import com.dwarfeng.dutil.develop.cfg.parser.IntegerValueParser;
import com.dwarfeng.dutil.develop.cfg.struct.ExconfigEntry;
import com.dwarfeng.dutil.develop.cfg.struct.ValueParser;

/**
 * 模态配置。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public enum ModalConfiguration implements ExconfigEntry {

	/** 主界面的西方面板是否可见。 */
	GUI_VISIBLE_MAINFRAME_WEST("gui.visible.mainframe.west", "true", new BooleanConfigChecker(), new BooleanValueParser()),
	/** 主界面的东方面板是否可见。 */
	GUI_VISIBLE_MAINFRAME_EAST("gui.visible.mainframe.east", "true", new BooleanConfigChecker(), new BooleanValueParser()),
	/** 主界面的被方面板是否可见。 */
	GUI_VISIBLE_MAINFRAME_NORTH("gui.visible.mainframe.north", "true", new BooleanConfigChecker(), new BooleanValueParser()),
	/** 主界面的南方面板是否可见。 */
	GUI_VISIBLE_MAINFRAME_SOUTH("gui.visible.mainframe.south", "true", new BooleanConfigChecker(), new BooleanValueParser()),

	/** 主界面是否最大化。 */
	GUI_MAXIMUM_MAINFRAME("gui.maximum.mainframe", "false", new BooleanConfigChecker(), new BooleanValueParser()),

	/** 主界面的西方面板的尺寸。 */
	GUI_SIZE_MAINFRAME_WEST("gui.size.mainframe.west", "150", new IntegerConfigChecker(0, Integer.MAX_VALUE), new IntegerValueParser()),
	/** 主界面的南方面板的尺寸。 */
	GUI_SIZE_MAINFRAME_SOUTH("gui.size.mainframe.south", "80", new IntegerConfigChecker(0, Integer.MAX_VALUE), new IntegerValueParser()),
	/** 主界面的东方面板的尺寸。 */
	GUI_SIZE_MAINFRAME_EAST("gui.size.mainframe.east", "150", new IntegerConfigChecker(0, Integer.MAX_VALUE), new IntegerValueParser()),

	/** 主界面的宽度。 */
	GUI_SIZE_MAINFRAME_WIDTH("gui.size.mainframe.width", "800", new IntegerConfigChecker(0, Integer.MAX_VALUE), new IntegerValueParser()),
	/** 主界面的高度。 */
	GUI_SIZE_MAINFRAME_HEIGHT("gui.size.mainframe.height", "600", new IntegerConfigChecker(0, Integer.MAX_VALUE), new IntegerValueParser()),

	/** 主界面的扩展状态。 */
	GUI_STATE_MAINFRAME_EXTENDED("gui.state.mainframe.extended", "0", new IntegerConfigChecker(), new IntegerValueParser()),

	// ------------------------------------------------------------------------------------------------------------------------------------------------

	/** NC设定界面的西方面板是否可见。 */
	GUI_VISIBLE_NCSETTINGSFRAME_WEST("gui.visible.ncsettingsframe.west", "true", new BooleanConfigChecker(), new BooleanValueParser()),
	/** NC设定界面的东方面板是否可见。 */
	GUI_VISIBLE_NCSETTINGSFRAME_EAST("gui.visible.ncsettingsframe.east", "true", new BooleanConfigChecker(), new BooleanValueParser()),
	/** NC设定界面的被方面板是否可见。 */
	GUI_VISIBLE_NCSETTINGSFRAME_NORTH("gui.visible.ncsettingsframe.north", "true", new BooleanConfigChecker(), new BooleanValueParser()),
	/** NC设定界面的南方面板是否可见。 */
	GUI_VISIBLE_NCSETTINGSFRAME_SOUTH("gui.visible.ncsettingsframe.south", "true", new BooleanConfigChecker(), new BooleanValueParser()),

	/** NC设定界面是否最大化。 */
	GUI_MAXIMUM_NCSETTINGSFRAME("gui.maximum.ncsettingsframe", "false", new BooleanConfigChecker(), new BooleanValueParser()),

	/** NC设定界面的西方面板的尺寸。 */
	GUI_SIZE_NCSETTINGSFRAME_WEST("gui.size.ncsettingsframe.west", "150", new IntegerConfigChecker(0, Integer.MAX_VALUE), new IntegerValueParser()),
	/** NC设定界面的南方面板的尺寸。 */
	GUI_SIZE_NCSETTINGSFRAME_SOUTH("gui.size.ncsettingsframe.south", "80", new IntegerConfigChecker(0, Integer.MAX_VALUE), new IntegerValueParser()),
	/** NC设定界面的东方面板的尺寸。 */
	GUI_SIZE_NCSETTINGSFRAME_EAST("gui.size.ncsettingsframe.east", "150", new IntegerConfigChecker(0, Integer.MAX_VALUE), new IntegerValueParser()),

	/** NC设定界面的宽度。 */
	GUI_SIZE_NCSETTINGSFRAME_WIDTH("gui.size.ncsettingsframe.width", "800", new IntegerConfigChecker(0, Integer.MAX_VALUE), new IntegerValueParser()),
	/** NC设定界面的高度。 */
	GUI_SIZE_NCSETTINGSFRAME_HEIGHT("gui.size.ncsettingsframe.height", "600", new IntegerConfigChecker(0, Integer.MAX_VALUE), new IntegerValueParser()),

	/** NC设定界面的扩展状态。 */
	GUI_STATE_NCSETTINGSFRAME_EXTENDED("gui.state.ncsettingsframe.extended", "0", new IntegerConfigChecker(), new IntegerValueParser()),

	;

	private final String name;
	private final String defaultValue;
	private final ConfigChecker configChecker;
	private final ValueParser valueParser;

	private ModalConfiguration(String name, String defaultValue, ConfigChecker configChecker, ValueParser valueParser) {
		this.name = name;
		this.defaultValue = defaultValue;
		this.configChecker = configChecker;
		this.valueParser = valueParser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConfigKey getConfigKey() {
		return new ConfigKey(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConfigFirmProps getConfigFirmProps() {
		return new DefaultConfigFirmProps(configChecker, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ValueParser getValueParser() {
		return valueParser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCurrentValue() {
		return defaultValue;
	}

}
