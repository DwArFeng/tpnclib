package com.dwarfeng.tpnclib.core.control;

import com.dwarfeng.dutil.develop.backgr.Task;
import com.dwarfeng.tpnclib.core.model.eum.LoggerStringKey;

class ThrowableReportTask extends TpncLibTask {

	private final Task task;

	public ThrowableReportTask(TpncLib tpncLib, Task task) {
		super(tpncLib);
		this.task = task;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void todo() throws Exception {
		if (task.getThrowable() != null) {
			warn(LoggerStringKey.TASK_THROABLEREPORT_0);
			formatWarn(LoggerStringKey.TASK_THROABLEREPORT_1, task.toString(), task.getClass().toString());
			warn(LoggerStringKey.TASK_THROABLEREPORT_2, task.getThrowable());
		}
	}

}
