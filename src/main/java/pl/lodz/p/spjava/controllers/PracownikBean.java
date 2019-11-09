/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author java
 */
@Named("Pracownik")
@ViewScoped

public class PracownikBean implements Serializable {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String newValue) {
        name = newValue;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newValue) {
        password = newValue;
    }
}
