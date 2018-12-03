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

/**
 *
 * @author nabil
 */
@Named(value = "accountMenuBean")
@SessionScoped
public class AccountMenuBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of AccountMenuBean
     */
    public AccountMenuBean() {
        
    }

    public String viewVisitingList() {
        return "viewVisitedProperties";
    }

    public String viewAccount() {
        //implemented in signInBean
        return null;
    }

    public String searchProperty() {
       return "searchProperty";
    }

    public String addProperty() {
        return "addProperty";
    }

    public String deleteAccount() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Not implemented yet!"));
        return null;
    }

    public String backToMenu() {
        return "Account Menu";
    }

}
