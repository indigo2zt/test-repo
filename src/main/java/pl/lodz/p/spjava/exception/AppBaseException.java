/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.exception;

/**
 *
 * @author java
 */

import javax.ejb.ApplicationException;


@ApplicationException(rollback=true)
public abstract class AppBaseException extends Exception {

    private String message;
    
    public AppBaseException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
    
}
