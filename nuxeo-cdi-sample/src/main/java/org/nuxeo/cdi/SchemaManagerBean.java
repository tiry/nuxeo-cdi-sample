package org.nuxeo.cdi;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.nuxeo.ecm.core.schema.SchemaManager;
import org.nuxeo.runtime.api.Framework;

@Named
@RequestScoped
public class SchemaManagerBean implements Serializable {

    private static final long serialVersionUID = 1L;
        
    @Produces @RequestScoped    
    public SchemaManager getSchemaManager() throws Exception {
        return Framework.getLocalService(SchemaManager.class);
    }
 
    
}
