package com.dwarfeng.tpnclib.core.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.dwarfeng.dutil.basic.cna.model.obv.SetObverser;
import com.dwarfeng.tpnclib.core.model.cm.LoggerHandler;
import com.dwarfeng.tpnclib.core.model.cm.SyncLoggerHandler;
import com.dwarfeng.tpnclib.core.model.struct.Logger;
import com.dwarfeng.tpnclib.core.model.struct.LoggerInfo;

/**
 * 模型工具。
 * <p>
 * 与模型有关的工具包。
 * 
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class ModelUtil {

	private static class SyncLoggerHandlerImpl implements SyncLoggerHandler {

		private LoggerHandler delegate;
		private final ReadWriteLock lock = new ReentrantReadWriteLock();

		public SyncLoggerHandlerImpl(LoggerHandler delegate) {
			this.delegate = delegate;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean add(LoggerInfo e) {
			lock.writeLock().lock();
			try {
				return delegate.add(e);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addAll(Collection<? extends LoggerInfo> c) {
			lock.writeLock().lock();
			try {
				return delegate.addAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addObverser(SetObverser<LoggerInfo> obverser) {
			lock.writeLock().lock();
			try {
				return delegate.addObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clear() {
			lock.writeLock().lock();
			try {
				delegate.clear();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clearObverser() {
			lock.writeLock().lock();
			try {
				delegate.clearObverser();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean contains(Object o) {
			lock.readLock().lock();
			try {
				return delegate.contains(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			lock.readLock().lock();
			try {
				return delegate.containsAll(c);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsAllKey(Collection<?> c) {
			lock.readLock().lock();
			try {
				return delegate.containsAllKey(c);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsKey(Object key) {
			lock.readLock().lock();
			try {
				return delegate.containsKey(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void debug(String message) {
			lock.readLock().lock();
			try {
				delegate.debug(message);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean equals(Object o) {
			lock.readLock().lock();
			try {
				return delegate.equals(o);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void error(String message, Throwable t) {
			lock.readLock().lock();
			try {
				delegate.error(message, t);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fatal(String message, Throwable t) {
			lock.readLock().lock();
			try {
				delegate.fatal(message, t);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public LoggerInfo get(String key) {
			lock.readLock().lock();
			try {
				return delegate.get(key);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ReadWriteLock getLock() {
			return lock;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Set<SetObverser<LoggerInfo>> getObversers() {
			lock.readLock().lock();
			try {
				return delegate.getObversers();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode() {
			lock.readLock().lock();
			try {
				return delegate.hashCode();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void info(String message) {
			lock.readLock().lock();
			try {
				delegate.info(message);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isEmpty() {
			lock.readLock().lock();
			try {
				return delegate.isEmpty();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Iterator<LoggerInfo> iterator() {
			lock.readLock().lock();
			try {
				return delegate.iterator();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean remove(Object o) {
			lock.writeLock().lock();
			try {
				return delegate.remove(o);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.removeAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeAllKey(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.removeAllKey(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeKey(Object key) {
			lock.writeLock().lock();
			try {
				return delegate.removeKey(key);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeObverser(SetObverser<LoggerInfo> obverser) {
			lock.writeLock().lock();
			try {
				return delegate.removeObverser(obverser);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.retainAll(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean retainAllKey(Collection<?> c) {
			lock.writeLock().lock();
			try {
				return delegate.retainAllKey(c);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int size() {
			lock.readLock().lock();
			try {
				return delegate.size();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object[] toArray() {
			lock.readLock().lock();
			try {
				return delegate.toArray();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			lock.readLock().lock();
			try {
				return delegate.toArray(a);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void trace(String message) {
			lock.readLock().lock();
			try {
				delegate.trace(message);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean unuse(String... keys) {
			lock.writeLock().lock();
			try {
				return delegate.unuse(keys);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean unuseAll() {
			lock.writeLock().lock();
			try {
				return delegate.unuseAll();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean use(String... keys) {
			lock.writeLock().lock();
			try {
				return delegate.use(keys);
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean useAll() {
			lock.writeLock().lock();
			try {
				return delegate.useAll();
			} finally {
				lock.writeLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Collection<Logger> usedLoggers() {
			lock.readLock().lock();
			try {
				return delegate.usedLoggers();
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void warn(String message) {
			lock.readLock().lock();
			try {
				delegate.warn(message);
			} finally {
				lock.readLock().unlock();
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void warn(String message, Throwable t) {
			lock.readLock().lock();
			try {
				delegate.warn(message, t);
			} finally {
				lock.readLock().unlock();
			}
		}

	}

	private static class UnmodifiableLoggerHandler implements LoggerHandler {

		private final class UnmodifiableLoggerInfoIterator implements Iterator<LoggerInfo> {

			private final Iterator<LoggerInfo> delegate;

			public UnmodifiableLoggerInfoIterator(Iterator<LoggerInfo> delegate) {
				this.delegate = delegate;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return delegate.hasNext();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public LoggerInfo next() {
				return new UnmodifiableLoggerInfo(delegate.next());
			}

		}

		private final LoggerHandler delegate;

		public UnmodifiableLoggerHandler(LoggerHandler delegate) {
			this.delegate = delegate;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean add(LoggerInfo e) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addAll(Collection<? extends LoggerInfo> c) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean addObverser(SetObverser<LoggerInfo> obverser) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clearObverser() {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean contains(Object o) {
			return delegate.contains(o);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return delegate.containsAll(c);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsAllKey(Collection<?> c) {
			return delegate.containsAllKey(c);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean containsKey(Object key) {
			return delegate.containsKey(key);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void debug(String message) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void error(String message, Throwable t) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void fatal(String message, Throwable t) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public LoggerInfo get(String key) {
			return unmodifiableLoggerInfo(delegate.get(key));
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Set<SetObverser<LoggerInfo>> getObversers() {
			return delegate.getObversers();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void info(String message) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Iterator<LoggerInfo> iterator() {
			return new UnmodifiableLoggerInfoIterator(delegate.iterator());
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean remove(Object o) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeAllKey(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeKey(Object key) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean removeObverser(SetObverser<LoggerInfo> obverser) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean retainAllKey(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int size() {
			return delegate.size();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object[] toArray() {
			return delegate.toArray();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			return delegate.toArray(a);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void trace(String message) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean unuse(String... keys) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean unuseAll() {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean use(String... keys) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean useAll() {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Collection<Logger> usedLoggers() {
			return Collections.unmodifiableCollection(delegate.usedLoggers());
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void warn(String message) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void warn(String message, Throwable t) {
			throw new UnsupportedOperationException();
		}

	}

	private static class UnmodifiableLoggerInfo implements LoggerInfo {

		private final LoggerInfo delegate;

		public UnmodifiableLoggerInfo(LoggerInfo delegate) {
			super();
			this.delegate = delegate;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getKey() {
			return delegate.getKey();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Logger newLogger() throws Exception {
			throw new UnsupportedOperationException();
		}

	}

	/**
	 * 根据已有的记录器处理器生成一个线程同步的记录器处理器。
	 * 
	 * @param loggerHandler
	 *            指定的记录器处理器。
	 * @return 根据已有的记录处理器生成的线程同步的记录处理器。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static SyncLoggerHandler syncLoggerHandler(LoggerHandler loggerHandler) {
		Objects.requireNonNull(loggerHandler, "入口参数 loggerHandler 不能为 null。");
		return new SyncLoggerHandlerImpl(loggerHandler);
	}

	/**
	 * 根据指定的记录器处理器生成一个不可编辑的记录器处理器。
	 * 
	 * @param loggerHandler
	 *            指定的记录器处理器。
	 * @return 根据指定的记录器处理器生成的不可编辑的记录器处理器。
	 * @throws NullPointerException
	 *             入口参数为 <code>null</code>。
	 */
	public static LoggerHandler unmodifiableLoggerHandler(LoggerHandler loggerHandler) {
		return new UnmodifiableLoggerHandler(loggerHandler);
	}

	/**
	 * 根据指定的记录器信息生成一个不可编辑的记录器信息。
	 * 
	 * @param loggerInfo
	 *            指定的记录器信息。
	 * @return 根据指定的记录器信息生成的不可编辑的记录器信息。
	 */
	public static LoggerInfo unmodifiableLoggerInfo(LoggerInfo loggerInfo) {
		return new UnmodifiableLoggerInfo(loggerInfo);
	}

	// 禁止外部实例化
	private ModelUtil() {
	}

}
