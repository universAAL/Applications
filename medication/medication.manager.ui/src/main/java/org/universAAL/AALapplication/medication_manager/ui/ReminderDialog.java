package org.universAAL.AALapplication.medication_manager.ui;

import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.owl.supply.LevelRating;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.ui.UICaller;
import org.universAAL.middleware.ui.UIRequest;
import org.universAAL.middleware.ui.UIResponse;
import org.universAAL.middleware.ui.owl.PrivacyLevel;
import org.universAAL.middleware.ui.rdf.Form;
import org.universAAL.middleware.ui.rdf.Label;
import org.universAAL.middleware.ui.rdf.SimpleOutput;
import org.universAAL.middleware.ui.rdf.Submit;
import org.universAAL.ontology.profile.User;

import java.util.Locale;

public class ReminderDialog extends UICaller {

  private ModuleContext moduleContext;

  private static final String CLOSE_BUTTON = "closeButton";
  private static final String INFO_BUTTON = "reminderButton";

  public ReminderDialog(ModuleContext context) {
    super(context);
    moduleContext = context;
  }

  @Override
  public void communicationChannelBroken() {
  }

  @Override
  public void dialogAborted(String dialogID) {
  }

  @Override
  public void handleUIResponse(UIResponse input) {
    if (CLOSE_BUTTON.equals(input.getSubmissionID())) {
      System.out.println("close");
    } else if (INFO_BUTTON.equals(input.getSubmissionID())) {
      showRequestMedicationInfoDialog(input.getUser());
    } else {
      System.out.println("unknown");
    }

  }

  private void showRequestMedicationInfoDialog(Resource user) {
    System.out.println("info");
    RequestMedicationInfoDialog dialog = new RequestMedicationInfoDialog(moduleContext);
    dialog.showDialog((User) user);
  }

  public void showDialog(User inputUser) {
    Form f = Form.newDialog("Medication Manager UI", new Resource());


    //start of the form model

    String reminderMessage = getUserfriendlyName(inputUser) + ",\nit is time to get your medicine.";

    new SimpleOutput(f.getIOControls(), null, null, reminderMessage);
    //...
    new Submit(f.getSubmits(), new Label("Close", null), CLOSE_BUTTON);
    new Submit(f.getSubmits(), new Label("Info", null), INFO_BUTTON);
    //stop of form model
    UIRequest req = new UIRequest(inputUser, f, LevelRating.none, Locale.ENGLISH, PrivacyLevel.insensible);
    this.sendUIRequest(req);
  }

  private String getUserfriendlyName(User inputUser) {
    String fullUserUriName = inputUser.toString();
    int index = fullUserUriName.lastIndexOf('#');
    String firstLetter = fullUserUriName.substring(index + 1).toUpperCase();
    return firstLetter.charAt(0) + fullUserUriName.substring(index + 2);
  }

}
