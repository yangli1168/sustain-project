package net.xinqushi.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 定制HashMap,在读写之间加锁控制，保证map里全套参数完全刷新(putAll)之后才能读取（get）
 * 
 * @author Administrator
 *
 * @param <K>
 * @param <V>
 */
public class CustomHashMap<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = 5587559227625188465L;
	private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

	public V get(Object key) {
		Lock readLock = rwLock.readLock();
		try {
			readLock.lock();
			return super.get(key);
		} finally {
			readLock.unlock();
		}
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		Lock writeLock = rwLock.writeLock();
		try {
			writeLock.lock();
			super.putAll(m);
		} finally {
			writeLock.unlock();
		}
	}

	public ReentrantReadWriteLock getRwLock() {
		return rwLock;
	}

}
