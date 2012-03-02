package org.nuxeo.cdi;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.nuxeo.ecm.core.api.CoreInstance;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.repository.RepositoryManager;
import org.nuxeo.runtime.api.Framework;

@Named
@RequestScoped
public class DocumentManagerBean implements Serializable{

    private static final long serialVersionUID = 1L;

    CoreSession coreSession =null;
    
    @Produces @RequestScoped    
    public CoreSession getCoreSession() throws Exception {
        if (coreSession==null) {
            RepositoryManager rm = Framework.getLocalService(RepositoryManager.class);        
            coreSession =  rm.getDefaultRepository().open();
        }
        return coreSession;
    }
 
    
    @PreDestroy
    public void cleanup() {
        if (coreSession!=null) {
            CoreInstance.getInstance().close(coreSession);
            coreSession=null;
        }
    }
}
