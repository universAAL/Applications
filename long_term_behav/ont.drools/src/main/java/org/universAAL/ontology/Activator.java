package org.universAAL.ontology;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator{
	public static BundleContext context=null;

	public void start(BundleContext context) throws Exception {
		Activator.context=context;
	}

	public void stop(BundleContext arg0) throws Exception {
	}

}
