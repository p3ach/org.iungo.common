package org.iungo.common.api;


public class ArrayUtils {

	public static final String PREFIX = "(";
	
	public static final String SUFFIX = ")";
	
	public static final String SEPARATER = ",";
	
    public static <T> String asString(final T[] a) {
    	return asString(a, SEPARATER);
    }

    public static <T> String asString(final T[] a, final String separator) {
		final StringBuilder result = new StringBuilder(1024);
		if (a.length > 0) {
			result.append(a[0]);
			for (int i = 1; i < a.length; i++) {
				result.append(SEPARATER + a[i]);
			}
		}
		return result.toString();
	}
}
