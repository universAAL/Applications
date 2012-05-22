package org.universaal.ontology.disease.owl;

import org.universAAL.middleware.owl.ManagedIndividual;
/*Manifestaci�n de la enfermedad*/
public class Sympthom extends ManagedIndividual{
	public static final String MY_URI = DiseaseOntology.NAMESPACE
	+ "Sympthom";
	
	
	public Sympthom () {
	    super();
	  }
	  
	  public Sympthom (String uri) {
	    super(uri);
	  }

	  public String getClassURI() {
	    return MY_URI;
	  }
	  public int getPropSerializationType(String arg0) {
		  return PROP_SERIALIZATION_FULL;
	  }

	  public boolean isWellFormed() {
		  return true; 
	  }
	
}
