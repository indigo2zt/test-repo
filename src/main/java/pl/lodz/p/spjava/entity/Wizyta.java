/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author java
 */
@Entity
@Table(name = "WIZYTA", schema="", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"pacjent", "data"}),
    @UniqueConstraint(columnNames = {"POKOJ","data"}),
    @UniqueConstraint(columnNames = {"lekarz","data"})
})
@NamedQueries({
    @NamedQuery(name = "Wizyta.findAll", query = "SELECT w FROM Wizyta w")
    , @NamedQuery(name = "Wizyta.findByLekarz", query = "SELECT w FROM Wizyta w WHERE w.wizytaPK.lekarz = :lekarz")
    , @NamedQuery(name = "Wizyta.findByPacjent", query = "SELECT w FROM Wizyta w WHERE w.wizytaPK.pacjent = :pacjent")
    , @NamedQuery(name = "Wizyta.findByData", query = "SELECT w FROM Wizyta w WHERE w.wizytaPK.data = :data")
    , @NamedQuery(name = "Wizyta.findByPokoj", query = "SELECT w FROM Wizyta w WHERE w.pokoj = :pokoj")})
public class Wizyta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WizytaPK wizytaPK;
    @Basic(optional = false)
    @Size(min = 1, max = 5)
    @Column(name = "POKOJ", nullable = false, length = 5)
    private String pokoj;
    @JoinColumn(name = "LEKARZ", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lekarz lekarz1;
    @JoinColumn(name = "PACJENT", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pacjent pacjent1;

    public Wizyta() {
    }

    public Wizyta(WizytaPK wizytaPK) {
        this.wizytaPK = wizytaPK;
    }

    public Wizyta(WizytaPK wizytaPK, String pokoj) {
        this.wizytaPK = wizytaPK;
        this.pokoj = pokoj;
    }

    public Wizyta(int lekarz, int pacjent, Date data) {
        this.wizytaPK = new WizytaPK(lekarz, pacjent, data);
    }

    public WizytaPK getWizytaPK() {
        return wizytaPK;
    }

    public void setWizytaPK(WizytaPK wizytaPK) {
        this.wizytaPK = wizytaPK;
    }

    public String getPokoj() {
        return pokoj;
    }

    public void setPokoj(String pokoj) {
        this.pokoj = pokoj;
    }

    public Lekarz getLekarz1() {
        return lekarz1;
    }

    public void setLekarz1(Lekarz lekarz1) {
        this.lekarz1 = lekarz1;
    }

    public Pacjent getPacjent1() {
        return pacjent1;
    }

    public void setPacjent1(Pacjent pacjent1) {
        this.pacjent1 = pacjent1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wizytaPK != null ? wizytaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wizyta)) {
            return false;
        }
        Wizyta other = (Wizyta) object;
        if ((this.wizytaPK == null && other.wizytaPK != null) || (this.wizytaPK != null && !this.wizytaPK.equals(other.wizytaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.spjava.entity.Wizyta[ wizytaPK=" + wizytaPK + " ]";
    }
    
}
