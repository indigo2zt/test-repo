package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import pl.lodz.p.spjava.entity.Przychodnia;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import p.lodz.p.spjava.endpoints.PrzychodniaEndpoint;

/**
 *
 * @author java
 */
@ViewScoped
@Named("PrzychodniaBean")
public class PrzychodniaBean implements Serializable {

    private List<Przychodnia> przychodnie = new ArrayList<>();

    public List<Przychodnia> getPrzychodnie() {
        return przychodnie;
    }

    public void setPrzychodnie(List<Przychodnia> przychodnie) {
        this.przychodnie = przychodnie;
    }

    private Przychodnia przychodnia = new Przychodnia();

    private Przychodnia selectPrzychodnia;

    public Przychodnia getSelectPrzychodnia() {
        return selectPrzychodnia;
    }

    public void setSelectPrzychodnia(Przychodnia selectPrzychodnia) {
        this.selectPrzychodnia = selectPrzychodnia;
    }

    @Inject
    private PrzychodniaEndpoint przychodniaEndpoint;

    public PrzychodniaBean() {
    }

    public Przychodnia getPrzychodnia() {
        return przychodnia;
    }

    public void setPrzychodnia(Przychodnia przychodnia) {
        this.przychodnia = przychodnia;
    }

    public String dodaj() {
        przychodniaEndpoint.create(przychodnia);
        return "ListaPrzychodni";
    }

    @PostConstruct
    public void init() {
        przychodnie = przychodniaEndpoint.findAll();

    }

    public String edytuj() {
        if (selectPrzychodnia != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectPrzychodnia", selectPrzychodnia);
            return "Edytuj";
        }
        
        return "";

    }

    public void usun() {
   
        this.przychodniaEndpoint.remove(selectPrzychodnia);
                przychodnie = przychodniaEndpoint.findAll();

       
       
       
      //  return "";

    }
    
  
//ychodnia
//    public List<Przychodnia> getLista() {
//        EntityManager em = DBManager.getManager().createEntityManager();
//        List list = em.createNamedQuery("Przychodnia.findAll").getResultList();
//        em.close();
//        return list;
//   }
//    private FacesMessage newFacesMessage(FacesMessage.Severity SEVERITY_INFO, String s, String string) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
//
//    public void przychodniaListener(ActionEvent ae) {
//        String ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("przychodniaID");
//        int id = Integer.parseInt(ids);
//        this.przychodnia.setId(id);
//    }
//
//    private FacesMessage newFacesMessage(FacesMessage.Severity SEVERITY_INFO, String s, String string) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//}
