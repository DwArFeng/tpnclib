package com.dwarfeng.tpnclib.core.control;

import com.dwarfeng.dutil.basic.cna.model.SyncReferenceModel;
import com.dwarfeng.tpnclib.core.model.struct.PieceCata;

final class SetAnchorPieceCataTask extends TpncLibTask {

	private final PieceCata newAnchor;

	public SetAnchorPieceCataTask(TpncLib tpncLib, PieceCata newAnchor) {
		super(tpncLib);
		this.newAnchor = newAnchor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void todo() throws Exception {
		SyncReferenceModel<PieceCata> anchorPieceCataModel = tpncLib.getToolkit().getAnchorPieceCataModel();

		anchorPieceCataModel.getLock().writeLock().lock();
		try {
			anchorPieceCataModel.set(newAnchor);
		} finally {
			anchorPieceCataModel.getLock().writeLock().unlock();
		}
		
		

	}
}