package org.nuxeo.cdi;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.nuxeo.runtime.api.Framework;

public class GenericServiceProvider implements Serializable {

    /**
    private static final long serialVersionUID = 1L;
    
    @Produces @NuxeoService
    public Object getService(InjectionPoint ip) throws Exception {        
        return Framework.getLocalService(ip.getType().getClass());
    }
    */

}
