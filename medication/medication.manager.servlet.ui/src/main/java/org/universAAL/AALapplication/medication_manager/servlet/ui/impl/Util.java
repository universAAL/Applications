package org.universAAL.AALapplication.medication_manager.servlet.ui.impl;

import org.universAAL.AALapplication.medication_manager.persistence.layer.entities.Person;
import org.universAAL.AALapplication.medication_manager.persistence.layer.entities.Role;

import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author George Fournadjiev
 */
public final class Util {


  public static final Person DOCTOR = new Person("Penchno", "uri", Role.PHYSICIAN, "us", "pa");
  public static final String LOGGED_DOCTOR = "Doctor";
  public static final String LOGIN_HTML_SERVLET_ALIAS = "/login.html";
  public static final String LOGIN_SERVLET_ALIAS = "/login";
  public static final String SELECT_USER_SERVLET_ALIAS = "/select_user";
  public static final String LIST_PRESCRIPTIONS_SERVLET_ALIAS = "/display_prescriptions";
  public static final String NEW_PRESCRIPTION_SERVLET_ALIAS = "/display_new_prescription";
  public static final String HANDLE_NEW_PRESCRIPTION_SERVLET_ALIAS = "/handle_new_prescription";
  public static final String LOGIN_ERROR = "LOGIN_ERROR";
  public static final String LOGIN_FILE_NAME = "login.html";

  private Util() {
  }

  public static void isServletSet(HttpServlet servlet, String servletName) {
    if (servlet == null) {
      throw new MedicationManagerServletUIException("The " + servletName + " is not set");
    }
  }

  public static String getHtml(String resourcePath) {
    InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(resourcePath);

    if (inputStream == null) {
      throw new MedicationManagerServletUIException("The resource: " + resourcePath + " cannot be found");
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

    return getHtmlText(br);
  }

  private static String getHtmlText(BufferedReader br) {
    StringBuffer sb = new StringBuffer();
    try {
      String line = br.readLine();
      while (line != null) {
        sb.append(line);
        sb.append('\n');
        line = br.readLine();
      }
    } catch (IOException e) {
      throw new MedicationManagerServletUIException(e);
    }

    return sb.toString();
  }

  public static void validateParameter(int parameter, String parameterName) {

    if (parameter <= 0) {
      throw new MedicationManagerServletUIException("The parameter : " +
          parameterName + " must be positive number");
    }

  }

  public static void validateParameter(Object parameter, String parameterName) {

    if (parameter == null) {
      throw new MedicationManagerServletUIException("The parameter : " + parameterName + " cannot be null");
    }

  }
}
