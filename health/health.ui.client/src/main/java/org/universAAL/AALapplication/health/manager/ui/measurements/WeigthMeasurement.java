package org.universAAL.AALapplication.health.manager.ui.measurements;

import org.universAAL.AALapplication.health.manager.ui.AbstractHealthForm;
import org.universAAL.AALapplication.health.manager.ui.MainMenu;
import org.universAAL.AALapplication.health.manager.ui.MeasurementTypeForm;
import org.universAAL.middleware.owl.supply.LevelRating;
import org.universAAL.middleware.rdf.PropertyPath;
import org.universAAL.middleware.service.DefaultServiceCaller;
import org.universAAL.middleware.service.ServiceCaller;
import org.universAAL.middleware.service.ServiceRequest;
import org.universAAL.middleware.ui.UIResponse;
import org.universAAL.middleware.ui.owl.PrivacyLevel;
import org.universAAL.middleware.ui.rdf.Form;
import org.universAAL.middleware.ui.rdf.InputField;
import org.universAAL.middleware.ui.rdf.Label;
import org.universAAL.middleware.ui.rdf.Submit;
import org.universAAL.ontology.health.owl.services.PerformedSessionManagementService;
import org.universAAL.ontology.healthmeasurement.owl.PersonWeight;
import org.universAAL.ontology.measurement.Measurement;

public class WeigthMeasurement extends AbstractHealthForm{

	//TODO: internationalization!
		
		static final LevelRating PRIORITY = LevelRating.low;
		static final PrivacyLevel PRIVACY = PrivacyLevel.insensible;
		private static final String DONE_ICON = null;
		private static final String DONE_LABEL = "Done";
		private static final String CANCEL_LABEL = "Cancel";
		private static final String CANCEL_ICON = null;
		private PersonWeight measurement;
		
	
	public WeigthMeasurement(AbstractHealthForm ahf, PersonWeight hm) {
		super(ahf);
		this.measurement = hm;
	}

	@Override
	public void handleUIResponse(UIResponse input) {
		measurement = (PersonWeight) input.getSubmittedData();
		if (input.getSubmissionID().startsWith(DONE_LABEL)){
			// service call add Performed Session.
			ServiceCaller sc = new DefaultServiceCaller(owner);
			ServiceRequest sr = new ServiceRequest(new PerformedSessionManagementService(null), inputUser);
			sr.addAddEffect(new String[]{PerformedSessionManagementService.PROP_MANAGES_SESSION}, measurement);
			sr.addValueFilter(new String[]{PerformedSessionManagementService.PROP_ASSISTED_USER}, targetUser);
			sc.call(sr);
			new MainMenu(this).show();
		}
		if (input.getSubmissionID().startsWith(CANCEL_LABEL)){
			//Back
			new MeasurementTypeForm(this).show();
		}
	}
	
	public void show() {
		// Create Dialog
		Form f = Form.newDialog("Weight", measurement);
		//TODO: add units from actual prefix and unit.
		InputField i = new InputField(f.getIOControls(), new Label("Weight ", null),
				new PropertyPath(PropertyPath.generateAnonURI(), true, new String[]{Measurement.PROP_VALUE}),null, Float.valueOf(64.5f));
		i.setHelpString("Insert Your Weight in Kg.");
		i.setHintString("64.5");
		new Submit(f.getSubmits(), 
				new Label(DONE_LABEL, DONE_ICON),
				DONE_LABEL);
		new Submit(f.getSubmits(), 
				new Label(CANCEL_LABEL, CANCEL_ICON),
				CANCEL_LABEL);

		sendForm(f);
	}
	
//	public void ShowIncorrectMessage(){
//		Form f = Form.newMessage("Weight", null);
//		new SimpleOutput(f.getIOControls(), null, null, "Your Measure is not correct");
//		sendForm(f);		
//	}
	
}
