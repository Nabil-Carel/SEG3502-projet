/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author nabil
 */
@Entity
@Table(name="Property08527089_")
public class Property implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private String type;
    private String location;
    private double rent;
    private boolean deletionStatus;
    private String owner;
    private String propertyName;
    @OneToOne
    private UserProfile user;
    
    private int numberOfOtherRooms;
   
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, 
            mappedBy="property")
    private Collection<Image> pictures;
     
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    
    private Address address;

    public Property() {
    }
     
     
     public Property(int numOfBedrooms,int numOfBathrooms,double rentAmount,String name,Address addr){
         numberOfBedrooms = numOfBedrooms;
         numberOfBathrooms = numOfBathrooms;
         rent = rentAmount;
         propertyName = name;
         address = addr;
         type = null;
         location = null;
         deletionStatus = false;
         pictures = new ArrayList<>();
         
     }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Property)) {
            return false;
        }
        Property other = (Property) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "persistence.Property[ id=" + getId() + " ]";
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
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
     * @return the pictures
     */
    public Collection<Image> getPictures() {
        return pictures;
    }

    /**
     * @param pictures the pictures to set
     */
    public void setPictures(Collection<Image> pictures) {
        this.pictures = pictures;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
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
     * @return the user
     */
    public UserProfile getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserProfile user) {
        this.user = user;
    }
    
}
