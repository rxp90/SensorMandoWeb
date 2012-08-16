/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.beans;

import com.pfc.sensormando.entity.Usuarios;
import com.pfc.sensormando.facades.UsuariosFacadeLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;

/**
 *
 * @author Sobremesa
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    @EJB
    private UsuariosFacadeLocal usuariosFacade;
    @ManagedProperty(value = "#{userControllerBean}")
    private UserControllerBean userControllerBean;
    @Size(min = 1)
    private String name;
    @Size(min = 1)
    private String password;
    private final int INCORRECTO = -1;
    private final int BAJA = -2;
    private int exitoLogin;

    public LoginBean() {
    }

    public String login() {
        String redireccion;
        Usuarios user = null;
        if (name != null && password != null) {
            user = usuariosFacade.checkLogin(name, password);
        }
        if (user != null) {
            if (user.getBaja() == 'n') {
                userControllerBean.setUsuario(user);
                redireccion = "login";
            } else {
                exitoLogin = BAJA;
                redireccion = "";
            }
        } else {
            redireccion = "";
            exitoLogin = INCORRECTO;
        }
        return redireccion;
    }

    public void logout() {
        userControllerBean.setUsuario(null);
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public int getExitoLogin() {
        return exitoLogin;
    }

    public int getINCORRECTO() {
        return INCORRECTO;
    }

    public int getBAJA() {
        return BAJA;
    }

    public void setUserControllerBean(UserControllerBean userControllerBean) {
        this.userControllerBean = userControllerBean;
    }
}
