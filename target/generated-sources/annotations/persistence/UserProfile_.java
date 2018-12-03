package persistence;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.Address;
import persistence.Property;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-12-02T10:49:10")
@StaticMetamodel(UserProfile.class)
public class UserProfile_ { 

    public static volatile SingularAttribute<UserProfile, String> firstName;
    public static volatile SingularAttribute<UserProfile, String> lastName;
    public static volatile SingularAttribute<UserProfile, String> password;
    public static volatile SingularAttribute<UserProfile, Address> address;
    public static volatile SingularAttribute<UserProfile, String> phone;
    public static volatile SingularAttribute<UserProfile, Date> dob;
    public static volatile ListAttribute<UserProfile, Property> userProperties;
    public static volatile SingularAttribute<UserProfile, String> bio;
    public static volatile SingularAttribute<UserProfile, String> emailId;

}