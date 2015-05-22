package org.iungo.common.api;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class CacheDaemonThreadPoolExecutor extends DaemonThreadPoolExecutor {

	public CacheDaemonThreadPoolExecutor() {
		super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(true));
	}

	@Override
	public String toString() {
		return "Cache [" + super.toString() + "]";
	}
}
