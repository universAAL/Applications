package org.universAAL.ontology.drools.service;

import java.util.Hashtable;

import org.universAAL.middleware.owl.ManagedIndividual;
import org.universAAL.middleware.owl.Restriction;
import org.universAAL.middleware.service.owl.Service;
import org.universAAL.ontology.drools.Consequence;
import org.universAAL.ontology.drools.Fact;
import org.universAAL.ontology.drools.FactProperty;
import org.universAAL.ontology.drools.Rule;

public class DroolsService extends Service {

    public static final String MY_NAMESPACE;

    public static final String MY_URI;
    public static final String FACT;
    public static final String RULE;
    public static final String CONSEQUENCE;
    public static Hashtable restrictions = new Hashtable();

    static {
	MY_NAMESPACE = "http://ontology.universAAL.org/Drools.Service.owl#";
	MY_URI = FactProperty.MY_NAMESPACE + "DroolsService";

	FACT = MY_NAMESPACE + "hasFact";
	RULE = MY_NAMESPACE + "hasRule";
	CONSEQUENCE = MY_NAMESPACE + "hasConsequence";

	register(DroolsService.class);

	addRestriction(Restriction.getAllValuesRestriction(FACT, Fact.MY_URI),
		new String[] { FACT }, restrictions);
	addRestriction(Restriction.getAllValuesRestriction(RULE, Rule.MY_URI),
		new String[] { RULE }, restrictions);
	addRestriction(Restriction.getAllValuesRestriction(CONSEQUENCE,
		Consequence.MY_URI), new String[] { CONSEQUENCE }, restrictions);
    }

    // In this method you must return the restrictions on the property you are
    // asked for
    public static Restriction getClassRestrictionsOnProperty(String propURI) {
	if (propURI == null)
	    return null;
	Object r = restrictions.get(propURI);
	if (r instanceof Restriction)
	    return (Restriction) r;
	return Service.getClassRestrictionsOnProperty(propURI);
    }

    // This method is used by the system to handle the ontologies. It returns
    // the URIs of all properties used in this concept.
    public static String[] getStandardPropertyURIs() {
	// First get property URIs of your parent concept (in this case we have
	// none, so we use ManagedIndividual)
	String[] inherited = ManagedIndividual.getStandardPropertyURIs();
	String[] toReturn = new String[inherited.length + 3];// Make sure you
	// increase the size by the number of properties declared in your
	// concept!
	int i = 0;
	// With this we copy the properties of the parent...
	while (i < inherited.length) {
	    toReturn[i] = inherited[i];
	    i++;
	}
	// ...and with this we add the properties declared in this concept
	toReturn[i++] = FACT;
	toReturn[i++] = RULE;
	toReturn[i] = CONSEQUENCE;
	// Now we have all the property URIs of the concept, both inherited and
	// declared by it.
	return toReturn;
    }

    public static String getRDFSComment() {
	return "A comment describing what this concept is used for";
    }

    public static String getRDFSLabel() {
	return "Human readable ID for the concept. e.g: 'My Concept'";
    }

    public DroolsService(String uri) {
	super(uri);
    }

    // This method is used for serialization purposes, to restrict the amount of
    // information to serialize when forwarding it among nodes.
    // For each property you must return one of PROP_SERIALIZATION_FULL,
    // REDUCED, OPTIONAL or UNDEFINED.
    // Refer to their javadoc to see what they mean.
    public int getPropSerializationType(String propURI) {
	// In this case we serialize everything. It is up to you to define what
	// is important to be serialized and what is expendable in your concept.
	return PROP_SERIALIZATION_FULL;
    }

    protected Hashtable getClassLevelRestrictions() {
	return restrictions;
    }

    public boolean isWellFormed() {
	return true;
    }

}
