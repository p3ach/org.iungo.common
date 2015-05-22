package org.iungo.common.dev;

public class DevObserver {

	static interface Observable<V> {
	}
	
	static interface Observer<V> {
		void observe(Observable<V> observable);
	}

	static interface Observers<K, V> {

		Boolean add(K key, Observer<V> observer);
		
		Boolean remove(K key);
		
		void clear();
		
		Observer<V> get(K key);
	}
	
	static class Fred {
	}
	
	static class FredObservable implements Observable<Fred> {
		private final String name;
		
		public FredObservable(final String name) {
			super();
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
	
	
	static class FredObserver implements Observer<Fred> {
		@Override
		public void observe(Observable<Fred> observable) {
			System.out.println(((FredObservable) observable).getName());
		}
	}
	
	public static void main(String[] args) {
		FredObserver fredObserver = new FredObserver();
		fredObserver.observe(new FredObservable("bob"));
	}

}
