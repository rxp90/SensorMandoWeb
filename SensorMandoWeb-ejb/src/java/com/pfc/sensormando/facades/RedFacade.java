/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.facades;

import com.pfc.sensormando.entity.Receptor;
import com.pfc.sensormando.entity.Red;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
            queryBuilder.append(" AND r.ID_RED = ").append(idRed);
        }
        if (ip != null) {
            queryBuilder.append(" AND r.ip LIKE '").append(ip).append("'");
        }
        if (nombre != null) {
            queryBuilder.append(" AND r.nombre LIKE '").append(nombre).append("'");
        }
        if (puerto != null) {
            queryBuilder.append(" AND r.puerto = ").append(puerto);
        }

        // NativeQuery devuelve los resultados en un ArrayList de Object[]
        List<Object[]> resultados = em.createNativeQuery(queryBuilder.toString()).getResultList();
        List<Red> resultadosRed = new ArrayList<Red>();

        for (Object[] object : resultados) {
            Red red = new Red((Integer) object[0], (Integer) object[1], (Integer) object[2], (String) object[3], (String) object[4], (Integer) object[5]);
            List<Receptor> receptoresQuery = em.createNamedQuery("Receptor.findByRed").setParameter("red", red).getResultList();
            if (receptoresQuery.size() > 0) {
                Set<Receptor> receptoresRed = new HashSet<Receptor>();
                receptoresRed.addAll(receptoresQuery);
                red.setReceptorSet(receptoresRed);
            }
            resultadosRed.add(red);
        }
        return resultadosRed;
    }
}
