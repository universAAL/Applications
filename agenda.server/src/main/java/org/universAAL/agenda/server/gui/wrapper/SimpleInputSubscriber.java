/**
 * 
 */
package org.universAAL.agenda.server.gui.wrapper;



import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;
import org.universAAL.agenda.server.Activator;
import org.universAAL.middleware.input.InputEvent;
import org.universAAL.middleware.input.InputSubscriber;

/**
 * @author KAgnantis
 *
 */
public class SimpleInputSubscriber extends InputSubscriber {
	
	SimpleInputSubscriber(BundleContext context) {
		super(context);
	}

	public void dialogAborted(String dialogID) {
		//TODO: check database/profile consistency?
	}

	/**
	 * @see org.persona.middleware.input.InputSubscriber#communicationChannelBroken()
	 */
	public void communicationChannelBroken() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.persona.middleware.input.InputSubscriber#handleInputEvent(org.persona.middleware.input.InputEvent)
	 */
	public void handleInputEvent(InputEvent event) {
		System.err.println(">>>got InputEvent in prof.server<<<");
		if (event.hasDialogInput()) {
			System.out.println("Dialog ID: " + event.getDialogID());
			String submissionID = event.getSubmissionID();
			if (submissionID == null) {
				Activator.log.log(LogService.LOG_WARNING, "InputSubscriber@ui.dm - handleInputEvent: submission ID null!");
				return;
			}

			
			if (SimpleOutputPublisher.SNOOZE_REMINDER.equals(submissionID)) {
				//get values and update profile
				System.out.println("Snooze reminder....");
				//do nothing
				return;
			}
			
			if (SimpleOutputPublisher.TURN_OFF_REMINDER.equals(submissionID)) {
				System.out.println("Turn off reminder...");
				//stopReminder
				try {
					String calendarURI = (String) event.getSubmittedData().getProperty(SimpleOutputPublisher.CALENDAR_URI);
					Integer eventId = (Integer) event.getSubmittedData().getProperty(SimpleOutputPublisher.EVENT_ID);
					Activator.getProvider().cancelReminder(calendarURI, eventId.intValue());
				} catch (ClassCastException cce) {
					Activator.log.log(LogService.LOG_ERROR, cce.getMessage());
				}
				
				return;
			}
		}
	}

	
	void subscribe(String dialogID) {
		addNewRegParams(dialogID);
	}

	void unsubscribe(String dialogID) {
		removeMatchingRegParams(dialogID);
	}

}
