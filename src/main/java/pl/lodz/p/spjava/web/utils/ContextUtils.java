/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.web.utils;

/**
 *
 * @author java
 */
import java.security.Principal;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class ContextUtils {

    public ContextUtils() {
    }

    public static ExternalContext getContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public static Object getApplicationAttribute(String attributeName) {
        return getContext().getApplicationMap().get(attributeName);
    }

    public static Object getSessionAttribute(String attributeName) {
        return getContext().getSessionMap().get(attributeName);
    }

    public static Object getRequestAttribute(String attributeName) {
        return getContext().getRequestMap().get(attributeName);
    }

    public static String getContextParameter(String paramName) {
        return getContext().getInitParameter(paramName);
    }

    public static void invalidateSession() {
        ((HttpSession) getContext().getSession(true)).invalidate();
    }

    public static String getSessionID() {
        HttpSession session = (HttpSession) getContext().getSession(true);
        return session.getId();
    }

    public static String getUserName() {
        Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        return (null == p ? "!BRAK UWIERZYTELNIENIA!" : p.getName());
    }

    public static ResourceBundle getDefaultBundle() {
        String bundlePath = getContextParameter("resourceBundle.path");
        if (null == bundlePath) {
            return null;
        } else {
            return ResourceBundle.getBundle(bundlePath);
        }
    }

    public static void emitInternationalizedMessage(String id, String key) {
        FacesMessage msg = new FacesMessage(ContextUtils.getDefaultBundle().getString(key));
        FacesContext.getCurrentInstance().addMessage(id, msg);

    }

    public static void emitSuccessMessage(String id) {
        emitInternationalizedMessage(id, "general.success.message");
    }
}
