package org.iungo.common.interval.api;

public interface Counter {

	static interface Listener {
		void go(int type);
	}
	
	static final int INTERVAL_CALLBACK = 2<<0;

	static final int RESET_CALLBACK = 2<<1;

//	static final int _CALLBACK = 2<<2;

//	static final int _CALLBACK = 2<<3;
	
	Long getIndex();
	
	Long getInterval();
	
	void setInterval(Long interval);
	
	Listener getListener();
	
	void setListener(Listener listener);
	
	void increment();
	
	void increment(Long value);
	
	void reset();
}
