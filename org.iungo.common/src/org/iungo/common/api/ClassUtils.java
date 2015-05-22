package org.iungo.common.api;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ClassUtils {
	
	public static final Map<String, Class<?>> builtInMap = createBuiltInMap();
	
	public static Map<String, Class<?>> createBuiltInMap() {
		Map<String, Class<?>> builtInMap = new HashMap<>();
		builtInMap.put(int.class.getName(), Integer.TYPE );
		builtInMap.put(long.class.getName(), Long.TYPE );
		builtInMap.put(double.class.getName(), Double.TYPE );
		builtInMap.put(float.class.getName(), Float.TYPE );
		builtInMap.put(boolean.class.getName(), Boolean.TYPE );
		builtInMap.put(char.class.getName(), Character.TYPE );
		builtInMap.put(byte.class.getName(), Byte.TYPE );
		builtInMap.put(void.class.getName(), Void.TYPE );
		builtInMap.put(short.class.getName(), Short.TYPE );
		return Collections.unmodifiableMap(builtInMap);
	}
	
	public static Class<?> forName(final String name) throws ClassNotFoundException {
		Class<?> c = builtInMap.get(name);
		if (c == null) {
			c = Class.forName(name);
		}
		return c;
	}

	public static Constructor<?> getConstructor(final Class<?> c, final Class<?>[] p) {
		try {
			return c.getConstructor(p);
		} catch (final Exception exception) {
			return null;
		}
	}
	
	
	
	public static Object create(final String className, final Class<?>[] parameters, final Object[] arguments) {
		final Class<?> c = forName(className);
		if (c == null) {
			return null;
		}
		final Constructor<?> constructor = getConstructor(c, parameters);
		if (constructor == null) {
			return null;
		}
		Object o = constructor.newInstance(arguments);
	}
	
	/**
	 * Return a String[] containing the Class names for the given Class<?>[].
	 * @param classes
	 * @return
	 */
	public static String[] getNames(final Class<?>[] classes) {
		String[] names = new String[classes.length];
		for (int i = 0; i < classes.length; i++) {
			names[i] = classes[i].getName();
		}
		return names;
	}
	
	public static void main(final String[] args) {
	}
}
