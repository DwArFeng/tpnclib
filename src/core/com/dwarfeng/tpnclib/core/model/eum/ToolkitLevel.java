package com.dwarfeng.tpnclib.core.model.eum;

/**
 * 工具包的权限分级。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public enum ToolkitLevel {

	/** 最低权限，不可以调用任何方法。 */
	NONE(0),
	/** 中间权限，不可以调用不安全的方法。 */
	MIDIUM(1500),
	/** 完全权限，可以调用任意方法。 */
	FULL(3000),

	;

	private final int level;

	private ToolkitLevel(int level) {
		this.level = level;
	}

	/**
	 * 获取工具包的权限等级。
	 * 
	 * @return
	 */
	public int getLevelValue() {
		return level;
	}
}
