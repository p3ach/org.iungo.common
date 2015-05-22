package org.iungo.common.properties.api;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Extend the Java Properties with convenience methods.
 * 
 * @author dick
 *
 */
public class IungoProperties extends Properties {
	
	private static final long serialVersionUID = 1L;
	
	public static IungoProperties valueOf(final URL url) {
		final IungoProperties iungoProperties = new IungoProperties();
		iungoProperties.load(url);
		return iungoProperties;
	}
	
	public IungoProperties() {
		super();
	}

	public IungoProperties(final Properties defaults) {
		super(defaults);
	}
	
	public synchronized Boolean hasProperty(final String key) {
		return getProperty(key) != null;
	}
	
	public synchronized void setDefaults(final Properties p) {
		defaults = p;
	}
	
	public synchronized void load(final URL url) {
		try (final InputStream is = url.openStream()) {
			load(is);
		} catch (final Exception exception) {
			System.err.println(exception);
		}
	}
}
