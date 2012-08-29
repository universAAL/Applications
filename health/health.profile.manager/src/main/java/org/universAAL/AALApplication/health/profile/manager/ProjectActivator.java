/*******************************************************************************
 * Copyright 2012 UPM, http://www.upm.es 
 * Universidad Polit�cnica de Madrid
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.universAAL.AALApplication.health.profile.manager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.container.osgi.uAALBundleContainer;

public class ProjectActivator implements BundleActivator {

	static ModuleContext context;

	private static final String MODULE = "health.profile.manager";
	
	public void start(BundleContext arg0) throws Exception {	
		context = uAALBundleContainer.THE_CONTAINER
                .registerModule(new Object[] {arg0});	
		context.logDebug(MODULE, "Initialising Project", null);

		/*
		 * uAAL stuff
		 */
		context.logInfo(MODULE, "Project started", null);
	}


	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
