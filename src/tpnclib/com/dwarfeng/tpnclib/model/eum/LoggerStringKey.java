package com.dwarfeng.tpnclib.model.eum;

import com.dwarfeng.dutil.basic.str.DefaultName;
import com.dwarfeng.dutil.basic.str.Name;

/**
 * 记录器文本键。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public enum LoggerStringKey implements Name {

	TASK_THROABLEREPORT_0(new DefaultName("task.throwablereport.0")), //
	TASK_THROABLEREPORT_1(new DefaultName("task.throwablereport.1")), //
	TASK_THROABLEREPORT_2(new DefaultName("task.throwablereport.2")), //

	;

	private Name name;

	private LoggerStringKey(Name name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.str.Name#getName()
	 */
	@Override
	public String getName() {
		return name.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return name.getName();
	}

}
