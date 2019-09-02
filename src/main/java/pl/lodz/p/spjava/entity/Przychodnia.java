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
@Table(name = "PRZYCHODNIA")
@NamedQueries({
    @NamedQuery(name = "Przychodnia.findAll", query = "SELECT p FROM Przychodnia p")
    , @NamedQuery(name = "Przychodnia.findById", query = "SELECT p FROM Przychodnia p WHERE p.id = :id")
    , @NamedQuery(name = "Przychodnia.findByNazwa", query = "SELECT p FROM Przychodnia p WHERE p.nazwa = :nazwa")
    , @NamedQuery(name = "Przychodnia.findByOpis", query = "SELECT p FROM Przychodnia p WHERE p.opis = :opis")
    , @NamedQuery(name = "Przychodnia.findByAdres", query = "SELECT p FROM Przychodnia p WHERE p.adres = :adres")
    , @NamedQuery(name = "Przychodnia.findByKontakt", query = "SELECT p FROM Przychodnia p WHERE p.kontakt = :kontakt")})
public class Przychodnia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAZWA", nullable = false, length = 100)
    private String nazwa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "OPIS", nullable = false, length = 300)
    private String opis;
    @Size(max = 150)
    @Column(name = "ADRES", length = 150)
    private String adres;
    @Size(max = 30)
    @Column(name = "KONTAKT", length = 30)
    private String kontakt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "przychodnia", fetch = FetchType.EAGER)
    private Set<Pacjent> pacjentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "przychodnia", fetch = FetchType.EAGER)
    private Set<Lekarz> lekarzList;
    private Set<Pacjent> pacjentList;
   

    public Przychodnia() {
    }

    public Przychodnia(Integer id) {
        this.id = id;
    }

    public Przychodnia(Integer id, String nazwa, String opis) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    @XmlTransient
    public Set<Pacjent> getPacjentSet() {
        return pacjentSet;
    }

    public void setPacjentList(Set<Pacjent> pacjentList) {
        this.pacjentList = pacjentList;
    }

    @XmlTransient
    public Set<Lekarz> getLekarzList() {
        return lekarzList;
    }

    public void setLekarzList(Set<Lekarz> lekarzList) {
        this.lekarzList = lekarzList;
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
        if (!(object instanceof Przychodnia)) {
            return false;
        }
        Przychodnia other = (Przychodnia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.spjava.entity.Przychodnia[ id=" + id + " ]";
    }
    
}
