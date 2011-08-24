/*	
	Copyright 2010-2014 UPM http://www.upm.es
	Universidad Politécnica de Madrdid
	
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
package org.universAAL.AALapplication.health.treat.logger;

/**
 * Interface for Treatment Storage and retrieving
 * Implementations of this interface go in {@link org.universAAL.AALapplication.health.treat.logger.impl}
 * Package; this way the actual storage of treatments can be expanded to other methods and service providers
 * can select from these methods the one that fits best and has best performance, or they can implement their
 * own storage method.
 * 
 * @author amedrano
 * 
 * TODO decide atomic operations
 */
public interface TreatmentLoggerInterface {

}
