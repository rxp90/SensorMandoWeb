/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.beans;

//import com.pfc.sensormando.hibernate.Helper;
import com.pfc.sensormando.facades.UsuariosFacadeLocal;
import com.pfc.sensormando.utilidades.EnviarEmail;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Sobremesa
 */
@ManagedBean
@RequestScoped
public class RecuperarPasswordBean {

    @EJB
    private UsuariosFacadeLocal usuariosFacade;
    private static final int EMAIL_INEXISTENTE_BD = 1;
    @Email
    private String email;
    private int emailEnviado = -2;

    /**
     * Creates a new instance of RecuperarPasswordBean
     */
    public RecuperarPasswordBean() {
    }

    public void recuperarPassword() {
        EnviarEmail emailSender = new EnviarEmail();
        if (email != null) {
            String existingPassword = usuariosFacade.getPassword(email);
            if (existingPassword != null) {
                emailSender.setDestinatario(email);
                emailSender.setAsunto("Recuperación de contraseña Sensor Mando");

                StringBuilder cuerpo = new StringBuilder("\"Estás recibiendo este email porque solicistate recordar tu contraseña del servicio Sensor Mando. \nPassword: ");
                cuerpo.append(existingPassword);

                emailSender.setCuerpo(cuerpo.toString());

                emailEnviado = emailSender.sendEmail();
            } else {
                emailEnviado = EMAIL_INEXISTENTE_BD;
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmailEnviado() {
        return emailEnviado;
    }

    public void setEmailEnviado(int emailEnviado) {
        this.emailEnviado = emailEnviado;
    }
}
