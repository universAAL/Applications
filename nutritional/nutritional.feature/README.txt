This file should be removed in the future:

1) install this bundle
2)run Karaf
3)add and install the MW2.0 featue: 

--> features:addurl mvn:org.universAAL.middleware/mw.karaf.feature.universAAL.osgi/1.3.2-SNAPSHOT/xml/features
--> features:install universAA2.0

4)Add the LDDI feature:

--> features:addurl mvn:org.universAAL.lddi/lddi.karaf.feature/1.3.2-SNAPSHOT/xml/features
--> features:install UI



Soon an official guide. Now you can check http://forge.universaal.org/wiki/middleware:MW2.0_Building_Block#Run_the_MW2.0

