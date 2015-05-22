package org.iungo.common.interval.api;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AutomaticTimer extends SimpleTimer {

	private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

	private volatile Long interval = null;
	
	private volatile ScheduledFuture<?> splitScheduledFuture = null;
	
	public Long getInterval() {
		return interval;
	}

	public synchronized void setInterval(final Long interval) {
		if (getStarted() != null) {
			throw new UnsupportedOperationException();
		}
		this.interval = interval;
	}

	@Override
	public synchronized void start() {
		super.start();
		final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				split();
			}
		};
		splitScheduledFuture = scheduledExecutorService.scheduleAtFixedRate(runnable, interval, interval, TimeUnit.MILLISECONDS);
	}

	@Override
	public synchronized void stop() {
		splitScheduledFuture.cancel(false);
		super.stop();
	}

	@Override
	public synchronized void reset() {
		splitScheduledFuture.cancel(false);
		splitScheduledFuture = null;
		super.reset();
	}

	@Override
	public String toString() {
		return String.format("Interval [%d] Timer [%s]", getInterval(), super.toString());
	}
}
