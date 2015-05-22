package org.iungo.common.api;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 * A concurrent MRSW double linked nodes based list.
 * <p>Mutable actions are protected by synchronisation and non mutable actions are volatile.
 * 
 * @author dick
 *
 * @param <E>
 */
public class ConcurrentLinkedList<E> implements ConcurrentList<E> {

	protected static class Node<E> {
		volatile E element = null;
		volatile Node<E> previous = null;
		volatile Node<E> next = null;
		
		public Node(E element, Node<E> previous, Node<E> next) {
			super();
			this.element = element;
			this.previous = previous;
			this.next = next;
		}
	}

	protected volatile boolean mutated = false;
	
	protected volatile int size = 0;
	
	protected volatile Node<E> head = null;
	
	protected volatile Node<E> tail = null;

	/*
	 * List.
	 */
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public boolean contains(Object o) {
		return (indexOf(o) != -1);
	}

	@Override
	public Iterator<E> iterator() {
		return listIterator();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized boolean add(final E e) {
		final Node<E> node = new Node<>(Objects.requireNonNull(e), null, null);
		if (size == 0) {
			tail = head = node;
		} else {
			node.previous = tail;
			tail.next = node;
			tail = node;
		}
		size++;
		return true;
	}

	@Override
	public synchronized boolean remove(final Object o) {
		Node<E> node = head;
		while (node != null) {
			if (node.element.equals(o)) {
				if (node.previous == null) {
					head = node.next;
				} else {
					node.previous.next = node.next;
				}
				if (node.next == null) {
					tail = node.previous;
				} else {
					node.next.previous = node.previous;
				}
				return true;
			}
			node = node.next;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized void clear() {
		tail = head = null;
		size = 0;
	}

	/**
	 * Get the E at the given index starting from the head.
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		/*
		 * Perform a best effort from the head as the list is mutable.
		 */
		int current = 0;
		Node<E> node = head;
		while (current != index && node != null) {
			current++;
			node = node.next;
		}
		if (current == index && node != null) {
			return node.element;
		}
		return null;
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		Node<E> node = head;
		int index = -1;
		while (true) {
			index++;
			if (node.element.equals(o)) {
				break;
			}
			node = node.next;
			if (node == null) {
				index = -1;
				break;
			}
		}
		return index;
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new ListIterator<E>() {

			protected Node<E> previous = null;
			
			protected Node<E> next = head;
			
			@Override
			public boolean hasNext() {
				return (next != null);
			}

			@Override
			public E next() {
				final Node<E> node = next;
				previous = node.previous;
				next = node.next;
				return (node == null ? null : node.element);
			}

			@Override
			public boolean hasPrevious() {
				return (previous != null);
			}

			@Override
			public E previous() {
				final Node<E> node = previous;
				previous = node.previous;
				next = node.next;
				return (node == null ? null : node.element);
			}

			@Override
			public int nextIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public int previousIndex() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void set(E e) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void add(E e) {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}
}
