package com.dwarfeng.tpnclib.core.model.eum;

import com.dwarfeng.dutil.basic.str.Name;

/**
 * 资源键。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public enum ResourceKey implements Name {

	/** 记录器设置 */
	LOGGER_SETTING("logger.setting"),

	/** 主程序的配置 */
	CONFIGURATION_CORE("configuration.core"),

	/** 模态配置 */
	CONFIGURATION_MODAL("configuration.modal"),

	/** 记录器多语言化的设置 */
	I18N_LOGGER_SETTING("i18n.logger.setting"),

	/** 记录器多语言化的设置 */
	I18N_LABEL_SETTING("i18n.label.setting"),

	/** 试件类型的反射设置 */
	REFLECT_PIECECATA("reflect.piececata"),

	;

	private final String name;

	private ResourceKey(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.str.Name#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

}
