/*
	Copyright 2008-2010 ITACA-TSB, http://www.tsb.upv.es
	Instituto Tecnologico de Aplicaciones de Comunicacion 
	Avanzadas - Grupo Tecnologias para la Salud y el 
	Bienestar (TSB)
	
	See the NOTICE file distributed with this work for additional 
	information regarding copyright ownership
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	  http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 */
package org.universAAL.agenda.remote;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.container.osgi.uAALBundleContainer;

public class Activator implements BundleActivator {

    public static BundleContext context = null;
    /**
     * {@link ModuleContext}
     */
    private static ModuleContext mcontext;
    public static SCallee guicallee = null;
    public static SCaller guicaller = null;
    public static ISubscriber guiinput = null;
    public static OPublisher guioutput = null;
    public static AgendaWebGUI gui = null;

    public void start(BundleContext arg0) throws Exception {
	BundleContext[] bc = { arg0 };
	mcontext = uAALBundleContainer.THE_CONTAINER.registerModule(bc);
	context = arg0;
	gui = new AgendaWebGUI();
	guioutput = new OPublisher(mcontext);
	guiinput = new ISubscriber(mcontext);
	guicaller = new SCaller(mcontext);
	guicallee = new SCallee(mcontext);
    }

    public void stop(BundleContext arg0) throws Exception {
	gui = null;
	guioutput = null;
	guiinput = null;
	guicaller = null;
	guicallee = null;
	context = null;
    }

}
