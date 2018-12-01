/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import persistence.Address;
import persistence.Image;
import persistence.Property;

/**
 *
 * @author nabil
 */
@Named(value = "addPropertyBean")
@ManagedBean
@SessionScoped
public class AddPropertyBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private String type;
    private String location;
    private double rent;
    private boolean deletionStatus;
    private Address propertyAddress;
    private int numberOfOtherRooms;
    private String propertyName;
    private String number;
    private String street;
    private String unit;
    private String city;
    private String province;
    private String postalCode;
    private Collection<Image> imagesCollection;
    private int imagesArrayIndex = 0;
    @Inject
    private UserProfileBean userProfileBean;
    private Map<String, Image> images;
    @Inject
    private Conversation conversation;
    /**
     * Creates a new instance of AddPropertyBean
     */
    public AddPropertyBean() {
        imagesCollection = new ArrayList<>(5);
        images = new HashMap<>();
    }
    
    @PostConstruct
    public void init() {
       // conversation.begin();
    }

    public String addProperty() {
        Address address = new Address(getNumber(), getStreet(), getUnit(), getCity(), getProvince(), getPostalCode());
        //System.out.println("address   "+address.getId());
        Property property = new Property(getNumberOfBedrooms(), getNumberOfBathrooms(), getRent(), getPropertyName(), address);

        property.setPictures(getImagesCollection());
        property.setOwner(getUserProfileBean().getUser().getEmailId());
        getUserProfileBean().persist(property);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Property Successfully Added"));
       // conversation.end();
        return "Account Menu";
    }

    public void handleUserPicUpload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        try {
            byte[] contents = IOUtils.toByteArray(uploadedFile.getInputstream()); // uploadedFile.getContents() doesn't work as expected
            //byte[] contents = uploadedFile.getContents();
            String type = uploadedFile.getContentType();
            Image image = new Image(contents, type);
            String filename = uploadedFile.getFileName();
            getImagesCollection().add(image);
            getImages().put(filename, image);
        } catch (IOException ex) {
            Logger.getLogger(UserProfileBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StreamedContent getStreamedImage() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String name = context.getExternalContext().getRequestParameterMap().get("id");
            Image image = getImages().get(name);

            return new DefaultStreamedContent(
                    new ByteArrayInputStream(image.getContents()), image.getType());
        }
    }



    

    /**
     * @return the imageIds
     */
    public Collection<String> getImageIds() {
        return getImages().keySet();
    }

    /**
     * @return the numberOfBedrooms
     */
    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    /**
     * @param numberOfBedrooms the numberOfBedrooms to set
     */
    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    /**
     * @return the numberOfBathrooms
     */
    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    /**
     * @param numberOfBathrooms the numberOfBathrooms to set
     */
    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the rent
     */
    public double getRent() {
        return rent;
    }

    /**
     * @param rent the rent to set
     */
    public void setRent(double rent) {
        this.rent = rent;
    }

    /**
     * @return the deletionStatus
     */
    public boolean isDeletionStatus() {
        return deletionStatus;
    }

    /**
     * @param deletionStatus the deletionStatus to set
     */
    public void setDeletionStatus(boolean deletionStatus) {
        this.deletionStatus = deletionStatus;
    }

    /**
     * @return the propertyAddress
     */
    public Address getPropertyAddress() {
        return propertyAddress;
    }

    /**
     * @param propertyAddress the propertyAddress to set
     */
    public void setPropertyAddress(Address propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    /**
     * @return the numberOfOthersRooms
     */
    /**
     * @return the numberOfOtherRooms
     */
    public int getNumberOfOtherRooms() {
        return numberOfOtherRooms;
    }

    /**
     * @param numberOfOtherRooms the numberOfOtherRooms to set
     */
    public void setNumberOfOtherRooms(int numberOfOtherRooms) {
        this.numberOfOtherRooms = numberOfOtherRooms;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the imagesArrayIndex
     */
    public int getImagesArrayIndex() {
        return imagesArrayIndex;
    }

    /**
     * @param imagesArrayIndex the imagesArrayIndex to set
     */
    public void setImagesArrayIndex(int imagesArrayIndex) {
        this.imagesArrayIndex = imagesArrayIndex;
    }

    /**
     * @return the userProfileBean
     */
    public UserProfileBean getUserProfileBean() {
        return userProfileBean;
    }

    /**
     * @param userProfileBean the userProfileBean to set
     */
    public void setUserProfileBean(UserProfileBean userProfileBean) {
        this.userProfileBean = userProfileBean;
    }

    /**
     * @return the imagesCollection
     */
    public Collection<Image> getImagesCollection() {
        return imagesCollection;
    }

    /**
     * @param imagesCollection the imagesCollection to set
     */
    public void setImagesCollection(Collection<Image> imagesCollection) {
        this.imagesCollection = imagesCollection;
    }

    /**
     * @return the propertyName
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * @param propertyName the propertyName to set
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * @param images the images to set
     */
    public void setImages(Map<String, Image> images) {
        this.images = images;
    }

    /**
     * @return the images
     */
    public Map<String, Image> getImages() {
        return images;
    }

}
