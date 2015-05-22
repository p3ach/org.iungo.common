package org.iungo.common.api;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceRegistrationEntry {
	
	protected final String[] serviceNames;
	
	protected final Object serviceInstance;
	
	protected final Dictionary<String, ?> serviceProperties;
	
	protected volatile ServiceRegistration<?> serviceRegistration; 

	public ServiceRegistrationEntry(final String[] serviceNames, final Object serviceInstance, final Dictionary<String, ?> serviceProperties) {
		super();
		this.serviceNames = serviceNames;
		this.serviceInstance = serviceInstance;
		this.serviceProperties = serviceProperties;
	}
	
	public void registerService(final BundleContext bundleContext) {
		serviceRegistration = bundleContext.registerService(serviceNames, serviceInstance, serviceProperties);
	}
	
	public void unregisterService() {
		serviceRegistration.unregister();
	}
}
