package org.iungo.util.concurrent.api;

public interface Observers<K, V> {

	Boolean add(K key, Observer<V> observer);
	
	Boolean remove(K key);
	
	Observer<V> get(K key);
}
