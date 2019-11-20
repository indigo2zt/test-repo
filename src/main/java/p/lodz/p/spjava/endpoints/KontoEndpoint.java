/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.p.spjava.endpoints;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import pl.lodz.p.spjava.ejb.facade.AdministratorFacade;
import pl.lodz.p.spjava.ejb.facade.KontoFacade;
import pl.lodz.p.spjava.ejb.facade.PacjentFacade;
import pl.lodz.p.spjava.ejb.facade.PracownikFacade;
import pl.lodz.p.spjava.ejb.intereceptor.LoggingInterceptor;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.entity.Pacjent;
import pl.lodz.p.spjava.exception.AppBaseException;
import pl.lodz.p.spjava.web.utils.KontoUtils;

/**
 *
 * @author java
 */

@Stateful
@LocalBean
@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class KontoEndpoint extends AbstractEndpoint implements SessionSynchronization {
    
    @Inject
    private KontoFacade kontoFacade;
    
    @Inject
    private PacjentFacade pacjentFacade;
    
    @Inject
    private PracownikFacade pracownikFacade;
    
    @Inject
    private AdministratorFacade administratorFacade;
    
    private Konto kontoStan;

    public Konto pobierzMojeKonto() {
        return znajdzLogin(pobierzMojLogin());
    }

    public String pobierzMojLogin() throws IllegalStateException {
        return sctx.getCallerPrincipal().getName();
    }

    public void utworzKonto(Konto konto) {
        konto.setAktywne(true);
        konto.setPotwierdzone(true);
        // Konto ma hasło jawne podane w formularzu, należy je przeliczyć na skrót
        konto.setHaslo(KontoUtils.wyliczSkrotHasla(konto.getHaslo()));
        kontoFacade.create(konto);
    }

    public void rejestrujPacjenta(Pacjent pacjent) {
        pacjent.setAktywne(true);
        pacjent.setPotwierdzone(false);
        pacjent.setHaslo(KontoUtils.wyliczSkrotHasla((String) pacjent.getHaslo()));
        pacjentFacade.create(pacjent);
    }
    
    public void aktywujKonto(Konto konto){
        Konto k = kontoFacade.find(konto.getId());
        k.setAktywne(true);
    }
    
    public void deaktywujKonto(Konto konto){
        Konto k = kontoFacade.find(konto.getId());
        k.setAktywne(false);
    }
    
    public void potwierdzKonto(Konto konto){
        Konto k = kontoFacade.find(konto.getId());
        k.setPotwierdzone(true);
    }
    
    public List<Konto> pobierzWszystkieKonta() {
        return kontoFacade.findAll();
    }
    
    public List<Konto> dopasujKonta(String loginWzor, String imieWzor, String nazwiskoWzor, String emailWzor) {
        return kontoFacade.dopasujKonta(loginWzor, imieWzor, nazwiskoWzor, emailWzor);
    }
    
    public Konto znajdzLogin(String login) {
        return kontoFacade.znajdzLogin(login);
    }
    
    public Konto pobierzKontoDoEdycji(Konto konto) {
        kontoStan = znajdzLogin(konto.getLogin());
        return kontoStan;
    }
    
    public void zapiszKontoPoEdycji(Konto konto) throws AppBaseException {
        if (null == kontoStan) {
            throw new IllegalArgumentException("Brak wczytanego konta do modyfikacji");
        }
        if (!kontoStan.equals(konto)) {
            throw new IllegalArgumentException("Modyfikowane konto niezgodne z wczytanym");
        }
        // Przepisz te dane konta, które podlegają edycji (sa dostepne na formularzu)
        KontoUtils.przepiszDanePoEdycji(konto, kontoStan);
        //wykonej merge() na encji Konto, aby weszła ona w stan zarządzany przez obecny kontekst trwalości
        kontoFacade.edit(kontoStan);
        //usuń stan konta po zakończonej operacji - unika błędnego wielokrotnego wykonania
        kontoStan=null;
    }
    
    public void zmienMojeHaslo(String stare, String nowe) {
        Konto mojeKonto = pobierzMojeKonto();
        if(!mojeKonto.getHaslo().equals(KontoUtils.wyliczSkrotHasla(stare)))
            throw new IllegalArgumentException("Podane dotychczasowe hasło nie zgadza się");
        mojeKonto.setHaslo(KontoUtils.wyliczSkrotHasla(nowe));
    }
    
    public void zmienHaslo(Konto konto, String haslo) {
        Konto k = kontoFacade.find(konto.getId());
        k.setHaslo(KontoUtils.wyliczSkrotHasla(haslo));
    }

    public void utworzKonto(Pacjent pacjentUtworz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
