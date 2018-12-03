/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import java.util.Date;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import static javax.ws.rs.client.Entity.entity;
import static org.eclipse.persistence.sessions.SessionProfiler.Transaction;

import org.primefaces.PrimeFaces;

import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import persistence.Address;
import persistence.Image;
import persistence.UserProfile;
import persistence.Property;

/**
 *
 * @author ssome
 */
@ManagedBean
@Named(value = "userProfileBean")
@ApplicationScoped

public class UserProfileBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Internal class to represent images prior to persisting
     */
    /*   class Image {

        byte[] contents;
        String type;

        private Image(byte[] contents, String type) {
            this.contents = contents;
            this.type = type;
        }

        byte[] getContents() {
            return contents;
        }

        String getType() {
            return type;
        }
    }*/
    private String emailId;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Date dob;
    private String bio;
    private String number;
    private String street;
    private String unit;
    private String city;
    private String province;
    private String postalCode;
    private String inputPassword;

    private UserProfile user;
    private String name;

    @PersistenceContext(unitName = "LAB4_PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

   // private Map<String, Image> images;
    
    private ArrayList<Property> visitedProperties;


    private boolean emailAlreadyInDB;
    private ArrayList<Property> properties;
    private ArrayList<String> propertiesIds;
    private Property property;
    private Collection<Image> imagesCollection;
    private ArrayList<Image> imagesList;
    private ArrayList<String> imagesIds;
    @Resource
    private UserTransaction ut;
    private boolean signedIn;

    // private Collection<Image> imagesCollection;
    // private ArrayList<StreamedContent> imagesList;
    // private Image[] imagesArray;
    // private StreamedContent streamedImg;
    // private byte[] imageContent;
    //private String imageType;
    @Inject
    private SignInBean signInBean;
    @Inject
    private AddPropertyBean addPropertyBean;

    /**
     * Creates a new instance of UserProfileBean
     */
    public UserProfileBean() {
        //images = new TreeMap<>();
        imagesIds = new ArrayList<>();

        visitedProperties = new ArrayList<>();

        imagesList = new ArrayList<>();
        signedIn = false;

    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bio = bio;
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
     * @return the name
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the name to set
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

    /*public void handleUserPicUpload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        try {
            byte[] contents = IOUtils.toByteArray(uploadedFile.getInputstream()); // uploadedFile.getContents() doesn't work as expected
            //byte[] contents = uploadedFile.getContents();
            String type = uploadedFile.getContentType();
           Image image = new Image(contents, type);
            String filename = uploadedFile.getFileName();
            getImages().put(filename, image);
        } catch (IOException ex) {
            Logger.getLogger(UserProfileBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public StreamedContent getStreamedImage() {
        FacesContext context = FacesContext.getCurrentInstance();

       System.out.println("context");
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
           
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            System.out.println("id  -- "+id);
            Image image = getEm().find(Image.class,Long.parseLong(id));

        System.out.println("context");
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            String id = context.getExternalContext().getRequestParameterMap().get("id");
            System.out.println("id  -- " + id);
            Image image = getEm().find(Image.class, Long.parseLong(id));

            //System.out.println(image);

            return new DefaultStreamedContent(
                    new ByteArrayInputStream(image.getContents()), image.getType());
        }
    }

    public String viewProperty(String index) {

        imagesList.clear();
        getImagesIds().clear();
        setProperty(getProperties().get(Integer.parseInt(index)));

        setImagesCollection(getProperty().getPictures());

        //System.out.println("index "+getProperty().getPictures());
        //  
        getImagesList().addAll(getImagesCollection());

        for (int i = 0; i < getImagesList().size(); i++) {
            getImagesIds().add(getImagesList().get(i).getId().toString());
            System.out.println("imagesId    " + imagesIds);
            //System.out.println("forloop " + getImagesArray()[i].getId().toString());
        }
        // System.out.println("imagesAray " + Arrays.toString(getImagesArray()));

        return "viewProperty?faces-redirect=true";
    }

    public String loadProperties() {
        setProperties(findProperty(getEm(), getUser().getEmailId()));
        setPropertiesIds(new ArrayList<>());

        return "viewProperties?faces-redirect=true";
    }

    public String deleteProperty(String index) throws NotSupportedException {
        int i = Integer.parseInt(index);
        setProperty(getProperties().get(i));
        try {
            getUt().begin();
            Property actorToBeRemoved = em.getReference(Property.class, property.getId());
            em.remove(actorToBeRemoved);
            //getEm().remove(getEm().merge(getProperty()));
            // imagesIds.remove(i);
            getUt().commit();
            setProperties(findProperty(getEm(), getUser().getEmailId()));
            setPropertiesIds(new ArrayList<>());
        } catch (SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(UserProfileBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("exception");
            throw new RuntimeException(ex);
        }

        return "viewProperties?faces-redirect=true";
    }

    /*public String loadUpdateMenu(String row){
       
        return "updatePropertyMenu?faces-redirect=true";
    }
    
    public String updatePropertyWithOldPicutres(){
        return "updateWithOldPictures?faces-redirect=true";
    }*/
    public String updatePropertyWithNewPictures(String row) {
        addPropertyBean.setIndex(row);
        return "updatePropertyWithNewPictures?faces-redirect=true";
    }

    /**
     * @return the imageIds
     */
    // public Collection<String> getImageIds() {
    //     return getImages().keySet();
    // }
    /**
     * Add the user to the database
     *
     * @return
     * @throws java.io.IOException
     */
    public String doRegister() throws IOException {
        Address address = new Address(getNumber(), getStreet(), getUnit(), getCity(), getProvince(), getPostalCode());
        UserProfile profile = new UserProfile(getEmailId(), getPassword(), getFirstName(), getLastName(), getPhone(), getDob(), address, getBio());
        // getImages().values().stream().map((p) -> new persistence.Image(p.getContents(), p.getType())).map((pim) -> {
        //     pim.setUser(profile);
        //   return pim;
        // }).forEachOrdered((pim) -> {
        //    profile.addPicture(pim);
        // });
        String userAccountMenu = null;
        try {
            persist(profile);
            setUser(profile);

            

            getSignInBean().setUser(getUser());
            getSignInBean().setInputPassword(getPassword());
            getSignInBean().setEmailId(getEmailId());


            String msg = "User Profile Created Successfully";
            userAccountMenu = "Account Menu?faces-redirect=true";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
            FacesContext.getCurrentInstance().getExternalContext()
                    .getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();

            // SignInBean.initialiseUserVariables();
            SignInBean.welcomeMsg();
        } catch (RuntimeException e) {
            if (userAccountMenu == null) {
                String msg = "Error While Creating User Profile";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", msg));
                FacesContext.getCurrentInstance().getExternalContext()
                        .getFlash().setKeepMessages(true);
            }

        }
        return userAccountMenu;
    }

    public void persist(Object object) {
        try {
            getUtx().begin();
            getEm().persist(object);
            getUtx().commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public UserProfile findUser(String emailId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //UserProfile user = getEm().find(UserProfile.class, emailId);
        UserProfile foundUser = findByEmail(getEm(), emailId);
        return foundUser;

    }

    public UserProfile findByEmail(EntityManager em, String email) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Query query = em.createQuery(
                "SELECT user FROM UserProfile user"
                + " WHERE user.emailId = :userEmail");
        query.setParameter("userEmail", email);
        return performQuery(query);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Property> findProperty(EntityManager em, String email) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Query query = em.createQuery(
                "SELECT prop FROM Property prop"
                + " WHERE prop.owner = :owner");
        query.setParameter("owner", email);
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        ArrayList<Property> results = new ArrayList<>();
        results.addAll(resultList);
        return results;
    }

    @SuppressWarnings("unchecked")
    private static UserProfile performQuery(final Query query) {
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        ArrayList<UserProfile> results = new ArrayList<>();
        results.addAll(resultList);
        return results.get(0);
    }

    /*public String Search() {
        System.out.println("Started");
        UserProfile user = findByEmail(getEm(), getSearchEmail());

        this.setSearchFirstName(user.getFirstName());
        this.setSearchLastName(user.getLastName());
        this.setSearchPhone(user.getPhone());
        this.setSearchDob(user.getDob());
        this.setSearchBio(user.getBio());
        this.setSearchNumber(user.getAddress().getNumber());
        //searchNtreet = user.getAddress().;
        this.setSearchUnit(user.getAddress().getUnit());
        this.setSearchCity(user.getAddress().getCity());
        this.setSearchProvince(user.getAddress().getProvince());
        this.setSearchPostalCode(user.getAddress().getPostalCode());
        this.setSearchStreet(user.getAddress().getName());

        
        return "search_results";
    }*/
    public String onFlowProcess(FlowEvent event) {
        UserProfile userProf = null;
        userProf = findByEmail(getEm(), getEmailId());
        setEmailAlreadyInDB(userProf != null);
        if (isEmailAlreadyInDB()) {
            setEmailAlreadyInDB(false);   //reset in case user goes back
            FacesMessage msg;

            msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Email already used!","");
            FacesContext.getCurrentInstance().addMessage(null, msg);
             

       
            //return "confirm";
            return event.getOldStep();
        } else {
            return event.getNewStep();
        }
    }

    public void welcomeMsg() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Welcome " + getUser().getFirstName()));
    }

    public String login() throws IOException {
        FacesMessage message = null;
        boolean loggedIn = false;

        // userProfileBean.findUser(getEmailId());
        //userProfileBean.setEmailId(emailId);
        //user = findUser(emailId);
        //userProfileBean.setUser(userProfileBean.findUser(emailId) );
        setUser(findUser(getEmailId()));

        String accountInfo = null;
        if (user == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No existent Account", "");
        } else if (getEmailId() != null && getUser().getEmailId().equals(getEmailId()) && getInputPassword() != null && getUser().getPassword().equals(getInputPassword())) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", getUser().getFirstName());
            //userProfileBean.initialiseUserVariables();
            accountInfo = "Account Menu?faces-redirect=true";

        } else {
            loggedIn = false;


            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid email or password!");

            accountInfo = "signIn?faces-redirect=true";

        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
        return accountInfo;
    }

    /**
     * ************************setters and getters
     * ********************************************
     *
     *
     * /
     *
     **
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the utx
     */
    public javax.transaction.UserTransaction getUtx() {
        return utx;
    }

    /**
     * @param utx the utx to set
     */
    public void setUtx(javax.transaction.UserTransaction utx) {
        this.utx = utx;
    }

    /**
     * @return the images
     */
//    public Map<String, Image> getImages() {
    //   return images;
    // }
    /**
     * @return the searchImageIds
     */
    /**
     * public Collection<persistence.Image> getSearchImages() { return
     * searchImages; }
     */
    /**
     * @param images the images to set
     */
//    public void setImages(Map<String, Image> images) {
    //   this.images = images;
    // }
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

    /**
     * @return the emailAlreadyInDB
     */
    public boolean isEmailAlreadyInDB() {
        return emailAlreadyInDB;
    }

    /**
     * @param emailAlreadyInDB the emailAlreadyInDB to set
     */
    public void setEmailAlreadyInDB(boolean emailAlreadyInDB) {
        this.emailAlreadyInDB = emailAlreadyInDB;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the inputPassword
     */
    public String getInputPassword() {
        return inputPassword;
    }

    /**
     * @param inputPassword the inputPassword to set
     */
    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

    /**
     * @return the searchImageIds
     */
    // public Collection<String> getSearchImageIds() {
    // return getSearchImages().keySet();
    // }
    //***************************** Navigation Methods ************************************************
    public String signIn() {
        
        
        return "signIn?faces-redirect=true";
    }

    public String createAccount() {
        return "index";
    }

    public String logout() {
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Not implemented yet!"));
        setUser(null);

        setEmailAlreadyInDB(false);
        setSignedIn(false);
        return "signIn?faces-redirect=true";
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    /**
     * @return the signInBean
     */
    public SignInBean getSignInBean() {
        return signInBean;
    }

    /**
     * @param signInBean the signInBean to set
     */
    public void setSignInBean(SignInBean signInBean) {
        this.signInBean = signInBean;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return the properties
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * @return the propertiesIds
     */
    public ArrayList<String> getPropertiesIds() {
        return propertiesIds;
    }

    /**
     * @param propertiesIds the propertiesIds to set
     */
    public void setPropertiesIds(ArrayList<String> propertiesIds) {
        this.propertiesIds = propertiesIds;
    }

    /**
     * @return the property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(Property property) {
        this.property = property;
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
     * @return the imagesIds
     */
    public ArrayList<String> getImagesIds() {
        return imagesIds;
    }

    /**
     * @param imagesIds the imagesIds to set
     */
    public void setImagesIds(ArrayList<String> imagesIds) {
        this.imagesIds = imagesIds;
    }

    public String backToMenu() {
        return "menu?faces-redirect=true";
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the ut
     */
    public UserTransaction getUt() {
        return ut;
    }

    /**
     * @param ut the ut to set
     */
    public void setUt(UserTransaction ut) {
        this.ut = ut;
    }

    /**
     * @return the imagesList
     */
    public ArrayList<Image> getImagesList() {
        return imagesList;
    }

    /**
     * @param imagesList the imagesList to set
     */
    public void setImagesList(ArrayList<Image> imagesList) {
        this.imagesList = imagesList;
    }

    public String deleteAccount() {
        try {
            getUt().begin();
            UserProfile actorToBeRemoved = em.getReference(UserProfile.class, user.getEmailId());
            em.remove(actorToBeRemoved);
            //getEm().remove(getEm().merge(getProperty()));
            // imagesIds.remove(i);
            getUt().commit();
            setProperties(findProperty(getEm(), getUser().getEmailId()));
            setPropertiesIds(new ArrayList<>());
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(UserProfileBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("exception");
            throw new RuntimeException(ex);
        }
        return "menu?faces-redirect=true";
    }

    /**
     * @return the signedIn
     */
    public boolean isSignedIn() {
        return signedIn;
    }

    /**
     * @param signedIn the signedIn to set
     */
    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
    
    public String backToMenu(){
        return "menu";
    }
    
    public ArrayList<Property> getVisitedProperties() {
        return visitedProperties;
    }

    public void setVisitedProperties(ArrayList<Property> visitedProperties) {
        this.visitedProperties = visitedProperties;
    }

}
