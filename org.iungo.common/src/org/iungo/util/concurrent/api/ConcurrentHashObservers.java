package org.iungo.util.concurrent.api;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashObservers<K, V> implements Observers<K, V> {

	private final ConcurrentMap<K, Observer<V>> observers = new ConcurrentHashMap<>();
	
	@Override
	public Boolean add(final K key, final Observer<V> observer) {
		return (observers.putIfAbsent(key, observer) == null);
	}

	@Override
	public Boolean remove(final K key) {
		return (observers.remove(key) != null);
	}

	@Override
	public Observer<V> get(final K key) {
		return observers.get(key);
	}

}
