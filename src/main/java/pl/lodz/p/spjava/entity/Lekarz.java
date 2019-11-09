/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author java
 */
@Entity
@Table(name = "LEKARZ")
@NamedQueries({
    @NamedQuery(name = "Lekarz.findAll", query = "SELECT l FROM Lekarz l")
    , @NamedQuery(name = "Lekarz.findById", query = "SELECT l FROM Lekarz l WHERE l.id = :id")
    , @NamedQuery(name = "Lekarz.findByImie", query = "SELECT l FROM Lekarz l WHERE l.imie = :imie")
    , @NamedQuery(name = "Lekarz.findByNazwisko", query = "SELECT l FROM Lekarz l WHERE l.nazwisko = :nazwisko")
    , @NamedQuery(name = "Lekarz.findBySpecjalizacja", query = "SELECT l FROM Lekarz l WHERE l.specjalizacja = :specjalizacja")})
public class Lekarz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "IMIE", nullable = false, length = 50)
    private String imie;
    @Basic(optional = false)
    @Column(name = "NAZWISKO", nullable = false, length = 50)
    private String nazwisko;
    @Basic(optional = false)
    @Column(name = "SPECJALIZACJA", nullable = false, length = 50)
    private String specjalizacja;
    @JoinColumn(name = "PRZYCHODNIA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Przychodnia przychodnia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lekarz1", fetch = FetchType.EAGER)
    private Set<Wizyta> wizytaList;

    public Lekarz() {
    }

    public Lekarz(Integer id) {
        this.id = id;
    }

    public Lekarz(Integer id, String imie, String nazwisko, String specjalizacja) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.specjalizacja = specjalizacja;
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

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public Przychodnia getPrzychodnia() {
        return przychodnia;
    }

    public void setPrzychodnia(Przychodnia przychodnia) {
        this.przychodnia = przychodnia;
    }

    @XmlTransient
    public Set<Wizyta> getWizytaList() {
        return wizytaList;
    }

    public void setWizytaSet(Set<Wizyta> wizytaList) {
        this.wizytaList = wizytaList;
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
        if (!(object instanceof Lekarz)) {
            return false;
        }
        Lekarz other = (Lekarz) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.spjava.entity.Lekarz[ id=" + id + " ]";
    }

}
