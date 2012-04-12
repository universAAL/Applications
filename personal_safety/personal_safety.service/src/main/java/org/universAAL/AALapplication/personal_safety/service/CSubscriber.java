/*
	Copyright 2008-2010 ITACA-TSB, http://www.tsb.upv.es
	Instituto Tecnologico de Aplicaciones de Comunicacion 
	Avanzadas - Grupo Tecnologias para la Salud y el 
	Bienestar (TSB)
	
	See the NOTICE file distributed with this work for additional 
	information regarding copyright ownership
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	  http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 */
package org.universAAL.AALapplication.personal_safety.service;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextSubscriber;
import org.universAAL.middleware.owl.Restriction;
import org.universAAL.middleware.owl.supply.LevelRating;
import org.universAAL.middleware.util.Constants;
import org.universAAL.ontology.phThing.PhysicalThing;
import org.universAAL.ontology.profile.User;
import org.universAAL.ontology.risk.PanicButton;
import org.universAAL.ontology.location.*;
/**
 * @author <a href="mailto:alfiva@itaca.upv.es">Alvaro Fides Valero</a>
 *
 */
public class CSubscriber extends ContextSubscriber {
	
	private final static Logger log=LoggerFactory.getLogger(CSubscriber.class);
	private static Hashtable locationTimes=new Hashtable();
	private static Hashtable batteryTimes=new Hashtable();
	private static Timer roomWatch;
	private static boolean roomTimerEnabled=false;//Preload to avoid reading props at every location event

	protected CSubscriber(ModuleContext context) {
		super(context, getPermanentSubscriptions());
		Enumeration locs=Main.getProperties().keys();
		log.debug("Preloading location timers");
		//Preloads to avoid reading props at every location event
		roomTimerEnabled=Boolean.parseBoolean(Main.getProperties().getProperty(Main.RISKENABLE,"false"));
		if(roomTimerEnabled){
			for (;locs.hasMoreElements();){
				String locProp=(String)locs.nextElement();
				if(locProp.startsWith("RISK.Room")){
					locationTimes.put(locProp.split("@")[1], Main.getProperties().getProperty(locProp).split(","));
				}
			}
			roomWatch=new Timer("Risk_LocationTimer");
		}
	}

	private static ContextEventPattern[] getPermanentSubscriptions() {
		ContextEventPattern[] ceps = new ContextEventPattern[3];

		ceps[0]=new ContextEventPattern();
		ceps[0].addRestriction(Restriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, User.MY_URI));
		ceps[0].addRestriction(Restriction.getFixedValueRestriction(ContextEvent.PROP_RDF_PREDICATE, PhysicalThing.PROP_PHYSICAL_LOCATION));
		
		ceps[1]=new ContextEventPattern();
		ceps[1].addRestriction(Restriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, PanicButton.MY_URI));
		ceps[1].addRestriction(Restriction.getFixedValueRestriction(ContextEvent.PROP_RDF_PREDICATE, PanicButton.PROP_ACTIVATED));

		ceps[2]=new ContextEventPattern();
		ceps[2].addRestriction(Restriction.getAllValuesRestriction(ContextEvent.PROP_RDF_SUBJECT, PanicButton.MY_URI));
		ceps[2].addRestriction(Restriction.getFixedValueRestriction(ContextEvent.PROP_RDF_PREDICATE, PanicButton.PROP_BATTERY_LEVEL));

		return ceps;
	}

	public void communicationChannelBroken() {
		// TODO Auto-generated method stub

	}

	public void handleContextEvent(ContextEvent event) {
		log.info("Received a Context Event in the riskstub: {} {} {}",
				new Object[]{event.getRDFSubject(),event.getRDFPredicate(),event.getRDFObject()});
		String pred = event.getRDFPredicate();

		// Triggered by ceps[0] --- location
		if (pred.equals(PhysicalThing.PROP_PHYSICAL_LOCATION)) {
			if(roomTimerEnabled){
				Location loc=(Location)event.getRDFObject();
				String uri=loc.getURI();
			    String suffix=uri.substring(uri.indexOf("#")+1);
			    String[] times=(String[])locationTimes.get(suffix);
			    
				if(times==null)times=(String[])locationTimes.get("Default");

				Calendar now=Calendar.getInstance();
				int hournow=now.get(Calendar.HOUR_OF_DAY);
				String finaltime="0";
				for (int i=0;i<times.length;i++){
					if(hournow>=Integer.parseInt(times[i].split(":")[0])){
						finaltime=times[i].split(":")[1];
					}else{
						break;
					}
				}

				User user=(User)event.getRDFSubject();
				roomWatch.cancel();
				long timetask=Long.parseLong(finaltime);
				if(timetask!=0){
					roomWatch=new Timer("Risk_LocationTimer");
					log.debug("Scheduling risk timer for {} at {} minutes from now",new Object[]{suffix,finaltime});
					roomWatch.schedule(new LocationRiskTask(user,suffix), timetask*60000);
				}else{
					log.debug("At this time this location does not have scheduled risk timer");
				}
			}
		}

		// Triggered by ceps[1] --- panic
		else if (pred.equals(PanicButton.PROP_ACTIVATED)) {
			User user = (User) ((PanicButton) event.getRDFSubject()).getPressedBy();
			if(((Boolean)event.getRDFObject()).booleanValue()){
				if (user == null)
					user = new User(
							Constants.uAAL_MIDDLEWARE_LOCAL_ID_PREFIX
							+ ((Main.getProperties().getProperty(
									Main.USER, "Saied")).split("-")[0]
									                                     .toLowerCase()));
				if(Boolean.parseBoolean(Main.getProperties().getProperty(Main.SMSENABLE,"false"))){
					boolean sent=Main.rcaller.sendPanicButtonSMSText();
					Main.routput.showSMSForm(user, sent);
				}
				if(Boolean.parseBoolean(Main.getProperties().getProperty(Main.VCENABLE,"false"))){
					boolean call=Main.rcaller.startVideoCall();
					if(!call)Main.routput.showNoVCForm(user);
				}
			}else{
				log.error("Received an unhandled Panic Button event: {}",event);
			}
		}
		
		// Triggered by ceps[2] --- battery
		else if (pred.equals(PanicButton.PROP_BATTERY_LEVEL)) {
			String uri = ((PanicButton) event.getRDFSubject()).getURI();
			LevelRating level=(LevelRating)event.getRDFObject();
			if(level!=null){
				if(level.equal(LevelRating.low)){
					Long time=((Long)batteryTimes.get(uri));
					if(time!=null){
						if(System.currentTimeMillis()-time.longValue()>97200000){
							User user = (User) ((PanicButton) event.getRDFSubject()).getPressedBy();
							batteryTimes.put(uri, new Long(System.currentTimeMillis()));
							Main.routput.showBatteryForm(user);
						}
					}else{
						batteryTimes.put(uri, new Long(System.currentTimeMillis()));
					}
				}
			}else{
				log.error("Received an unhandled Panic Button battery event: {}",event);
			}
		}else{
			log.error("Received an unhandled event: {}",event);
		}
	}
	
	private static class LocationRiskTask extends TimerTask{
		public static User user;
		//public static String location;
		public LocationRiskTask(User usr,String loc){
			user=usr;
			//location=loc;
		}
		public void run() {
			log.debug("Triggered risk situation");
			Main.routput.showButtonScreenForm(user);
		}
	}
}
