package org.nuxeo.cdi.layouts;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelFactory;

@Named
@SessionScoped
public class LayoutBinding implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String title;

    protected String description;

    protected DocumentModel sampleDocumentModel;

    public LayoutBinding() {
        title = "My title here";
        description = "My description here";
        sampleDocumentModel = DocumentModelFactory.createDocumentModel("File");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DocumentModel getSampleDocumentModel() {
        return sampleDocumentModel;
    }

    public void setSampleDocumentModel(DocumentModel sampleDocumentModel) {
        this.sampleDocumentModel = sampleDocumentModel;
    }

}
