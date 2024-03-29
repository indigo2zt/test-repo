/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.ejb.intereceptor;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author java
 */
public class LoggingInterceptor implements Serializable {


    @AroundInvoke
    public Object additionalInvokeForMethod(InvocationContext invocation) throws Exception {
        StringBuilder sb = new StringBuilder("Wywołanie metody biznesowej ")
                .append(invocation.getTarget().getClass().getName()).append('.')
                .append(invocation.getMethod().getName());
        //sb.append(" z tożsamością: ").append(sessionContext.getCallerPrincipal().getName());
        try {
            Object[] parameters = invocation.getParameters();
            if (null != parameters) {
                for (Object param : parameters) {
                    if (param != null) {
                        sb.append(" z parametrem ").append(param.getClass().getName()).append('=').append(param);
                    } else {
                        sb.append(" bez wartości (null)");
                    }
                }
            }

            Object result = invocation.proceed();

            if (result != null) {
                sb.append(" zwrócono ").append(result.getClass().getName()).append('=').append(result);
            } else {
                sb.append(" zwrócono wartość null");
            }

            return result;
        } catch (Exception ex) {
            sb.append(" wystapil wyjatek: ").append(ex);
            throw ex; //ponowne zgloszenie wyjatku
        } finally {
            Logger.getGlobal().log(Level.INFO, sb.toString());
        }
    }
}

