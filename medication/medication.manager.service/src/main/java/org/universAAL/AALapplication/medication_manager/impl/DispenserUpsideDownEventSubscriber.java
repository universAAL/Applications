package org.universAAL.AALapplication.medication_manager.impl;

import org.universAAL.AALapplication.medication_manager.ui.DispenserUpsideDownDialog;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextSubscriber;
import org.universAAL.middleware.owl.MergedRestriction;
import org.universAAL.ontology.medMgr.DispenserUpsideDown;
import org.universAAL.ontology.medMgr.DueIntake;
import org.universAAL.ontology.medMgr.MyDeviceUserMappingDatabase;
import org.universAAL.ontology.profile.User;

/**
 * @author George Fournadjiev
 */
public final class DispenserUpsideDownEventSubscriber extends ContextSubscriber {

  private final ModuleContext moduleContext;

  private static ContextEventPattern[] getContextEventPatterns() {
    ContextEventPattern cep = new ContextEventPattern();

    MergedRestriction mr = MergedRestriction.getAllValuesRestrictionWithCardinality(
        ContextEvent.PROP_RDF_SUBJECT, DispenserUpsideDown.MY_URI, 1, 1);

    cep.addRestriction(mr);

    return new ContextEventPattern[]{cep};

  }

  public DispenserUpsideDownEventSubscriber(ModuleContext context) {
    super(context, getContextEventPatterns());

    this.moduleContext = context;
  }

  public void communicationChannelBroken() {
    //"Not implemented yet"
  }

  public void handleContextEvent(ContextEvent event) {
    Log.info("Received event of type %s", getClass(), event.getType());

    DispenserUpsideDown dispenserUpsideDown = (DispenserUpsideDown) event.getRDFSubject();

    String deviceId = dispenserUpsideDown.getDeviceId();

    Log.info("DeviceId %s", getClass(), deviceId);

    User user = MyDeviceUserMappingDatabase.getUser(deviceId);

    DispenserUpsideDownDialog dispenserUpsideDownDialog =
        new DispenserUpsideDownDialog(moduleContext);

    dispenserUpsideDownDialog.showDialog(user);

  }
}
