/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.facades;

import com.pfc.sensormando.entity.Red;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RedFacade extends AbstractFacade<Red> implements RedFacadeLocal {

    @PersistenceContext(unitName = "SensorMando-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RedFacade() {
        super(Red.class);
    }

    @Override
    public List<Red> findByParameters(Integer id, Integer canal, Integer idRed, String ip, String nombre, Integer puerto) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Red r WHERE 1=1");
        if (id != null) {
            queryBuilder.append(" AND r.id = ").append(id);
        }
        if (canal != null) {
            queryBuilder.append(" AND r.canal = ").append(canal);
        }
        if (idRed != null) {
            queryBuilder.append(" AND r.idRed = ").append(idRed);
        }
        if (ip != null) {
            queryBuilder.append(" AND r.ip = ").append(ip);
        }
        if (nombre != null) {
            queryBuilder.append(" AND r.nombre = ").append(nombre);
        }
        if (puerto != null) {
            queryBuilder.append(" AND r.puerto = ").append(puerto);
        }
        List<Red> resultados = em.createNativeQuery(queryBuilder.toString()).getResultList();
        return resultados;
    }
}
