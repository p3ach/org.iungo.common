package org.iungo.common.interval.test;

import org.iungo.common.interval.api.AutomaticTimer;
import org.iungo.common.interval.api.SimpleTimer;
import org.iungo.common.interval.api.Timer.Listener;
import org.junit.Test;

public class IntervalTest {

	@Test
	public void timer() throws InterruptedException {
		System.out.println(SimpleTimer.class.getName());
		final SimpleTimer timer = new SimpleTimer();
		timer.start();
		System.out.println(timer);
		Thread.sleep(500);
		timer.split();
		System.out.println(timer);
		Thread.sleep(500);
		timer.split();
		System.out.println(timer);
		Thread.sleep(500);
		timer.stop();
		System.out.println(timer);
	}
	
	@Test
	public void automaticTimer() throws InterruptedException {
		System.out.println(AutomaticTimer.class.getName());
		final AutomaticTimer automaticTimer = new AutomaticTimer();
		automaticTimer.setInterval(500L);
		automaticTimer.setListener(new Listener() {
			@Override
			public void go(final int type) {
				System.out.println(type);
			}
		});
		automaticTimer.start();
		System.out.println(automaticTimer);
		Thread.sleep(2000);
		System.out.println(automaticTimer);
		automaticTimer.stop();
		System.out.println(automaticTimer);
	}

}
