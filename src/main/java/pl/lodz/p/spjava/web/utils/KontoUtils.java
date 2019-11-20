
package pl.lodz.p.spjava.web.utils;

import pl.lodz.p.spjava.entity.Administrator;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.entity.Pracownik;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class KontoUtils {
    /**
     * 
     * @param konto
     * @return
     */
    public static boolean isAdministrator(Konto konto) {
        return (konto instanceof Administrator);
    }

    /**
     * 
     * @param konto
     * @return
     */
    public static boolean isPracownik(Konto konto) {
        return (konto instanceof Pracownik);
    }

    /**
     * 
     * @param konto
     * @return
     */
  
    
    /** Przepisuje do przekazanej encji dane z formularza edycji konta.
     * Uwzględnione są klasy rozszerzające Konto (Administrator, Pracownik, Klient), przy czym tylko dane występujące na formularzu sa przepisywane. Pomijane są: login, hasło, id, wersja.
     * 
     * @param zrodlo encja zawierająca dane z formularza edycji
     * @param cel encja docelowa
     */
    public static void przepiszDanePoEdycji(Konto zrodlo, Konto cel) {
        
        if (null == zrodlo || null == cel) return;
        
        cel.setImie(zrodlo.getImie());
        cel.setNazwisko(zrodlo.getNazwisko());
        cel.setEmail(zrodlo.getEmail());
        cel.setTelefon(zrodlo.getTelefon());
        
        if (isAdministrator(zrodlo) && isAdministrator(cel)) {
            Administrator zrodloAdministrator = (Administrator) zrodlo;
            Administrator celAdministrator = (Administrator) cel;
            celAdministrator.setAlarmNumber(zrodloAdministrator.getAlarmNumber());
        }
        
        if (isPracownik(zrodlo) && isPracownik(cel)) {
            Pracownik zrodloPracownik = (Pracownik) zrodlo;
            Pracownik celPracownik = (Pracownik) cel;
            celPracownik.setIntercom(zrodloPracownik.getIntercom());
        }
        
      
        }
    
    
    public static String wyliczSkrotHasla(String hasloJawne){
        return hasloJawne;
    }

    public static String kodojWSha256(String haslo) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(haslo.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return DatatypeConverter.printBase64Binary(digest).toString();
    }

    public static boolean isPacjent(Konto konto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
