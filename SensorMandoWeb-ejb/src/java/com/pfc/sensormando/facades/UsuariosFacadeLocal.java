/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.facades;

import com.pfc.sensormando.entity.Usuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Raul
 */
@Local
public interface UsuariosFacadeLocal {

    boolean create(Usuarios usuarios);

    boolean edit(Usuarios usuarios);

    boolean remove(Usuarios usuarios);

    List<Usuarios> findAllDistinct(Usuarios usuario);
    
    String getPassword(String email);

    Usuarios checkLogin(String username, String password);

    Usuarios find(Object id);

    List<Usuarios> findAll();

    List<Usuarios> findRange(int[] range);

    int count();
}
