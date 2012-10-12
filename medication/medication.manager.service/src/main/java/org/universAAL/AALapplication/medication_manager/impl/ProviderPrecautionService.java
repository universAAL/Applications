package org.universAAL.AALapplication.medication_manager.impl;

import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.middleware.owl.SimpleOntology;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.rdf.impl.ResourceFactoryImpl;
import org.universAAL.middleware.service.owls.profile.ServiceProfile;
import org.universAAL.ontology.medMgr.Precaution;

/**
 * @author George Fournadjiev
 */
public final class ProviderPrecautionService extends Precaution {

  public static final String PRECAUTION_SERVER_NAMESPACE =
      "http://ontologies.universAAL.com/PrecautionServer.owl#";

  public static final String MY_URI = PRECAUTION_SERVER_NAMESPACE + "PrecautionService";

  public static final String SERVICE_GET_PRECAUTION = PRECAUTION_SERVER_NAMESPACE + "getPrecaution";

  static final String OUTPUT_PRECAUTION_SIDEEFFECT = PRECAUTION_SERVER_NAMESPACE + "sideeffect";
  static final String OUTPUT_PRECAUTION_INCOMPLIANCE = PRECAUTION_SERVER_NAMESPACE + "incompliance";

  static final ServiceProfile[] profiles = new ServiceProfile[1];

  static {

    //Register

    OntologyManagement.getInstance().register(
        new SimpleOntology(MY_URI, Precaution.MY_URI,
            new ResourceFactoryImpl() {
              @Override
              public Resource createInstance(String classURI,
                                             String instanceURI, int factoryIndex) {
                return new ProviderPrecautionService(instanceURI);
              }
            }));

    String[] ppPrecationSideeffect = new String[]{Precaution.PROP_SIDEEFFECT};

    ProviderPrecautionService getPrecation =
        new ProviderPrecautionService(SERVICE_GET_PRECAUTION);

    getPrecation.addOutput(OUTPUT_PRECAUTION_SIDEEFFECT, Precaution.MY_URI, 1, 1, ppPrecationSideeffect);

    String[] ppPrecationIncompliance = new String[]{Precaution.PROP_INCOMPLIANCE};

    getPrecation.addOutput(OUTPUT_PRECAUTION_INCOMPLIANCE, Precaution.MY_URI, 1, 1, ppPrecationIncompliance);

    profiles[0] = getPrecation.myProfile;

  }

  private ProviderPrecautionService(String uri) {
    super(uri);
  }

  public String getClassURI() {
    return MY_URI;
  }


}

