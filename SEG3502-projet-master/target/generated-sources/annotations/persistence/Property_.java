package persistence;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.Address;
import persistence.Image;
import persistence.UserProfile;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-02T21:08:41")
@StaticMetamodel(Property.class)
public class Property_ { 

    public static volatile SingularAttribute<Property, String> owner;
    public static volatile SingularAttribute<Property, Address> address;
    public static volatile SingularAttribute<Property, String> type;
    public static volatile SingularAttribute<Property, Double> rent;
    public static volatile CollectionAttribute<Property, Image> pictures;
    public static volatile SingularAttribute<Property, String> propertyName;
    public static volatile SingularAttribute<Property, String> location;
    public static volatile SingularAttribute<Property, Integer> numberOfOtherRooms;
    public static volatile SingularAttribute<Property, Long> id;
    public static volatile SingularAttribute<Property, Boolean> deletionStatus;
    public static volatile SingularAttribute<Property, Integer> numberOfBedrooms;
    public static volatile SingularAttribute<Property, UserProfile> user;
    public static volatile SingularAttribute<Property, Integer> numberOfBathrooms;

}