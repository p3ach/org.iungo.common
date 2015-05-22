package org.iungo.common.api;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashSet<E> implements Set<E>, Serializable {

private static final long serialVersionUID = 1L;
	
	private final ConcurrentMap<E, Object> m;
	
	public ConcurrentHashSet() {
		super();
		m = new ConcurrentHashMap<E, Object>();
	}

	@Override
	public int size() {
		return m.size();
	}

	@Override
	public boolean isEmpty() {
		return m.isEmpty();
	}

	@Override
	public boolean contains(final Object o) {
		return m.containsKey(o);
	}

	@Override
	public Iterator<E> iterator() {
		return m.keySet().iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(final E e) {
		return m.putIfAbsent(e, Boolean.TRUE) == null;
	}

	@Override
	public boolean remove(final Object o) {
		return m.remove(o, Boolean.TRUE);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (E e : c) {
			if (!add(e)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		m.clear();
	}

}
