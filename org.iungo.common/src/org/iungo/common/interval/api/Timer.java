package org.iungo.common.interval.api;

import java.util.List;

public interface Timer {
	
	static interface Listener {
		void go(int type);
	}
	
	static final int START_CALLBACK = 2<<0;

	static final int SPLIT_CALLBACK = 2<<1;
	
	static final int STOP_CALLBACK = 2<<2;
	
	static final int RESET_CALLBACK = 2<<3;

	Long getStarted();
	
	List<Long> getSplits();
	
	Long getStopped();
	
	void start();
	
	void split();
	
	void stop();
	
	void reset();
}
