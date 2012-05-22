package org.universaal.ontology.disease.owl;

import org.universAAL.middleware.owl.ManagedIndividual;
/*conjunto de mecanismos biol�gicos, f�sicos o qu�micos que llevan a la producci�n de una enfermedad*/
public class Patogeny extends ManagedIndividual{
	
	public static final String MY_URI = DiseaseOntology.NAMESPACE
	+ "Patogeny";
	
	public Patogeny () {
	    super();
	  }
	  
	  public Patogeny (String uri) {
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
