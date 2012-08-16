/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.beans;

import com.pfc.sensormando.entity.Usuarios;
import com.pfc.sensormando.facades.UsuariosFacadeLocal;
//import com.pfc.sensormando.hibernate.Helper;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Raul
 */
@ManagedBean
@RequestScoped
public class AdministrarUsuariosBean implements Serializable {
    @EJB
    private UsuariosFacadeLocal usuariosFacade;

    @ManagedProperty(value = "#{userControllerBean}")
    private UserControllerBean userControllerBean;
    private String stringUsuario;
    private Usuarios usuario;
    private int exito;
    private final int CORRECTO = 1;
    private final int ERROR = -1;
    private List<Usuarios> usuarios;

    public void setUserControllerBean(UserControllerBean userControllerBean) {
        this.userControllerBean = userControllerBean;
    }

    /**
     * Creates a new instance of AdministrarUsuariosBean
     */
    public AdministrarUsuariosBean() {
    }

    @PostConstruct
    public void init() {
       // helper = new Helper();
        this.usuarios = usuariosFacade.findAllDistinct(userControllerBean.getUsuario());
        // Rellenamos los datos con el primer usuario.
        this.usuario = usuarios.get(0);
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public String getStringUsuario() {
        return stringUsuario;
    }

    public void setStringUsuario(String stringUsuario) {
        this.stringUsuario = stringUsuario;
        // Ahorro una búsqueda en la base de datos, pues tengo todos los datos en la cadena.
        usuario = new Usuarios(stringUsuario);
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void modificarUsuario() {
        if (usuario != null) {
            if (usuariosFacade.edit(usuario)) {
                exito = CORRECTO;
            } else {
                exito = ERROR;
            }
        }
    }

    public void eliminarUsuario() {
        if (usuario != null) {
            if (usuariosFacade.remove(usuario)) {
                exito = CORRECTO;
                usuario = null;
            } else {
                exito = ERROR;
            }
        }
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