package org.iungo.common.interval.api;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

import org.iungo.common.api.ConcurrentLinkedList;

public class SimpleTimer implements Timer {
	
	protected volatile Long started = null;
	
	protected final List<Long> splits = new ConcurrentLinkedList<>();
	
	protected volatile Long stopped = null;
	
	protected volatile WeakReference<Listener> listener = null;
	
	@Override
	public Long getStarted() {
		return started;
	}
	
	@Override
	public List<Long> getSplits() {
		return Collections.unmodifiableList(splits);
	}
	
	@Override
	public Long getStopped() {
		return stopped;
	}

	public Listener getListener() {
		final WeakReference<Listener> listener = this.listener;
		return (listener == null ? null : listener.get());
	}
	
	public synchronized void setListener(final Listener listener) {
		this.listener = new WeakReference<Listener>(listener);
	}
	
	protected void listenerGo(final int type) {
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
	public synchronized void start() {
		final long time = System.currentTimeMillis();
		started = time;
		listenerGo(START_CALLBACK);
	}

	@Override
	public synchronized void split() {
		Long time = System.currentTimeMillis();
		splits.add(time);
		listenerGo(SPLIT_CALLBACK);
	}
	
	@Override
	public synchronized void stop() {
		Long time = System.currentTimeMillis();
		stopped= time;
		listenerGo(STOP_CALLBACK);
	}
	
	@Override
	public synchronized void reset() {
		started = null;
		splits.clear();
		stopped = null;
		listenerGo(RESET_CALLBACK);
	}

	@Override
	public String toString() {
		return String.format("Started [%d] Splits [%d] Stopped [%d]", getStarted(), splits.size(), getStopped());
	}
}
