This project contains custom XPATH functions.
Whenever the functions are modified and rebuilt the following steps must be performed.

1) Use the Project->Deploy command to create a .jar file
2) Register the jar file with JDeveloper so the changes to the functions
   are visible in the XPATH Expression editor and XLST Mapper.
   Under JDeveloper Main menu   Tools->Preferences->SOA set path to jar
   Then restart Jdeveloper
3) Register the jar file with the SOA Middlware Home / Server runtime.
   Copy the jar file to the  ${MW_HOME}/Oracle_SOA1/soa/modules/oracle.soa.ext_11.1.1/
   Execute the build.xml file in the directory using Ant to rebuild the registry.
   Restart the Managed Servers that utlize the xpath function jar or redeploy the oracle.soa.ext shared library
   
   The "registry" is a shared library referenced by the soa-infra runtime.
   The oracle.soa.ext.jar in the directory is deployed as a shared library in WLS.
   The oracle.soa.ext.jar is referenced by soa-infra using a <library> ref in the soa-infra weblogic.application.xml
   The MANIFEST.MF in the oracle.soa.ext.jar lists a set of classes that are included in the classpath of the soa-infra EAR
   The build.xml rebuilds the MANIFEST.MF to include the classes in the new function jar
   
