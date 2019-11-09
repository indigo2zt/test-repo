/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.p.spjava.web.konto;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import p.lodz.p.spjava.endpoints.KontoEndpoint;
import pl.lodz.p.spjava.entity.Administrator;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.entity.Pacjent;
import pl.lodz.p.spjava.entity.Pracownik;
import pl.lodz.p.spjava.exception.AppBaseException;
import pl.lodz.p.spjava.web.utils.ContextUtils;

/**
 *
 * @author java
 */
@Named("kontoSession")
@SessionScoped
public class KontoSession implements Serializable {

    private static final Logger LOG = Logger.getLogger(KontoSession.class.getName());

    @Inject
    private KontoEndpoint kontoEndpoint;

    public String resetujSesje() {
        ContextUtils.invalidateSession();
        /* Poprawne zakończenie sesji wymaga wymuszenia nowego żądania na przeglądarce, stąd metoda ta
         * prowadzi do przypadku nawigacji z elementem <redirect />.
         * UWAGA: integracja logowania typu BASIC z przeglądarką powoduje, że czasem mimo to "wylogowanie" jest nieskuteczne - 
         * powstaje nowa sesja już zalogowanego użytkownika. Dlatego bezpieczniej jest stosować uwierzytelnianie przez formularz (FORM).
         */
        return "cancelAction";
    }

    public String getMojLogin() {
        return ContextUtils.getUserName();
    }

    private Pacjent pacjentRejestracja;

    private Pacjent pacjentUtworz;

    private Pracownik pracownikRejestracja;

    private Pracownik pracownikUtworz;

    private Administrator administratorUtworz;

    private Konto kontoEdytuj;

    private Konto kontoZmienHaslo;

    public Konto getKontoZmienHaslo() {
        return kontoZmienHaslo;
    }

    public Konto getKontoEdytuj() {
        return kontoEdytuj;
    }

    public Pacjent getPacjentRejestracja() {
        return pacjentRejestracja;
    }

    public KontoSession() {
    }

    public String utworzPacjenta(Pacjent pacjent) {
        pacjentUtworz = pacjent;
        kontoEndpoint.utworzKonto(pacjentUtworz);
        pacjentUtworz = null;
        return "success";
    }

    public String utworzPracownika(Pracownik pracownik) {
        pracownikUtworz = pracownik;
        kontoEndpoint.utworzKonto(pracownikUtworz);
        pracownikUtworz = null;
        return "success";
    }

    public String utworzAdministratora(Administrator admin) {
        administratorUtworz = admin;
        kontoEndpoint.utworzKonto(administratorUtworz);
        administratorUtworz = null;
        return "success";
    }

    public String potwierdzRejestracjePacjenta(Pacjent pacjent) {
        this.pacjentRejestracja = pacjent;
        return "confirmRegister";
    }

    public String rozpocznijZmianeHasla(Konto konto) {
        this.kontoZmienHaslo = konto;
        return "changePassword";
    }

    public String rejestrujPacjenta() {
        kontoEndpoint.rejestrujPacjenta(pacjentRejestracja);
        pacjentRejestracja = null;
        return "success";
    }

    public void aktywujKonto(Konto Konto) {
        kontoEndpoint.aktywujKonto(Konto);
        ContextUtils.emitSuccessMessage(ListaKontPageBean.GENERAL_MSG_ID);
    }

    public void deaktywujKonto(Konto Konto) {
        kontoEndpoint.deaktywujKonto(Konto);
        ContextUtils.emitSuccessMessage(ListaKontPageBean.GENERAL_MSG_ID);
    }

    public void potwierdzKonto(Konto Konto) {
        kontoEndpoint.potwierdzKonto(Konto);
        ContextUtils.emitSuccessMessage(ListaKontPageBean.GENERAL_MSG_ID);
    }

    public String pobierzKontoDoEdycji(Konto Konto) {
        kontoEdytuj = kontoEndpoint.pobierzKontoDoEdycji(Konto);
        return "editAccount";
    }

    public String zapiszKontoPoEdycji(Konto Konto) throws AppBaseException {
        kontoEndpoint.zapiszKontoPoEdycji(Konto);
        return "success";
    }

    public String zmienHasloKonta(String haslo) {
        kontoEndpoint.zmienHaslo(kontoZmienHaslo, haslo);
        return "success";
    }

    public String zmienMojeHaslo(String stare, String nowe) {
        kontoEndpoint.zmienMojeHaslo(stare, nowe);
        return "success";
    }

    public List<Konto> pobierzWszystkieKonta() {
        return kontoEndpoint.pobierzWszystkieKonta();
    }

    public List<Konto> dopasujKonta(String loginWzor, String imieWzor, String nazwiskoWzor, String emailWzor) {
        return kontoEndpoint.dopasujKonta(loginWzor, imieWzor, nazwiskoWzor, emailWzor);
    }

    public Konto pobierzMojeKonto() {
        return kontoEndpoint.pobierzMojeKonto();
    }

    @PostConstruct
    private void init() {
        LOG.severe("Session started: " + ContextUtils.getSessionID());
    }

    String potwierdzRejestracjePracownika(Pracownik konto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
