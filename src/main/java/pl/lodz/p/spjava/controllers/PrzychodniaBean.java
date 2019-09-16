package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import pl.lodz.p.spjava.entity.Przychodnia;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.spjava.ejb.facade.PrzychodniaFacade;

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

    @Inject
    private PrzychodniaFacade przychodniaFacade;

    public PrzychodniaBean() {
    }

    public Przychodnia getPrzychodnia() {
        return przychodnia;
    }

    public void setPrzychodnia(Przychodnia przychodnia) {
        this.przychodnia = przychodnia;
    }

    public String dodaj() {

        przychodniaFacade.create(przychodnia);
        this.przychodnia.setNazwa();
        this.przychodnia.setOpis();
        this.przychodnia.setAdres();
        this.przychodnia.setKontakt();
        return "Dodaj";
    }
    
     
    public void dodajInformacje(String s) {
        FacesContext.getCurrentInstance().addMessage(null, newFacesMessage(FacesMessage.SEVERITY_INFO, s, ""));
    }
 
      
  
    
    @PostConstruct
    public void init() {
        przychodnie = przychodniaFacade.findAll();
        
    }
  public String edytujPrzychodnia (Przychodnia przychodnia) {
      this.przychodniaFacade.edit(przychodnia);
      this.dodajInformacje("Edytowano przychodnie");
        return "edytuj";
      
  }
//  public List<Przychodnia> getAllPrzychodnie() {
//        return this.PrzychodniaFacade.findAll(
//  public List<Przychodnia>getAllPrzychodnie {
//    return.this.przychodniaFacade.findAll()
//}
    
    public String usunPrzychodnia (Przychodnia przychodnia) {
        this.przychodniaFacade.remove(przychodnia);
        this.dodajInformacje("Usunięto przychodnie");
        return "usun";
       
    }
    
//ychodnia
//    public List<Przychodnia> getLista() {
//        EntityManager em = DBManager.getManager().createEntityManager();
//        List list = em.createNamedQuery("Przychodnia.findAll").getResultList();
//        em.close();
//        return list;
   
  
       
//   }
    private FacesMessage newFacesMessage(FacesMessage.Severity SEVERITY_INFO, String s, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
