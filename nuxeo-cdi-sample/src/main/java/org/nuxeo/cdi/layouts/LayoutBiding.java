package org.nuxeo.cdi.layouts;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LayoutBiding implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String title;

    protected String description;

    public LayoutBiding() {
    }

    public String getTitle() {
        if (title == null) {
            title = "Bonjour";
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        if (description == null) {
            description = "tout le monde !";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
