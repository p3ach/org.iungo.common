package org.iungo.common.interval.api;

import java.lang.ref.WeakReference;

public class SimpleCounter implements Counter {

	protected volatile Long index = 0L;
	
	protected volatile Long interval = null;
	
	protected volatile Long listenerIndex = null;
	
	/**
	 * WeakReference to the Listener so we don't block the GC i.e. Lapsed Listener Problem.
	 */
	protected volatile WeakReference<Listener> listener = null;

	@Override
	public Long getIndex() {
		return index;
	}
	
	@Override
	public Long getInterval() {
		return interval;
	}

	@Override
	public synchronized void setInterval(final Long value) {
		interval = value;
		listenerIndex = index + value;
	}

	@Override
	public Listener getListener() {
		final WeakReference<Listener> listener = this.listener;
		return (listener == null ? null : listener.get());
	}
	
	@Override
	public synchronized void setListener(final Listener listener) {
		this.listener = new WeakReference<Listener>(listener);
	}
	
	protected void doCallback(final int type) {
		final Listener listener = getListener();
		if (listener != null) {
			try {
				listener.go(type);
			} catch (final Throwable throwable) {
				System.err.println(throwable);
			}
		}
	}
	
	@Override
	public synchronized void increment() {
		index++;
		checkForIntervalCallback();
	}

	@Override
	public synchronized void increment(final Long value) {
		index += value;
		checkForIntervalCallback();
	}

	/**
	 * Call from a synchronized method.
	 */
	protected void checkForIntervalCallback() {
		if (listenerIndex != null && index >= listenerIndex) {
			doCallback(INTERVAL_CALLBACK);
			listenerIndex += interval;
		}
	}

	@Override
	public synchronized void reset() {
		index = 0L;
		listenerIndex = (interval == null ? null : interval);
		doCallback(RESET_CALLBACK);
	}

	@Override
	public String toString() {
		return String.format("Index [%d] Interval [%d/%d] Callback [%s]", index, interval, listenerIndex, getListener());
	}
}
