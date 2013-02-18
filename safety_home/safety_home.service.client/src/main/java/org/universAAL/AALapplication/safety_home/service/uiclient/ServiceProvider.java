/*****************************************************************************************
 * Copyright 2012 CERTH, http://www.certh.gr - Center for Research and Technology Hellas
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
 *****************************************************************************************/

package org.universAAL.AALapplication.safety_home.service.uiclient;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.service.CallStatus;
import org.universAAL.middleware.service.ServiceCall;
import org.universAAL.middleware.service.ServiceCallee;
import org.universAAL.middleware.service.ServiceResponse;
import org.universAAL.middleware.service.owl.InitialServiceDialog;
import org.universAAL.middleware.service.owls.profile.ServiceProfile;
import org.universAAL.ontology.Safety.SafetyManagement;

/**
 * @author dimokas
 * 
 */
public class ServiceProvider extends ServiceCallee {

    public static final String UI_SAFETY_CLIENT_NAMESPACE = SharedResources.CLIENT_SAFETY_UI_NAMESPACE
			+ "ServiceProvider#";
	public static final String START_URI = UI_SAFETY_CLIENT_NAMESPACE
			+ "MainDialog";

    ServiceProvider(ModuleContext mc) {
	super(mc, new ServiceProfile[] { InitialServiceDialog
		.createInitialDialogProfile(SafetyManagement.MY_URI,
				"http://www.certh.gr",
				"Safety and Security UI Client", START_URI) });
    }

    /**
     * @see org.persona.middleware.service.ServiceCallee#communicationChannelBroken()
     */
    public void communicationChannelBroken() {
	// TODO Auto-generated method stub
    }

    /**
     * @see org.persona.middleware.service.ServiceCallee#handleCall(org.persona.middleware.service.ServiceCall)
     */
    public ServiceResponse handleCall(ServiceCall call) {
	if (call != null) {
	    String operation = call.getProcessURI();
	    if (operation != null && operation.startsWith(START_URI)) {
			System.out.println("-- Safety and Security UI Client Main Menu --");
			SharedResources.uIProvider.startMainDialog();
			ServiceResponse sr = new ServiceResponse(CallStatus.succeeded);
			return sr;
	    }
	}
	return null;
    }
}
