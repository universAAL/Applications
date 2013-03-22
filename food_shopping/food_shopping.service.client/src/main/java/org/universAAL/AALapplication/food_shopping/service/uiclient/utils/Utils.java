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

package org.universAAL.AALapplication.food_shopping.service.uiclient.utils;

import org.universAAL.AALapplication.food_shopping.service.uiclient.SharedResources;
import org.universAAL.middleware.container.utils.LogUtils;

/**
 * @author dimokas
 * 
 */

public class Utils {

	
	public static void println(String msg) {
	    LogUtils.logDebug(SharedResources.moduleContext, Utils.class, "Food and Shopping", new Object[]{msg},null);
	}
	
}
