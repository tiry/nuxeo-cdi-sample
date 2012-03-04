package org.nuxeo.cdi.extension;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.util.AnnotationLiteral;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.model.RegistrationInfo;

public class NuxeoRuntimeServiceExtension implements Extension {

    protected static Log log = LogFactory.getLog(NuxeoRuntimeServiceExtension.class);

    void afterBeanDiscovery(@Observes
    AfterBeanDiscovery abd, BeanManager bm) {

        log.error("Registering Nuxeo Runtime Services in CDI");

        Collection<RegistrationInfo> registrations = Framework.getRuntime().getComponentManager().getRegistrations();

        for (RegistrationInfo ri : registrations) {

            if (ri.getProvidedServiceNames() != null) {
                for (String serviceClassName : ri.getProvidedServiceNames()) {
                    try {
                        Class<?> serviceClass = Class.forName(serviceClassName);
                        // log.error("  - Registering Service " +
                        // serviceClassName);
                        registerNuxeoRuntimeService(serviceClass, abd, bm);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    protected <T> void registerNuxeoRuntimeService(
            final Class<T> nuxeoServiceClass, AfterBeanDiscovery abd,
            BeanManager bm) {

        AnnotatedType<T> at = bm.createAnnotatedType(nuxeoServiceClass);

        // final InjectionTarget<T> it = bm.createInjectionTarget(at);

        abd.addBean(new Bean<T>() {

            @Override
            public T create(CreationalContext<T> ctx) {
                return Framework.getLocalService(nuxeoServiceClass);
            }

            @Override
            public void destroy(T instance, CreationalContext<T> ctx) {
                // NOP
            }

            @Override
            public String getName() {
                return nuxeoServiceClass.getName();
            }

            @Override
            public Set<Annotation> getQualifiers() {
                Set<Annotation> qualifiers = new HashSet<Annotation>();
                qualifiers.add(new AnnotationLiteral<Default>() {
                });
                qualifiers.add(new AnnotationLiteral<Any>() {
                });
                return qualifiers;
            }

            @Override
            public Class<? extends Annotation> getScope() {
                return Dependent.class;
                // return RequestScoped.class;
            }

            @Override
            public Set<Class<? extends Annotation>> getStereotypes() {
                return Collections.emptySet();
            }

            @Override
            public Set<Type> getTypes() {
                Set<Type> types = new HashSet<Type>();
                types.add(nuxeoServiceClass);
                types.add(Object.class);
                return types;
            }

            @Override
            public boolean isAlternative() {
                return false;
            }

            @Override
            public boolean isNullable() {
                return false;
            }

            @Override
            public Class<?> getBeanClass() {
                return nuxeoServiceClass;
            }

            @Override
            public Set<InjectionPoint> getInjectionPoints() {
                return Collections.emptySet();
            }

        });

    }
}
