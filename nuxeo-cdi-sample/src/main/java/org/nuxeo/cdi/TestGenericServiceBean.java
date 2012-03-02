package org.nuxeo.cdi;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.nuxeo.ecm.core.api.repository.Repository;
import org.nuxeo.ecm.core.api.repository.RepositoryManager;

@Named
@RequestScoped
public class TestGenericServiceBean {
        
    @Inject
    protected RepositoryManager repositoryManager; 
        
    public List<String> getRepoNames() {
        List<String> repos = new ArrayList<String>();
        for (Repository repo : repositoryManager.getRepositories()) {
            repos.add(repo.getName());
        }
        return repos;
    }    
}
