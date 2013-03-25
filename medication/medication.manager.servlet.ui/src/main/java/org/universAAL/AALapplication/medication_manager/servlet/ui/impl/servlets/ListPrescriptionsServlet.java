package org.universAAL.AALapplication.medication_manager.servlet.ui.impl.servlets;

import org.universAAL.AALapplication.medication_manager.persistence.layer.PersistentService;
import org.universAAL.AALapplication.medication_manager.persistence.layer.entities.Person;
import org.universAAL.AALapplication.medication_manager.servlet.ui.impl.parser.script.forms.ListPrescriptionsScriptForm;
import org.universAAL.AALapplication.medication_manager.servlet.ui.impl.parser.script.forms.ScriptForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.universAAL.AALapplication.medication_manager.servlet.ui.impl.Activator.*;
import static org.universAAL.AALapplication.medication_manager.servlet.ui.impl.Util.*;

/**
 * @author George Fournadjiev
 */
public final class ListPrescriptionsServlet extends BaseServlet {

  private final Object lock = new Object();
  private DisplayServlet displayServlet;

  private static final String LIST_PRESCRIPTION_HTML_FILE_NAME = "prescriptions.html";

  public ListPrescriptionsServlet() {
    super(LIST_PRESCRIPTION_HTML_FILE_NAME);
  }

  public void setDisplayServlet(DisplayServlet displayServlet) {
    this.displayServlet = displayServlet;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    synchronized (lock) {
      isServletSet(displayServlet, "displayServlet");
      HttpSession session = req.getSession(true);
      Person person = (Person) session.getAttribute(LOGGED_DOCTOR);
      if (person == null) {
        displayServlet.doGet(req, resp);
        return;
      }

      handleResponse(resp);
    }
  }

  private void handleResponse(HttpServletResponse resp) throws IOException {
    try {
      PersistentService persistentService = getPersistentService();
      ScriptForm scriptForm = new ListPrescriptionsScriptForm(persistentService);
      sendResponse(resp, scriptForm);
    } catch (Exception e) {
      sendErrorResponse(resp, e);
    }
  }


}
