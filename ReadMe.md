
# What is it for ?

This is a sample plugin to test CDI integration with Nuxeo service platform.

For now it does not much, but it runs CDI/JSF2 components from a Nuxeo bundle deployed in a Nuxeo server ... that's a start ...

# How to test

Here is the current reciepe :

## Download nuxeo-core-server tomcat distribution

http://maven.in.nuxeo.com/nexus/content/repositories/daily-snapshots/org/nuxeo/ecm/distribution/nuxeo-distribution-tomcat/5.6-SNAPSHOT/nuxeo-distribution-tomcat-5.6-20120217.114025-141-coreserver.zip

## Cleanup the core server distrib

There are some libs in Core Server distrib that should not be there and don't work with CDI/JSF2 :

    rm nxserver/bundles/nuxeo-platform-ui-*
    rm nxserver/lib/jsf-*
    rm nxserver/lib/*seam*.jar
    rm nxserver/bundles/*seam*.jar
    rm nxserver/lib/jstl-1.1.2.jar
    rm nxserver/lib/richfaces*

## Remove libs that break CDI

    rm nxserver/lib/javassist-3.9.0.GA.jar

## Adapt templates

CDI requires some changes in the web.xml and faces-config :

 - web.xml template requires a new slot
 - faces-config.xml template needs to be updated to use version 2.0

**web.xml**

    vim templates/common/META-INF/templates/web.xml

Then add a new tag %{RESOURCES}% at the end of the template

       ...
       </session-config>
       %{RESOURCES}%       
     </web-app>

**faces-config**

Simply replace the ./templates/common/META-INF/templates/faces-config.xml with an empty one :

    <?xml version="1.0" encoding="UTF-8"?>
    <faces-config version="2.0"
        xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="
          http://java.sun.com/xml/ns/javaee
          http://java.sun.com/xml/ns/javaee/web-facesconfig_2_0.xsd">
    </faces-config>

## Bind the BeanManager

The BeanManager must be bound inside the context.

    vim templates/default/conf/Catalina/localhost/nuxeo.xml

Add a new Resource :

     <Resource name="BeanManager" 
      auth="Container"
      type="javax.enterprise.inject.spi.BeanManager"
      factory="org.jboss.weld.resources.ManagerObjectFactory"/>

## Build the sample

    mvn clean install

Then copy the jars :

    cp target/nuxeo-cdi*.jar $NUXEO/nxserver/bundles/.
    cp target/tomcat-lib/* $NUXEO/lib/.
    cp target/nuxeo-lib/* $NUXEO/nxserver/lib/.


  

