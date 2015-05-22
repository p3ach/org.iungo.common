package org.iungo.common.api;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BoundedDaemonThreadPoolExecutor extends DaemonThreadPoolExecutor {
	
	public BoundedDaemonThreadPoolExecutor(final Integer poolSize) {
		super(poolSize, poolSize, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	}
}
