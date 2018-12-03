/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.ByteArrayInputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.persistence.Query;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import persistence.Image;
import persistence.Property;

/**
 *
 * @author Jérémie
 */
@Named(value = "addToVisitingListBean")
@SessionScoped
public class AddToVisitingListBean implements Serializable {

    private Property currentProperty;
    private Collection<Image> imagesCollection;
    private Image[] imagesArray;
    private ArrayList<String> imagesIds;
    
    @Inject
    private UserProfileBean userProfileBean;
    
    /**
     * Creates a new instance of AddToVisitingList
     */
    public AddToVisitingListBean() {
        imagesIds = new ArrayList<>();
    }
    
    public String addToUserList() {
        userProfileBean.getVisitedProperties().add(currentProperty);
        
        return "searchProperty";
    }
    
    public String visitProperty(Long id) {
        Query query = userProfileBean.getEm().createQuery("SELECT p FROM Property p "
                + "WHERE p.id = :propertyId");
        query.setParameter("propertyId", id);
        
        Property prop = (Property) query.getSingleResult();
        
        setCurrentProperty(prop);
        
        viewProperty();
        return "addToVisitingList";
    }
    
    public Property getCurrentProperty() {
        return currentProperty;
    }

    public void setCurrentProperty(Property currentProperty) {
        this.currentProperty = currentProperty;
    }
    
    public StreamedContent getStreamedImage() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            Image image = userProfileBean.getEm().find(Image.class,Long.parseLong(id));
            
            return new DefaultStreamedContent(
                    new ByteArrayInputStream(image.getContents()), image.getType());
        }
    }
    
    public void viewProperty() {
        imagesCollection = (getCurrentProperty().getPictures());
        imagesArray = imagesCollection.toArray(new Image[0]);
        
        for(int i =0; i < imagesArray.length;i++) {
            imagesIds.add(imagesArray[i].getId().toString());
        }
    }
    
    public ArrayList<String> getImagesIds() {
        return imagesIds;
    }
    
    public void setImagesIds(ArrayList<String> imagesIds) {
        this.imagesIds = imagesIds;
    }
    
}
