package org.iungo.common.collections.api;

import java.util.HashSet;
import java.util.Set;

public class IungoCollections {
	
	public static <T> Set<T> newSet(final T[] objs) {
	    Set<T> set = new HashSet<T>();
	    for (T o : objs) {
	        set.add(o);
	    }
	    return set;
	}
}
