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

/**
 *
 */
@Entity
@Table(name = "DaneAdministrator")
@DiscriminatorValue("ADMIN")
@NamedQueries({
    @NamedQuery(name = "Administrator.findAll", query = "SELECT d FROM Administrator d"),
    @NamedQuery(name = "Administrator.findByAlarmNumber", query = "SELECT d FROM Administrator d WHERE d.alarmNumber = :alarmNumber")
})
public class Administrator extends Konto implements Serializable {

    @NotNull
    @Size(message="{constraint.string.length.toolong}")
    @Column(name = "AlarmNumber", unique=true, nullable=false, length=12)
    private String alarmNumber;

    public Administrator() {
    }

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String number) {
        this.alarmNumber = number;
    }
}
