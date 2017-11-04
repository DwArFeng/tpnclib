package com.dwarfeng.tpnclib.model.obv;

import com.dwarfeng.tpnclib.model.struct.Logger;
import com.dwarfeng.tpnclib.model.struct.LoggerInfo;

/**
 * 记录器观察器适配器。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public abstract class LoggerAdapter implements LoggerObverser {

	@Override
	public void fireAdded(LoggerInfo element) {
	}

	@Override
	public void fireRemoved(LoggerInfo element) {
	}

	@Override
	public void fireCleared() {
	}

	@Override
	public void fireLoggerUsed(String key, Logger logger) {
	}

	@Override
	public void fireLoggerUnused(String key, Logger logger) {
	}

}
