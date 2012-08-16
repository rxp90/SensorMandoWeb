/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.beans;

import com.pfc.sensormando.entity.Usuarios;
import com.pfc.sensormando.facades.UsuariosFacadeLocal;
import com.pfc.sensormando.utilidades.EnviarEmail;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Sobremesa
 */
@ManagedBean
@RequestScoped
public class RegistroBean {
    @EJB
    private UsuariosFacadeLocal usuariosFacade;


    private Usuarios user;
    private boolean baja;
    private boolean administrador;
    private int exito;
    private final int REGISTRO_CORRECTO = 1;
    private final int REGISTRO_CORRECTO_ERROR_ENVIAR_EMAIL = 2;
    private final int ERROR_REGISTRO = -1;

    /**
     * Creates a new instance of RegistroBean
     */
    public RegistroBean() {
        this.user = new Usuarios();
    }

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public void registrarse() {

        char bajaChar = 'n';
        char adminChar = 'n';

        if (baja) {
            bajaChar = 'y';
        }
        if (administrador) {
            adminChar = 'y';
        }
        user.setAdministrador(adminChar);
        user.setBaja(bajaChar);
        boolean usuarioCreado = usuariosFacade.create(user);
        
        if (usuarioCreado) {

            EnviarEmail emailSender = new EnviarEmail();
            emailSender.setDestinatario(user.getEmail());
            emailSender.setAsunto("Cuenta creada en Sensor Mando");

            StringBuilder cuerpo = new StringBuilder("Enhorabuena. Un administrador te ha creado una cuenta para la aplicación Sensor Mando. Aquí tienes tus datos para iniciar sesión.\n\n\tNombre de usuario: ");
            cuerpo.append(user.getUsername());
            cuerpo.append("\n\tContraseña: ");
            cuerpo.append(user.getPassword());

            emailSender.setCuerpo(cuerpo.toString());
            int exitoEmail = emailSender.sendEmail();

            switch (exitoEmail) {
                case EnviarEmail.EMAIL_ENVIADO:
                    exito = REGISTRO_CORRECTO;
                    break;
                case EnviarEmail.ERROR_ENVIANDO:
                    exito = REGISTRO_CORRECTO_ERROR_ENVIAR_EMAIL;
                    break;
            }
        } else {
            exito = ERROR_REGISTRO;
        }
    }

    public int getExito() {
        return exito;
    }

    public int getREGISTRO_CORRECTO() {
        return REGISTRO_CORRECTO;
    }

    public int getREGISTRO_CORRECTO_ERROR_ENVIAR_EMAIL() {
        return REGISTRO_CORRECTO_ERROR_ENVIAR_EMAIL;
    }

    public int getERROR_REGISTRO() {
        return ERROR_REGISTRO;
    }
}
