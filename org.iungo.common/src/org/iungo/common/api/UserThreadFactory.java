package org.iungo.common.api;

import java.util.concurrent.ThreadFactory;

public class UserThreadFactory implements ThreadFactory {

	protected static final UserThreadFactory instance = new UserThreadFactory();
	
	/**
	 * Create a new Daemon Thread for the given runnable and (optional) name.
	 * @param runnable
	 * @param name
	 * @return
	 */
	public static Thread valueOf(final Runnable runnable, final String name) {
		final Thread thread = instance.newThread(runnable);
		if (name != null) {
			thread.setName(name);
		}
		return thread;
	}
	
	public Thread newThread(final Runnable runnable) {
		final Thread t = new Thread(runnable);
		t.setDaemon(false);
		return t;
	}
}