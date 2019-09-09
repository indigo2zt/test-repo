package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
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
public class PrzychodniaBean implements Serializable{

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
        return "Dodaj";
    }

    public void dodajInformacje(String s) {
        FacesContext.getCurrentInstance().addMessage(null, newFacesMessage(FacesMessage.SEVERITY_INFO, s, ""));
    }
//
//    public List<Przychodnia> getLista() {
//        EntityManager em = DBManager.getManager().createEntityManager();
//        List list = em.createNamedQuery("Przychodnia.findAll").getResultList();
//        em.close();
//        return list;

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
