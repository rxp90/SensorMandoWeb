/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.validators;

import com.pfc.sensormando.beans.UserControllerBean;
import com.pfc.sensormando.entity.Usuarios;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
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
public class NewPasswordValidator implements Validator {

    @ManagedProperty(value = "#{userControllerBean}")
    private UserControllerBean userControllerBean;
    private UIInput newPasswordComponent;
    /* ¿Innecesario si lo cojo de newPasswordComponent? */
    private String newPasswordValue;
    private int resultado;
    private final int CORRECTO = 1;
    private final int ERROR = -1;

    public void setUserControllerBean(UserControllerBean userControllerBean) {
        this.userControllerBean = userControllerBean;
    }

    public UIInput getNewPasswordComponent() {
        return newPasswordComponent;
    }

    public void setNewPasswordComponent(UIInput newPasswordComponent) {
        this.newPasswordComponent = newPasswordComponent;
    }

    public String getNewPasswordValue() {
        return newPasswordValue;
    }

    public void setNewPasswordValue(String newPasswordValue) {
        this.newPasswordValue = newPasswordValue;
    }

//    public void changePassword() {
//        if (userControllerBean.isLoggedIn()) {
//            Usuarios user = userControllerBean.getUsuario();
//            user.setPassword(newPasswordValue);
//            boolean exito = userControllerBean.actualizaUsuario();
//            if (exito) {
//                resultado = CORRECTO;
//            } else {
//                resultado = ERROR;
//            }
//        }
//    }

    public int getResultado() {
        return resultado;
    }

    public int getCORRECTO() {
        return CORRECTO;
    }

    public int getERROR() {
        return ERROR;
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String repeatedPassword = (String) value;
        String newPassword = (String) getNewPasswordComponent().getLocalValue();

        if (newPassword.equals("") || repeatedPassword.equals("")) {
            FacesMessage msg = new FacesMessage("Introduzca una contraseña.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else if (!repeatedPassword.equals(newPassword)) {
            FacesMessage msg = new FacesMessage("Las contraseñas no coinciden");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
