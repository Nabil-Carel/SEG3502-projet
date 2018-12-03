/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;
import persistence.Property;

/**
 *
 * @author Jérémie
 */
@Named(value = "visitedPropertyBean")
@SessionScoped
public class VisitedPropertyBean implements Serializable {
    
    private Property property;
    
    @Inject
    private UserProfileBean userProfileBean;

    /**
     * Creates a new instance of VisitedPropertyBean
     */
    public VisitedPropertyBean() {
    }
    
    public String visit(int index) {
        property = userProfileBean.getVisitedProperties().get(index);
        
        return "viewVisitedProperty";
    }
    
    public String removeVisit() {
        userProfileBean.getVisitedProperties().remove(property);
        
        return "viewVisitedProperties";
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
    
    
}
