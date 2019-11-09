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

/**
 * Klasa bazowego wyjątku aplikacyjnego
 */
@ApplicationException(rollback = true)
abstract public class AppBaseException extends Exception {

    protected AppBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    protected AppBaseException(String message) {
        super(message);
    }

}
