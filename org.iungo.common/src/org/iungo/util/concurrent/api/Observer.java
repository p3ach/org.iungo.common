package org.iungo.util.concurrent.api;

public interface Observer<V> {

	 void go(Observable<V> observable);
}
