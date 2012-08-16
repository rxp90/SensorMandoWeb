/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.beans;

import com.pfc.sensormando.entity.Usuarios;
import com.pfc.sensormando.facades.UsuariosFacadeLocal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Sobremesa
 */
@ManagedBean
@RequestScoped
public class ModificarPerfilBean {

    @EJB
    private UsuariosFacadeLocal usuariosFacade;
    @ManagedProperty(value = "#{userControllerBean}")
    private UserControllerBean userControllerBean;
    //Creo que no es necesario, que se puede modificar en UserControllerBean
    private Usuarios usuario;
    private int exito;
    private final int CORRECTO = 1;
    private final int ERROR = -1;

    /**
     * Creates a new instance of ModificarPerfilBean
     */
    public ModificarPerfilBean() {
        this.usuario = userControllerBean.getUsuario();
    }

    public void actualizaUsuario() {
        if (usuario != null) {
            if (usuariosFacade.edit(usuario)) {
                exito = CORRECTO;
            } else {
                exito = ERROR;
            }
        }
    }

    public void setUserControllerBean(UserControllerBean userControllerBean) {
        this.userControllerBean = userControllerBean;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public int getExito() {
        return exito;
    }

    public int getCORRECTO() {
        return CORRECTO;
    }

    public int getERROR() {
        return ERROR;
    }
}
