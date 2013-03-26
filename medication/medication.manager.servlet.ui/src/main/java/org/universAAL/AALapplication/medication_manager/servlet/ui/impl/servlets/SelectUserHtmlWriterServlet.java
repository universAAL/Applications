package org.universAAL.AALapplication.medication_manager.servlet.ui.impl.servlets;

import org.universAAL.AALapplication.medication_manager.persistence.layer.PersistentService;
import org.universAAL.AALapplication.medication_manager.persistence.layer.entities.Person;
import org.universAAL.AALapplication.medication_manager.servlet.ui.impl.parser.script.forms.ScriptForm;
import org.universAAL.AALapplication.medication_manager.servlet.ui.impl.parser.script.forms.UserSelectScriptForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.universAAL.AALapplication.medication_manager.servlet.ui.impl.Activator.*;
import static org.universAAL.AALapplication.medication_manager.servlet.ui.impl.Util.*;

/**
 * @author George Fournadjiev
 */
public final class SelectUserHtmlWriterServlet extends BaseHtmlWriterServlet {

  private final Object lock = new Object();
  private DisplayLoginHtmlWriterServlet displayServlet;

  private static final String USER_HTML_FILE_NAME = "user.html";

  public SelectUserHtmlWriterServlet(SessionTracking sessionTracking) {
    super(USER_HTML_FILE_NAME, sessionTracking);
  }

  public void setDisplayServlet(DisplayLoginHtmlWriterServlet displayServlet) {
    this.displayServlet = displayServlet;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    synchronized (lock) {
      isServletSet(displayServlet, "displayServlet");

      Session httpSession = getSession(req);
      Person doctor = (Person) httpSession.getAttribute(LOGGED_DOCTOR);

      if (doctor == null) {
        displayServlet.doGet(req, resp);
        return;
      }

      handleResponse(resp, doctor);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }


  private void handleResponse(HttpServletResponse resp, Person doctor) throws IOException {
    try {
      PersistentService persistentService = getPersistentService();
      ScriptForm scriptForm = new UserSelectScriptForm(persistentService, doctor);
      sendResponse(resp, scriptForm);
    } catch (Exception e) {
      sendErrorResponse(resp, e);
    }
  }


}
