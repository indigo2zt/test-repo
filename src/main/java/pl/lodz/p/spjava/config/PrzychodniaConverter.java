/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.config;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import pl.lodz.p.spjava.entity.Przychodnia;

/**
 *
 * @author java
 */
public class PrzychodniaConverter implements Converter {
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
    if (modelValue == null) {
        return "";
    }

    if (modelValue instanceof Przychodnia) {
        return String.valueOf(((Przychodnia) modelValue).getId());
    } else {
        throw new ConverterException(new FacesMessage(modelValue + " is not a valid Przychodnia"));
    }
    
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

//    @Override
//    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}
