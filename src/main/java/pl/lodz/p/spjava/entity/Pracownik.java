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
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DanePracownik")
@DiscriminatorValue("PRACOWNIK")
@NamedQueries({
    @NamedQuery(name = "Pracownik.findAll", query = "SELECT d FROM Pracownik d"),
    @NamedQuery(name = "Pracownik.findByIntercom", query = "SELECT d FROM Pracownik d WHERE d.intercom = :intercom")
})
public class Pracownik extends Konto implements Serializable {

    @NotNull
    @Size(message="{constraint.string.length.toolong}")
    @Column(name = "intercom", unique=true, nullable=false, length=12)
    private String intercom;

    @JoinColumn(name = "Konto_id", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Konto konto;


    public Pracownik() {
    }

    public String getIntercom() {
        return intercom;
    }

    public void setIntercom(String intercom) {
        this.intercom = intercom;
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }
}

