package com.dwarfeng.tpnclib.core.control;

final class StartTask extends TpncLibTask {

	public StartTask(TpncLib tpncLib) {
		super(tpncLib);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void todo() throws Exception {
		// TODO 异常处理？
		tpncLib.getToolkit().start();
	}
}

final class ExitTask extends TpncLibTask {

	public ExitTask(TpncLib tpncLib) {
		super(tpncLib);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void todo() throws Exception {
		// TODO 异常处理？
		tpncLib.getToolkit().exit();
	}

}