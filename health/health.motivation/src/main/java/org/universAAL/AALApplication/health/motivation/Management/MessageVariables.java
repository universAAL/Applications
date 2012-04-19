package org.universAAL.AALApplication.health.motivation.Management;

public class MessageVariables {
	
public static String[] replaceVariables(String[] variable){
		
		String[] values = new String[variable.length];
		for (int i=0;i<variable.length;i++){
			
			if (variable[i].equals("caregiverRol"))
				values[i] = "Doctor";
			
			else if(variable[i].equals("treatmentName"))
				values[i] = "Nombredeltratamiento";
			
			else if(variable[i].equals("username"))
				values[i] = "Pepe";
			
			else if(variable[i].equals("treatmentDefinition"))
				values[i] = "Definici�n del tratamiento";
			
			else if(variable[i].equals("treatmentPuropose"))
				values[i] = "Prop�sito del tratamiento";
			
			else{
				System.out.println("Par�metro no definido");
				values[i] = null;
			}
		
		}
		return values;
	}

}
