package org.universaal.ontology.messageManagement;

import java.util.Locale;

public class MotivationalMessagesDatabase {

	//Aqu� hay que pedirle al sistema, a partir de las properties
	//que est�n en configuraci�n, que me de la ruta de la carpeta de los idiomas
	
	
	  public static final String ES = "ES";
	  public static final String EN = "EN";
	
	
	public static String getDBRute(Locale language){
		
		switch(language){
		case Locale.ENGLISH:
			return "";
		default:
		return null;
	
		}
}
}
