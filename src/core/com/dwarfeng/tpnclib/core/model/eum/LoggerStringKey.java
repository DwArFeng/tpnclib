package com.dwarfeng.tpnclib.core.model.eum;

import com.dwarfeng.dutil.basic.str.DefaultName;
import com.dwarfeng.dutil.basic.str.Name;

/**
 * 记录器文本键。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public enum LoggerStringKey implements Name {

	TASK_POSE_0(new DefaultName("task.pose.0")), //
	TASK_POSE_1(new DefaultName("task.pose.1")), //
	TASK_POSE_2(new DefaultName("task.pose.2")), //
	TASK_POSE_3(new DefaultName("task.pose.3")), //
	TASK_POSE_4(new DefaultName("task.pose.4")), //
	TASK_POSE_5(new DefaultName("task.pose.5")), //
	TASK_POSE_6(new DefaultName("task.pose.6")), //
	TASK_POSE_7(new DefaultName("task.pose.7")), //
	TASK_POSE_8(new DefaultName("task.pose.8")), //
	TASK_POSE_9(new DefaultName("task.pose.9")), //
	TASK_POSE_10(new DefaultName("task.pose.10")), //
	TASK_POSE_11(new DefaultName("task.pose.11")), //
	TASK_POSE_12(new DefaultName("task.pose.12")), //
	TASK_POSE_13(new DefaultName("task.pose.13")), //
	TASK_POSE_14(new DefaultName("task.pose.14")), //
	TASK_POSE_15(new DefaultName("task.pose.15")), //
	TASK_POSE_16(new DefaultName("task.pose.16")), //
	TASK_POSE_17(new DefaultName("task.pose.17")), //
	TASK_POSE_18(new DefaultName("task.pose.18")), //
	TASK_POSE_19(new DefaultName("task.pose.19")), //
	TASK_POSE_20(new DefaultName("task.pose.20")), //
	TASK_POSE_21(new DefaultName("task.pose.21")), //
	TASK_POSE_22(new DefaultName("task.pose.22")), //
	TASK_POSE_24(new DefaultName("task.pose.24")), //
	TASK_POSE_25(new DefaultName("task.pose.25")), //
	TASK_POSE_26(new DefaultName("task.pose.26")), //

	TASK_DISPOSE_0(new DefaultName("task.dispose.0")), //
	TASK_DISPOSE_1(new DefaultName("task.dispose.1")), //
	TASK_DISPOSE_2(new DefaultName("task.dispose.2")), //
	TASK_DISPOSE_3(new DefaultName("task.dispose.3")), //
	TASK_DISPOSE_4(new DefaultName("task.dispose.4")), //
	TASK_DISPOSE_5(new DefaultName("task.dispose.5")), //
	TASK_DISPOSE_6(new DefaultName("task.dispose.6")), //
	TASK_DISPOSE_7(new DefaultName("task.dispose.7")), //
	TASK_DISPOSE_8(new DefaultName("task.dispose.8")), //
	TASK_DISPOSE_9(new DefaultName("task.dispose.9")), //

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
