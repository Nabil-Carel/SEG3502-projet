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
 * @author Jeremie
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
        userProfileBean.setImagesCollection(property.getPictures()); 
        userProfileBean.getImagesList().addAll(userProfileBean.getImagesCollection());

        for (int i = 0; i < userProfileBean.getImagesList().size(); i++) {
            userProfileBean.getImagesIds().add(userProfileBean.getImagesList().get(i).getId().toString());
           
            //System.out.println("forloop " + getImagesArray()[i].getId().toString());
        }
        
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
