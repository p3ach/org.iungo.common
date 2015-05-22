package org.iungo.util.concurrent.api;

public interface CacheEntry<K, V> {

	K getKey();
	
	V getValue();
}
