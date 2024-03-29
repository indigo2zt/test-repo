/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author java
 */
@Entity
@Table(name = "PACJENT", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PESEL"})})
@NamedQueries({
    @NamedQuery(name = "Pacjent.findAll", query = "SELECT p FROM Pacjent p")
    , @NamedQuery(name = "Pacjent.findById", query = "SELECT p FROM Pacjent p WHERE p.id = :id")
    , @NamedQuery(name = "Pacjent.findByImie", query = "SELECT p FROM Pacjent p WHERE p.imie = :imie")
    , @NamedQuery(name = "Pacjent.findByNazwisko", query = "SELECT p FROM Pacjent p WHERE p.nazwisko = :nazwisko")
    , @NamedQuery(name = "Pacjent.findByPesel", query = "SELECT p FROM Pacjent p WHERE p.pesel = :pesel")
    , @NamedQuery(name = "Pacjent.findByLogin", query = "SELECT p FROM Pacjent p WHERE p.konto.login = :login")
    , @NamedQuery(name = "Pacjent.findByKonto", query = "SELECT p FROM Pacjent p WHERE p.konto = :konto")
    , @NamedQuery(name = "Pacjent.findByPrzychodnia", query = "SELECT p FROM Pacjent p JOIN Przychodnia prz Where prz.id = :przychodniaId and p.przychodnia.id = prz.id")})
public class Pacjent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    @NotNull
    private Integer id;
    @Basic(optional = false)
    @Column(name = "IMIE", nullable = false, length = 50)
    private String imie;
    @Basic(optional = false)
    @Size(min = 1)
    @Column(name = "NAZWISKO", nullable = false, length = 50)
    private String nazwisko;
    @Basic(optional = false)
    @Size(min = 1)
    @Column(name = "PESEL", nullable = false, length = 11)
    private String pesel;
    @JoinColumn(name = "PRZYCHODNIA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Przychodnia przychodnia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacjent1", fetch = FetchType.EAGER)
    private Set<Wizyta> wizytaSet;

    @JoinColumn(name = "Konto_id", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Konto konto;


    public Pacjent() {
    }

    public Pacjent(Integer id) {
        this.id = id;
    }

    public Pacjent(Integer id, String imie, String nazwisko, String pesel) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Przychodnia getPrzychodnia() {
        return przychodnia;
    }

    public void setPrzychodnia(Przychodnia przychodnia) {
        this.przychodnia = przychodnia;
    }

    @XmlTransient
    public Set<Wizyta> getWizytaSet() {
        return wizytaSet;
    }

    public void setWizytaSet(Set<Wizyta> wizytaSet) {
        this.wizytaSet = wizytaSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacjent)) {
            return false;
        }
        Pacjent other = (Pacjent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.spjava.entity.Pacjent[ id=" + id + " ]";
    }

    public Object getHaslo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAktywne(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPotwierdzone(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setHaslo(String wyliczSkrotHasla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }
}
