/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.facades;

import com.pfc.sensormando.entity.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Raul
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "SensorMando-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    @Override
    public Usuarios checkLogin(String username, String password) {
        Usuarios usuarioBuscado = null;
        List<Usuarios> usuarios =
                em.createNamedQuery("Usuarios.findByUsernameAndPassword")
                .setParameter("username", username)
                .setParameter("password", password)
                .setMaxResults(1)
                .getResultList();
        if (usuarios.size() > 0) {
            usuarioBuscado = usuarios.get(0);
        }
        return usuarioBuscado;
    }

    @Override
    public List<Usuarios> findAllDistinct(Usuarios usuario) {
        List<Usuarios> usuarios =
                em.createNamedQuery("Usuarios.findDistinctUsername")
                .setParameter("username", usuario.getUsername())
                .getResultList();

        return usuarios;
    }

    @Override
    public String getPassword(String email) {
        String password = null;
        List<Usuarios> usuarios =
                em.createNamedQuery("Usuarios.findByEmail")
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultList();
        if (usuarios.size() > 0) {
            Usuarios usuarioBuscado = usuarios.get(0);
            password = usuarioBuscado.getPassword();
        }
        return password;
    }
}
