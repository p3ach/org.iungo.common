package org.iungo.util.concurrent.api;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WeakValueConcurrentCache<K, V> implements Cache<K, V> {
	
	private static class WeakValueCacheEntry<K, V> implements CacheEntry<K, V> {

		private final K key;
		
		private final WeakReference<V> value;
		
		public WeakValueCacheEntry(final K key, final WeakReference<V> value) {
			super();
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value.get();
		}
	}
	
	private static class WeakValueCacheIterator<K, V> implements Iterator<CacheEntry<K, V>> {

		private final Iterator<Entry<K, WeakReference<V>>> iterator;
		
		public WeakValueCacheIterator(Iterator<Entry<K, WeakReference<V>>> iterator) {
			super();
			this.iterator = iterator;
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public CacheEntry<K, V> next() {
			Entry<K, WeakReference<V>> entry = iterator.next();
			return new WeakValueCacheEntry<K, V>(entry.getKey(), entry.getValue());
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	private static final long serialVersionUID = 1L;
	
	private final ConcurrentHashMap<K, WeakReference<V>> cache = new ConcurrentHashMap<>();
	
	private final AtomicInteger hitCount = new AtomicInteger();
	
	private final AtomicInteger missCount = new AtomicInteger();
	
	private final AtomicInteger goCount = new AtomicInteger();
	
	public WeakValueConcurrentCache() {
		super();
	}

	@Override
	public Set<K> keys() {
		return Collections.unmodifiableSet(cache.keySet());
	}

	@Override
	public Set<V> values() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Iterator<CacheEntry<K, V>> iterator() {
		return new WeakValueCacheIterator<>(cache.entrySet().iterator());
	}
	
	public V get(final K key) {
		return get(key, null);
	}
	
	public V get(final K key, final Go<K, V> go) {
		while (true) {
			/*
			 * Get the current value for the given key.
			 */
			final WeakReference<V> weakReference = cache.get(key);
			/*
			 * Key does not exist so call Go.get(K) or return null.
			 */
			if (weakReference == null) {
				/*
				 * 
				 */
				missCount.incrementAndGet();
				/*
				 * If Go not defined return null.
				 */
				if (go == null) {
					return null;
				}
				/*
				 * Create a new WeakReference using the return from Go.get(K).
				 */
				goCount.incrementAndGet();
				final V value = go.get(key);
				cache.put(key, new WeakReference<V>(go.get(key)));
				return value;
			}
			/*
			 * Get the value which might be null.
			 */
			final V value = weakReference.get();
			if (value != null) {
				/*
				 * The value is not null so return it.
				 */
				hitCount.incrementAndGet();
				return value;
			}
			/*
			 * Try and remove the key/value pair and return null if we succeed.
			 * It is possible that another value for the same key has been added in which case the remove will return false and we loop again.
			 */
			if (cache.remove(key, weakReference)) {
				return null;
			}
		}
	}
	
	public Boolean add(final K key, final V value) {
		return (cache.putIfAbsent(key, new WeakReference<V>(value)) == null);
	}
	
	public Boolean remove(final K key) {
		return cache.remove(key) != null;
	}

	@Override
	public String toString() {
		return String.format("Hit [%d] Miss [%d] Go [%d]", hitCount, missCount, goCount);
	}
}
