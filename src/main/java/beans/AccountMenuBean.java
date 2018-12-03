/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author nabil
 */
@Named(value = "accountMenuBean")
@SessionScoped
public class AccountMenuBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private AddPropertyBean addPropertyBean;

    /**
     * Creates a new instance of AccountMenuBean
     */
    public AccountMenuBean() {
        
    }
    
    
    public String viewVisitHistory() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Not implemented yet!"));
        return null;
    }

    public String viewVisitingList() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Not implemented yet!"));
        return null;
    }

    public String viewAccount() {
        //implemented in signInBean
        return null;

    }
    
    public String updateAccount() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Not implemented yet!"));
        return null;
    }

    public String viewProperties() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Not implemented yet!"));
        return null;
    }

    public String searchProperty() {
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Not implemented yet!"));
        return null;
    }

    

    public String browsePropertiesByLocation() {
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Not implemented yet!"));
        return null;
    }

    public String addProperty() {
        addPropertyBean.getImages().clear();
        addPropertyBean.getImagesCollection().clear();
        return "addProperty?faces-redirect=true";
    }

    public String deleteAccount() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Not implemented yet!"));
        return null;
    }

    public String backToMenu() {
        return "Account Menu?faces-redirect=true";
    }

}
