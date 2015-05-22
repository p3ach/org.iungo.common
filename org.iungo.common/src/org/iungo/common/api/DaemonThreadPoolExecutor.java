package org.iungo.common.api;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {
	
	static public final Integer THREAD_CORE_DEFAULT = Runtime.getRuntime().availableProcessors() * 2;
	
	static public final Integer THREAD_MAX_DEFAULT = THREAD_CORE_DEFAULT * 8;
	
	static public final Long THREAD_IDLE_PERIOD_DEFAULT = 60000L;
	
	static public final TimeUnit THREAD_IDLE_UNIT_DEFAULT = TimeUnit.MILLISECONDS;
	
	static public final Integer QUEUE_MAX_DEFAULT = THREAD_MAX_DEFAULT * 8;
	
	public DaemonThreadPoolExecutor(Integer corePool, Integer maxPool, Long threadIdle, TimeUnit idleUnit, BlockingQueue<Runnable> workQueue) {
		super(corePool, maxPool, threadIdle, idleUnit, workQueue, new DaemonThreadFactory());
	}
	
	@Override
	public String toString() {
		return String.format("Daemon ~Active [%d] ~Total [%d] ~Completed [%d] Core [%d] Largest [%d] Maximum [%d] ~Queue [%d(%s)]", getActiveCount(), getTaskCount(), getCompletedTaskCount(), getCorePoolSize(), getLargestPoolSize(), getMaximumPoolSize(), getQueue().size(), getQueue().getClass().getSimpleName());
	}
}