/*******************************************************************************
 * Copyright 2012 , http://www.prosyst.com - ProSyst Software GmbH
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


package org.universAAL.AALapplication.medication_manager.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.universAAL.AALapplication.medication_manager.configuration.ConfigurationProperties;
import org.universAAL.AALapplication.medication_manager.persistence.layer.PersistentService;
import org.universAAL.AALapplication.medication_manager.providers.MissedIntakeContextProvider;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.container.osgi.uAALBundleContainer;

/**
 * @author George Fournadjiev
 */
public class Activator implements BundleActivator {

  public static ModuleContext mc;
  public static BundleContext bundleContext;
  private static ServiceTracker configurationPropertiesServiceTracker;
  private static ServiceTracker persistenceServiceTracker;

  /*
    * (non-Javadoc)
    * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
    */
  public void start(final BundleContext context) throws Exception {

    bundleContext = context;

    mc = uAALBundleContainer.THE_CONTAINER
        .registerModule(new Object[]{context});
    Log.info("Starting %s", getClass(), "Starting the Medication Service");

    configurationPropertiesServiceTracker = new ServiceTracker(context, ConfigurationProperties.class.getName(), null);
    persistenceServiceTracker = new ServiceTracker(context, PersistentService.class.getName(), null);

    configurationPropertiesServiceTracker.open();
    persistenceServiceTracker.open();

    new Thread() {
      public void run() {
        new PrecautionProvider(mc);
        MissedIntakeContextProvider missedIntakeContextProvider = new MissedIntakeContextProvider(mc);
        registerService(missedIntakeContextProvider, context);
        MissedIntakeEventSubscriber missedIntakeEventSubscriber = new MissedIntakeEventSubscriber(mc);
        new DueIntakeReminderEventSubscriber(mc, missedIntakeContextProvider);
        new DispenserUpsideDownEventSubscriber(mc);
      }
    }.start();

  }

  private void registerService(Object service, BundleContext context) {

    context.registerService(service.getClass().getName(), service, null);

  }

  /*
    * (non-Javadoc)
    * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
    */
  public void stop(BundleContext context) throws Exception {

    bundleContext = null;
  }

  public static ConfigurationProperties getConfigurationProperties() {
    if (configurationPropertiesServiceTracker == null) {
      throw new MedicationManagerException("The ConfigurationProperties ServiceTracker is not set");
    }
    ConfigurationProperties service = (ConfigurationProperties) configurationPropertiesServiceTracker.getService();
    if (service == null) {
      throw new MedicationManagerException("The ConfigurationProperties is missing");
    }

    return service;
  }

  public static PersistentService getPersistentService() {
    if (persistenceServiceTracker == null) {
      throw new MedicationManagerException("The PersistentService ServiceTracker is not set");
    }
    PersistentService service = (PersistentService) persistenceServiceTracker.getService();
    if (service == null) {
      throw new MedicationManagerException("The PersistentService is missing");
    }

    return service;
  }

}
