/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.entity;

/**
 *
 * @author java
 */

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Konto")
@SecondaryTable(name = "DanePersonalne")
@TableGenerator(name = "KontoIdGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "Konto", initialValue=100)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "typ")
@DiscriminatorValue("KONTO")
@NamedQueries({
        @NamedQuery(name = "Konto.findByLogin", query = "SELECT k FROM Konto k where k.login = :login"),
        @NamedQuery(name = "Konto.findByKod", query = "SELECT k FROM Konto k where k.kodaktywacji = :kod")
})

public class Konto implements Serializable {

    public Konto() {

    }

    protected Object getBusinessKey() {
        return login;
    }
    @Id
    @Column(name = "id", updatable = false) 
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "KontoIdGen")
    private Long id;
    @Column(name = "login", length = 32, nullable = false, unique = true, updatable = false)
    private String login;
    @NotNull(message="{constraint.notnull}")
    @Size(min=6,message="{constraint.string.length.tooshort}")
    @Column(name = "haslo", length = 256, nullable = false)
    private String haslo;

    @Column(name = "potwierdzone", nullable = false)
    private boolean potwierdzone;
    
    @Column(name = "aktywne", nullable = false)
    private boolean aktywne;

    @Column(name = "kodaktywacji", nullable = true)
    private String kodaktywacji;

    @Column(name="typ",updatable=false)
    private String typ;
    @NotNull(message="{constraint.notnull}")
    @Size(min=3,max=32,message="{constraint.string.length.notinrange}")
    @Column(name = "imie", table = "DanePersonalne", length = 32, nullable = false)
    private String imie;
    @NotNull(message="{constraint.notnull}")
    @Size(min=3,max=32,message="{constraint.string.length.notinrange}")
    @Column(name = "nazwisko", table = "DanePersonalne", length = 32, nullable = false)
    private String nazwisko;

    @NotNull(message="{constraint.notnull}")
    @Size(min=6,max=64,message="{constraint.string.length.notinrange}")
    @Pattern(regexp="^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$",message="{constraint.string.incorrectemail}")
    @Column(name = "email", table = "DanePersonalne", length = 64, unique = true, nullable = false)
    private String email;
        
    @Size(max=12,message="{constraint.string.length.toolong}")
    @Column(name = "telefon", table = "DanePersonalne", length = 12, unique = true, nullable = true)
    private String telefon;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login", fetch = FetchType.EAGER)
    private Set<Grupa> grupy;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "konto", fetch = FetchType.EAGER)
    private Pacjent pacjent;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "konto", fetch = FetchType.EAGER)
    private Lekarz lekarz;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "konto", fetch = FetchType.EAGER)
    private Pracownik pracownik;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public boolean isPotwierdzone() {
        return potwierdzone;
    }

    public void setPotwierdzone(boolean potwierdzone) {
        this.potwierdzone = potwierdzone;
    }

    public boolean isAktywne() {
        return aktywne;
    }

    public void setAktywne(boolean aktywne) {
        this.aktywne = aktywne;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTyp() {
        return typ;
    }

    public String getKodaktywacji() {
        return kodaktywacji;
    }

    public void setKodaktywacji(String kodaktywacji) {
        this.kodaktywacji = kodaktywacji;
    }

    public Set<Grupa> getGrupy() {
        return grupy;
    }

    public void setGrupy(Set<Grupa> grupy) {
        this.grupy = grupy;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

}
