package org.iungo.common.api;

public class SingleDaemonThreadPoolExecutor extends BoundedDaemonThreadPoolExecutor {

	public SingleDaemonThreadPoolExecutor() {
		super(1);
	}
}
