package org.iungo.common.api;

import java.util.LinkedList;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public abstract class AbstractBundleActivator implements BundleActivator {

	protected static volatile AbstractBundleActivator instance = null;
	
	public static AbstractBundleActivator getInstance() {
		return instance;
	}
	
	protected volatile BundleContext bundleContext = null;
	
	protected final List<ServiceRegistrationEntry> services = new LinkedList<>();

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(final BundleContext bundleContext) throws Exception {
		this.bundleContext = bundleContext;
		registerServices();
		instance = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(final BundleContext bundleContext) throws Exception {
		instance = null;
		unregisterServices();
		this.bundleContext = null;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAPI(final Class<?> classInstance) {
		return (T) bundleContext.getService(bundleContext.getServiceReference(classInstance));
	}
	
	protected abstract void defineServices();
	
	protected void registerServices() {
		for (ServiceRegistrationEntry serviceRegistrationEntry : services) {
			serviceRegistrationEntry.registerService(bundleContext);
		}
	}
	
	protected void unregisterServices() {
		for (ServiceRegistrationEntry serviceRegistrationEntry : services) {
			serviceRegistrationEntry.unregisterService();
		}
		services.clear();
	}
}
