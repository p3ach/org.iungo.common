package org.iungo.common.osgi;

import org.iungo.common.api.CommonAPI;
import org.iungo.common.api.ConcurrentLinkedList;
import org.iungo.common.api.ConcurrentList;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public abstract class CommonBundleActivator implements BundleActivator {

	protected static volatile CommonBundleActivator instance = null;
	
	public CommonBundleActivator getInstance() {
		return instance;
	}
	
	protected volatile BundleContext bundleContext = null;
	
	public final CommonAPI commonAPI = new CommonAPI() {
		@Override
		public <E> ConcurrentList<E> createConcurrentList() {
			return new ConcurrentLinkedList<>();
		}
	};
	
	protected ServiceRegistration<CommonAPI> serviceRegistration = null;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(final BundleContext bundleContext) throws Exception {
		this.bundleContext = bundleContext;
		serviceRegistration = bundleContext.registerService(CommonAPI.class, commonAPI, null);
		instance = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(final BundleContext bundleContext) throws Exception {
		instance = null;
		serviceRegistration.unregister();
		this.bundleContext = null;
	}

}
