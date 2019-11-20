/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.entity;

import pl.lodz.p.spjava.web.utils.DateConverter;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author java
 */
@Embeddable
public class WizytaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "LEKARZ", nullable = false)
    private int lekarz;
    @Basic(optional = false)
    @Column(name = "PACJENT", nullable = false)
    private int pacjent;
    @Basic(optional = false)
    @Column(name = "DATA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    public WizytaPK() {
    }

    public WizytaPK(int lekarz, int pacjent, Date data) {
        this.lekarz = lekarz;
        this.pacjent = pacjent;
        this.data = data;
    }

    public int getLekarz() {
        return lekarz;
    }

    public void setLekarz(int lekarz) {
        this.lekarz = lekarz;
    }

    public int getPacjent() {
        return pacjent;
    }

    public void setPacjent(int pacjent) {
        this.pacjent = pacjent;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) lekarz;
        hash += (int) pacjent;
        hash += (data != null ? data.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WizytaPK)) {
            return false;
        }
        WizytaPK other = (WizytaPK) object;
        if (this.lekarz != other.lekarz) {
            return false;
        }
        if (this.pacjent != other.pacjent) {
            return false;
        }
        if ((this.data == null && other.data != null) || (this.data != null && !this.data.equals(other.data))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.spjava.entity.WizytaPK[ lekarz=" + lekarz + ", pacjent=" + pacjent + ", data=" + data + " ]";
    }
    
}
