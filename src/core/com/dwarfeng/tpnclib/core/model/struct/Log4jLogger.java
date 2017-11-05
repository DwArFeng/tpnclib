package com.dwarfeng.tpnclib.core.model.struct;

import java.util.Objects;

/**
 * 通过 Log4j 实现的记录器接口。
 * <p>
 * 该类是线程安全的。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class Log4jLogger implements Logger {

	private final org.apache.logging.log4j.core.Logger logger;

	/**
	 * 生成一个由指定 logger 构成的记录器接口。
	 * 
	 * @param logger
	 *            指定的 logger。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public Log4jLogger(org.apache.logging.log4j.core.Logger logger) {
		Objects.requireNonNull(logger, "入口参数 logger 不能为 null。");
		this.logger = logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.notewiz.core.model.struct.Logger#trace(java.lang.String)
	 */
	@Override
	public void trace(String message) {
		logger.trace(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.notewiz.core.model.struct.Logger#debug(java.lang.String)
	 */
	@Override
	public void debug(String message) {
		logger.debug(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.notewiz.core.model.struct.Logger#info(java.lang.String)
	 */
	@Override
	public void info(String message) {
		logger.info(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.notewiz.core.model.struct.Logger#warn(java.lang.String)
	 */
	@Override
	public void warn(String message) {
		logger.warn(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.notewiz.core.model.struct.Logger#warn(java.lang.String,
	 * java.lang.Throwable)
	 */
	@Override
	public void warn(String message, Throwable t) {
		logger.warn(message, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.notewiz.core.model.struct.Logger#error(java.lang.String,
	 * java.lang.Throwable)
	 */
	@Override
	public void error(String message, Throwable t) {
		logger.error(message, t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.notewiz.core.model.struct.Logger#fatal(java.lang.String,
	 * java.lang.Throwable)
	 */
	@Override
	public void fatal(String message, Throwable t) {
		logger.fatal(message, t);
	}

}
