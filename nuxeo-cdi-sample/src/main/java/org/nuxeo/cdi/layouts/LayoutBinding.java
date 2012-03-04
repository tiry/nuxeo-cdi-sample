package org.nuxeo.cdi.layouts;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LayoutBinding implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String title;

    protected String description;

    public LayoutBinding() {
        title = "My title here";
        description = "My description here";
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

}
