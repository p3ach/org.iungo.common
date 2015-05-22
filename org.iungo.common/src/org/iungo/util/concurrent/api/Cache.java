package org.iungo.util.concurrent.api;

import java.util.Set;

public interface Cache<K, V> extends Iterable<CacheEntry<K, V>> {

	interface Go<K, V> {
		V get(K key);
	}
	
	V get(K key);

	V get(K key, Go<K, V> go);
	
	Boolean add(K key, V value);
	
	Boolean remove(K key);
	
	Set<K> keys();

	Set<V> values();
}
