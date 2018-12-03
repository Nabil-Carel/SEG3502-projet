/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.persistence.Query;
import persistence.Property;

/**
 *
 * @author Jérémie
 */
@Named(value = "searchPropertyBean")
@SessionScoped
public class SearchPropertyBean implements Serializable {
    
    private Integer numberOfBedrooms;
    private Integer numberOfBathrooms;
    private Double rent;
    private String propertyName;
    private String owner;
    private String street;
    private String city;
    private String province;
    
    private String sqlQuery;
    private Query query;
    private Map<String, Object> queryPropertyParameters;
    private Map<String, Object> queryAddressParameters;
    
    @Inject
    private UserProfileBean userProfileBean;
    
    /**
     * Creates a new instance of SearchPropertyBean
     */
    public SearchPropertyBean() {
        propertyName = "";
        owner = "";
        street = "";
        city = "";
        province = "";
        numberOfBedrooms = 0;
        numberOfBathrooms = 0;
        rent = 0.0;
        
        sqlQuery = "SELECT p FROM Property p";
        
        queryPropertyParameters = new HashMap<>();
        queryAddressParameters = new HashMap<>();
    }
    
    public void submit() {
        sqlQuery = "SELECT p FROM Property p ";
        
        int i = 0;
        Iterator<Map.Entry<String, Object>> iterator = queryPropertyParameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            
            if (entry.getValue() != null){
                if (i == 0) {
                    sqlQuery += " WHERE ";
                } else {
                    sqlQuery += " AND ";
                }

                sqlQuery += "p." + entry.getKey() + " = \'" + entry.getValue().toString() + "\'";

                i++;
            }
        }
        
        iterator = queryAddressParameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            
            if (entry.getValue() != null){
                if (i == 0) {
                    sqlQuery += " WHERE ";
                } else {
                    sqlQuery += " AND ";
                }

                sqlQuery += "p.address." + entry.getKey() + " = \'" + entry.getValue().toString() + "\'";
                
                i++;
            }
        }
    }
    
    public List<Property> getProperties() {         
        query = userProfileBean.getEm().createQuery(sqlQuery);
        List resultList = query.getResultList();
        
        if (resultList.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Property> results = new ArrayList<>();
        results.addAll(resultList);
        return results;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        if (numberOfBedrooms > 0) {
            queryPropertyParameters.put("numberOfBedrooms", numberOfBedrooms);
        } else {
            if (queryPropertyParameters.containsKey("numberOfBedrooms")) {
                queryPropertyParameters.remove("numberOfBedrooms");
            }
        }
        
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        if (numberOfBathrooms > 0) {
            queryPropertyParameters.put("numberOfBathrooms", numberOfBathrooms);
        } else {
            if (queryPropertyParameters.containsKey("numberOfBathrooms")) {
                queryPropertyParameters.remove("numberOfBathrooms");
            }
        }
        
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        if (rent > 0.0) {
            queryPropertyParameters.put("rent", rent);
        } else {
            if (queryPropertyParameters.containsKey("rent")) {
                queryPropertyParameters.remove("rent");
            }
        }
        
        this.rent = rent;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        if (owner.length() > 0) {
            queryPropertyParameters.put("owner", owner);
        } else {
            if (queryPropertyParameters.containsKey("owner")) {
                queryPropertyParameters.remove("owner");
            }
        }
        
        this.owner = owner;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        if (propertyName.length() > 0) {
            queryPropertyParameters.put("propertyName", propertyName);
        } else {
            if (queryPropertyParameters.containsKey("propertyName")) {
                queryPropertyParameters.remove("propertyName");
            }
        }
        
        this.propertyName = propertyName;
    }
    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street.length() > 0) {
            queryAddressParameters.put("name", street);
        } else {
            if (queryAddressParameters.containsKey("name")) {
                queryAddressParameters.remove("name");
            }
        }
        
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city.length() > 0) {
            queryAddressParameters.put("city", city);
        } else {
            if (queryAddressParameters.containsKey("city")) {
                queryAddressParameters.remove("city");
            }
        }
        
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        if (province.length() > 0) {
            queryAddressParameters.put("province", province);
        } else {
            if (queryAddressParameters.containsKey("province")) {
                queryAddressParameters.remove("province");
            }
        }
        
        this.province = province;
    }
}
