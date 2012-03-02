package org.nuxeo.cdi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.nuxeo.ecm.core.schema.DocumentType;
import org.nuxeo.ecm.core.schema.SchemaManager;
import org.nuxeo.ecm.core.schema.types.Type;

@Named
@RequestScoped
public class TestServiceBean {

    @Inject
    protected SchemaManager schemaManager;
    
    
    public Type[] getTypes() {        
        return schemaManager.getTypes();
    }
    
    public DocumentType[] getDocTypes() { 
        return schemaManager.getDocumentTypes();
    }
            
}
