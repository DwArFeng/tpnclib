package com.dwarfeng.tpnclib.control;

import com.dwarfeng.dutil.develop.backgr.Task;
import com.dwarfeng.dutil.develop.backgr.obv.BackgroundObverser;
import com.dwarfeng.tpnclib.model.struct.Toolkit.BackgroundType;

class BackgroundObverserImpl extends TpncLibObverser implements BackgroundObverser {

	public BackgroundObverserImpl(TpncLib tpncLib) {
		super(tpncLib);
	}

	@Override
	public void fireTaskSubmitted(Task task) {
	}

	@Override
	public void fireTaskStarted(Task task) {
	}

	@Override
	public void fireTaskFinished(Task task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fireTaskRemoved(Task task) {
		if (task instanceof ThrowableReportTask)
			return;
		if (task.getThrowable() == null)
			return;

		tpncLib.getToolkit().submitTask(new ThrowableReportTask(tpncLib, task), BackgroundType.CONCURRENT);
	}

	@Override
	public void fireShutDown() {
	}

	@Override
	public void fireTerminated() {
	}

}
