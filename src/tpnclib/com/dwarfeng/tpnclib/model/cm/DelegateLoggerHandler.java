package com.dwarfeng.tpnclib.model.cm;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.dwarfeng.dutil.basic.cna.model.KeySetModel;
import com.dwarfeng.dutil.basic.cna.model.MapKeySetModel;
import com.dwarfeng.dutil.basic.cna.model.obv.SetObverser;
import com.dwarfeng.tpnclib.model.obv.LoggerObverser;
import com.dwarfeng.tpnclib.model.struct.Logger;
import com.dwarfeng.tpnclib.model.struct.LoggerInfo;

/**
 * 代理记录器处理器。
 * <p>
 * 通过代理一个 <code>KeySetModel</code> 来实现的记录器处理器。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class DelegateLoggerHandler implements LoggerHandler {

	private final KeySetModel<String, LoggerInfo> delegate;
	private final Map<String, Logger> usingMap = new LinkedHashMap<>();

	/**
	 * 生成一个默认的代理记录器处理器。
	 */
	public DelegateLoggerHandler() {
		this(new MapKeySetModel<>());
	}

	/**
	 * 生成一个具有指定代理的记录器处理器。
	 * 
	 * @param delegate
	 *            指定的代理。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public DelegateLoggerHandler(KeySetModel<String, LoggerInfo> delegate) {
		Objects.requireNonNull(delegate, "入口参数 delegate 不能为 null。");
		this.delegate = delegate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#getObversers()
	 */
	@Override
	public Set<SetObverser<LoggerInfo>> getObversers() {
		return delegate.getObversers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.prog.ObverserSet#addObverser(com.dwarfeng.dutil.
	 * basic.prog.Obverser)
	 */
	@Override
	public boolean addObverser(SetObverser<LoggerInfo> obverser) {
		return delegate.addObverser(obverser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.cna.model.KeySetModel#get(java.lang.Object)
	 */
	@Override
	public LoggerInfo get(String key) {
		return delegate.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.prog.ObverserSet#removeObverser(com.dwarfeng.
	 * dutil.basic.prog.Obverser)
	 */
	@Override
	public boolean removeObverser(SetObverser<LoggerInfo> obverser) {
		return delegate.removeObverser(obverser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#containsKey(java.lang.
	 * Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		return delegate.containsKey(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.prog.ObverserSet#clearObverser()
	 */
	@Override
	public void clearObverser() {
		delegate.clearObverser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#containsAllKey(java.util.
	 * Collection)
	 */
	@Override
	public boolean containsAllKey(Collection<?> c) {
		return delegate.containsAllKey(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.dutil.basic.cna.model.KeySetModel#removeKey(java.lang.
	 * Object)
	 */
	@Override
	public boolean removeKey(Object key) {
		if (delegate.removeKey(key)) {
			unuseOne((String) key);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#removeAllKey(java.util.
	 * Collection)
	 */
	@Override
	public boolean removeAllKey(Collection<?> c) {
		Objects.requireNonNull(c, "入口参数 c 不能为 null。");
		return batchRemoveKey(c, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.dutil.basic.cna.model.KeySetModel#retainAllKey(java.util.
	 * Collection)
	 */
	@Override
	public boolean retainAllKey(Collection<?> c) {
		Objects.requireNonNull(c, "入口参数 c 不能为 null。");
		return batchRemoveKey(c, false);
	}

	private boolean batchRemoveKey(Collection<?> c, boolean aFlag) {
		boolean result = false;

		for (LoggerInfo loggerInfo : delegate) {

			if (c.contains(loggerInfo == null ? null : loggerInfo.getKey()) == aFlag) {
				removeKey(loggerInfo);
				result = true;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#size()
	 */
	@Override
	public int size() {
		return delegate.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#iterator()
	 */
	@Override
	public Iterator<LoggerInfo> iterator() {
		return new DelegateIterator(delegate.iterator());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#toArray()
	 */
	@Override
	public Object[] toArray() {
		return delegate.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return delegate.toArray(a);
	}

	private class DelegateIterator implements Iterator<LoggerInfo> {

		private final Iterator<LoggerInfo> delegate;
		private LoggerInfo loggerInfo;

		public DelegateIterator(Iterator<LoggerInfo> delegate) {
			this.delegate = delegate;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return delegate.hasNext();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public LoggerInfo next() {
			loggerInfo = delegate.next();
			return loggerInfo;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			delegate.remove();
			unuseOne(loggerInfo == null ? null : loggerInfo.getKey());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#add(java.lang.Object)
	 */
	@Override
	public boolean add(LoggerInfo e) {
		return delegate.add(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		if (delegate.remove(o)) {
			LoggerInfo loggerInfo = (LoggerInfo) o;
			unuse(loggerInfo == null ? null : loggerInfo.getKey());
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return delegate.containsAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends LoggerInfo> c) {
		return delegate.addAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		Objects.requireNonNull(c, "入口参数 c 不能为 null。");
		return batchRemove(c, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		Objects.requireNonNull(c, "入口参数 c 不能为 null。");
		return batchRemove(c, false);
	}

	private boolean batchRemove(Collection<?> c, boolean aFlag) {
		boolean result = false;

		for (LoggerInfo loggerInfo : delegate) {

			if (c.contains(loggerInfo) == aFlag) {
				remove(loggerInfo);
				result = true;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#clear()
	 */
	@Override
	public void clear() {
		delegate.clear();
		unuseAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		return delegate.equals(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return delegate.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.notewiz.core.model.cm.LoggerHandler#use(java.lang.String[])
	 */
	@Override
	public boolean use(String... keys) {
		Objects.requireNonNull(keys, "入口参数 keys 不能为 null。");

		boolean aFlag = false;
		for (String key : keys) {
			if (useOne(key))
				aFlag = true;
		}
		return aFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwarfeng.notewiz.core.model.cm.LoggerHandler#unuse(java.lang.String[]
	 * )
	 */
	@Override
	public boolean unuse(String... keys) {
		Objects.requireNonNull(keys, "入口参数 keys 不能为 null。");

		boolean aFlag = false;
		for (String key : keys) {
			if (useOne(key)) {
				aFlag = true;
			}
		}
		return aFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.notewiz.core.model.cm.LoggerHandler#useAll()
	 */
	@Override
	public boolean useAll() {
		boolean aFlag = false;
		for (LoggerInfo loggerInfo : this) {
			useOne(loggerInfo == null ? null : loggerInfo.getKey());
		}
		return aFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.notewiz.core.model.cm.LoggerHandler#unuseAll()
	 */
	@Override
	public boolean unuseAll() {
		boolean aFlag = false;
		for (LoggerInfo loggerInfo : this) {
			unuseOne(loggerInfo == null ? null : loggerInfo.getKey());
		}
		return aFlag;
	}

	private boolean useOne(String key) {
		if (!containsKey(key))
			return false;
		LoggerInfo loggerInfo = get(key);
		if (Objects.isNull(loggerInfo))
			return false;
		try {
			Logger logger = loggerInfo.newLogger();
			usingMap.put(key, logger);
			fireLoggerUsed(key, logger);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean unuseOne(String key) {
		if (!usingMap.containsKey(key))
			return false;
		Logger logger = usingMap.remove(key);
		fireLoggerUnused(key, logger);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dwarfeng.notewiz.core.model.cm.LoggerHandler#usedLoggers()
	 */
	@Override
	public Collection<Logger> usedLoggers() {
		return Collections.unmodifiableCollection(usingMap.values());
	}

	/**
	 * 通知观察器指定的记录器被使用。
	 * 
	 * @param key
	 *            指定的记录器对应的键。
	 * @param logger
	 *            指定的记录器。
	 */
	protected void fireLoggerUsed(String key, Logger logger) {
		for (SetObverser<LoggerInfo> obverser : delegate.getObversers()) {
			if (Objects.nonNull(obverser) && obverser instanceof LoggerObverser) {
				try {
					((LoggerObverser) obverser).fireLoggerUsed(key, logger);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 通知观察器指定的记录器停止使用。
	 * 
	 * @param key
	 *            指定的记录器对应的键。
	 * @param logger
	 *            指定的记录器。
	 */
	protected void fireLoggerUnused(String key, Logger logger) {
		for (SetObverser<LoggerInfo> obverser : delegate.getObversers()) {
			if (Objects.nonNull(obverser) && obverser instanceof LoggerObverser) {
				try {
					((LoggerObverser) obverser).fireLoggerUnused(key, logger);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void trace(String message) {
		Objects.requireNonNull(message, "入口参数 message 不能为 null。");
		usingMap.values().forEach(logger -> {
			logger.trace(message);
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void debug(String message) {
		Objects.requireNonNull(message, "入口参数 message 不能为 null。");
		usingMap.values().forEach(logger -> {
			logger.debug(message);
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void info(String message) {
		Objects.requireNonNull(message, "入口参数 message 不能为 null。");
		usingMap.values().forEach(logger -> {
			logger.info(message);
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void warn(String message) {
		Objects.requireNonNull(message, "入口参数 message 不能为 null。");
		usingMap.values().forEach(logger -> {
			logger.warn(message);
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void warn(String message, Throwable t) {
		Objects.requireNonNull(message, "入口参数 message 不能为 null。");
		Objects.requireNonNull(t, "入口参数 t 不能为 null。");
		usingMap.values().forEach(logger -> {
			logger.warn(message, t);
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void error(String message, Throwable t) {
		Objects.requireNonNull(message, "入口参数 message 不能为 null。");
		Objects.requireNonNull(t, "入口参数 t 不能为 null。");
		usingMap.values().forEach(logger -> {
			logger.error(message, t);
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fatal(String message, Throwable t) {
		Objects.requireNonNull(message, "入口参数 message 不能为 null。");
		Objects.requireNonNull(t, "入口参数 t 不能为 null。");
		usingMap.values().forEach(logger -> {
			logger.fatal(message, t);
		});
	}

}
