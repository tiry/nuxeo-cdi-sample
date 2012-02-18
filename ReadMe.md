
# What is it for?

This is a sample plugin to test CDI integration with Nuxeo service platform.

For now it does not much, but it runs CDI/JSF2 components from a Nuxeo bundle deployed in a Nuxeo server ... that's a start ...

# Build the custom Nuxeo CDI Distribution

    $ mvn clean install -Pdistribution

You will end up with a full distribution `nuxeo-cdi-1.0-SNAPSHOT-tomcat.zip` in the `nuxeo-cdi-distribution/target` folder.
This distribution is running CDI / JSF2 components on a customized Nuxeo Core Server distribution.

The `nuxeo-cdi-sample` bundle is already deployed.

See the `nuxeo-cdi-distribution/src/main/assemble/assembly.xml` file for how the custom distribution is built.
  

