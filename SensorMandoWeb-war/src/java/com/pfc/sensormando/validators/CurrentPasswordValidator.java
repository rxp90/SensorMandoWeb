/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.validators;

import com.pfc.sensormando.beans.UserControllerBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Raul
 */
/* Para que funcione la inyección de dependencia.
 @FacesValidator("com.pfc.sensormando.PasswordValidator")
 */
@ManagedBean
@RequestScoped
public class CurrentPasswordValidator implements Validator {

    @ManagedProperty(value = "#{userControllerBean}")
    private UserControllerBean userControllerBean;

    public void setUserControllerBean(UserControllerBean userControllerBean) {
        this.userControllerBean = userControllerBean;
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String currentPassword = (String) value;

        if (currentPassword.equals("")) {
            FacesMessage msg = new FacesMessage("Introduzca su contraseña actual.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else if (!currentPassword.equals(userControllerBean.getUsuario().getPassword())) {
            FacesMessage msg = new FacesMessage("La contraseña introducida no coincide con la contraseña de esta cuenta.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
