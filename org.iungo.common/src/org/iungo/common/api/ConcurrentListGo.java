package org.iungo.common.api;

public interface ConcurrentListGo<E> {

	<T> T go(ConcurrentList<E> concurrentList);
}
