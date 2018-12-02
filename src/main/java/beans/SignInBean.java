/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Collection;




import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;



import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;
import persistence.Image;
import persistence.UserProfile;

/**
 *
 * @author nabil
 */
@Named(value = "signInBean")
@SessionScoped


public class SignInBean implements Serializable {

    private static String emailId;
    private static final long serialVersionUID = 1L;

    private String inputPassword;
    private static UserProfile user;

    //@ManagedProperty(value="#{userProfileBean}")
    @Inject
    private UserProfileBean userProfileBean;

    private Collection<Image> imagesCollection;
    private ArrayList<String> imagesIds;
    private Image[] imagesArray;
    private Integer index = 0;
    private StreamedContent streamedImg;
   
    /**
     * Creates a new instance of SignInBean
     */
    public SignInBean() {

    }

  
 

   
    public String viewAccount() {
        return "viewAccount?faces-redirect=true";
    }

    public String accountMenu() {
        return "Account Menu?faces-redirect=true";
    }

    /*public boolean checkPassword(){
         Search();
         return getUser().getPassword().equals(getInputPassword());
    
     }*/
    public static void welcomeMsg() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Welcome " + getUser().getFirstName()));
    }

    //throws InterruptedException
    public String login()  throws IOException {
        FacesMessage message = null;
        boolean loggedIn = false;

        // userProfileBean.findUser(getEmailId());
        //userProfileBean.setEmailId(emailId);
        //user = findUser(emailId);
        //userProfileBean.setUser(userProfileBean.findUser(emailId) );
        setUser(getUserProfileBean().findUser(getEmailId()));
        userProfileBean.setUser(user);
        String accountInfo = null;

        if (getEmailId() != null && getUser().getEmailId().equals(getEmailId()) && getInputPassword() != null && getUser().getPassword().equals(getInputPassword())) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", getUser().getFirstName());
            //userProfileBean.initialiseUserVariables();
            accountInfo = accountMenu();
//            setImagesArray(getUser().getPictures().toArray(new Image[0]));
           
//           for(int i = 0; i< imagesArray.length;i++){
               //
             //  imagesIds.add(imagesArray[i].getId().toString());
         // }
          // System.out.println(user);
          // System.out.println(imagesList.size());
          // System.out.println("accountInfo "+accountInfo);
            
            
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid email or password!");
            accountInfo = "signIn";
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
        return accountInfo;
    }
    
   /* public StreamedContent getStreamedImage() {
        FacesContext context = FacesContext.getCurrentInstance();
        //System.out.println("wrong way");
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            //System.out.println("god of war");
            String name = context.getExternalContext().getRequestParameterMap().get("id");
           // Image image = userProfileBean.getEm().find(Image.class,Long.parseLong(name));
            //System.out.println(image);

            return new DefaultStreamedContent(
                    new ByteArrayInputStream(image.getContents()), image.getType());
        }
    }*/

  

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
        SignInBean.emailId = emailId;
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
     * @return the user
     */
    public static UserProfile getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserProfile user) {
        SignInBean.user = user;
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
     **
     * @return the imagesList
     */
    public ArrayList<String> getImagesIds() {
        return imagesIds;
    }

    /**
     * @param imagesList the imagesList to set
     */
    public void setImagesList(ArrayList<String> imagesList) {
        this.imagesIds = imagesList;
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
     * @return the imagesArray
     */
    public Image[] getImagesArray() {
        return imagesArray;
    }

    /**
     * @param imagesArray the imagesArray to set
     */
    public void setImagesArray(Image[] imagesArray) {
        this.imagesArray = imagesArray;
    }
    
    public StreamedContent getStreamedImg(){
        return streamedImg;
    }
    private int getIndex() {
        return index++;
    }

}
