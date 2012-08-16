/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.beans;

import com.pfc.sensormando.entity.Usuarios;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Sobremesa
 */
@ManagedBean
@SessionScoped
public class UserControllerBean implements Serializable {

    /**
     * Usuario perteneciente a la sesión.
     */
    private Usuarios usuario;

    /**
     * Creates a new instance of UserControllerBean
     */
    public UserControllerBean() {
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public boolean isLoggedIn() {
        return usuario != null;
    }

    public boolean isAdmin() {
        return usuario != null && usuario.getAdministrador() == 'y';
    }

}
