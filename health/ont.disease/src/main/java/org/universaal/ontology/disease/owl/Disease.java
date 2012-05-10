package org.universaal.ontology.disease.owl;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;

import org.universAAL.middleware.owl.ManagedIndividual;
import org.universAAL.middleware.service.owl.Service;
import org.universAAL.ontology.phThing.Device;

public class Disease extends ManagedIndividual {
  public static final String MY_URI = DiseaseOntology.NAMESPACE
    + "Disease";


  public Disease () {
    super();
  }
  
  public Disease (String uri) {
    super(uri);
  }

  public String getClassURI() {
    return MY_URI;
  }
  public int getPropSerializationType(String arg0) {
	// TODO Implement or if for Device subclasses: remove 
	return 0;
  }

  public boolean isWellFormed() {
	return true ;
  }
}
