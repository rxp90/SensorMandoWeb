/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.facades;

import com.pfc.sensormando.entity.Mando;
import com.pfc.sensormando.entity.Receptor;
import com.pfc.sensormando.entity.Red;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Raul
 */
@Stateless
public class ReceptorFacade extends AbstractFacade<Receptor> implements ReceptorFacadeLocal {

    @PersistenceContext(unitName = "SensorMando-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReceptorFacade() {
        super(Receptor.class);
    }

    @Override
    public List<Receptor> findByParameters(Integer id, Integer direccion, String nombre, Integer idRed) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Receptor r WHERE 1=1");
        if (id != null) {
            queryBuilder.append(" AND r.id = ").append(id);
        }

        if (direccion != null) {
            queryBuilder.append(" AND r.direccion = ").append(direccion);
        }
        if (nombre != null) {
            queryBuilder.append(" AND r.nombre LIKE '").append(nombre).append("'");
        }
        if (idRed != null) {
            queryBuilder.append(" AND r.red = ").append(idRed);
        }


        // NativeQuery devuelve los resultados en un ArrayList de Object[]
        List<Object[]> resultados = em.createNativeQuery(queryBuilder.toString()).getResultList();
        List<Receptor> resultadosReceptor = new ArrayList<Receptor>();

        for (Object[] object : resultados) {
            Receptor receptor = new Receptor((Integer) object[0]);
            receptor.setDireccion((Integer) object[1]);
            receptor.setNombre((String) object[2]);
            
            Integer idRedQuery = (Integer) object[3];
            Red redReceptor = (Red) em.createNamedQuery("Red.findById").setParameter("id", idRedQuery).getSingleResult();

            receptor.setRed(redReceptor);
            
            List<Mando> mandosQuery = em.createNamedQuery("Mando.findByReceptor").setParameter("idReceptor", receptor).getResultList();
            if (mandosQuery.size() > 0) {
                Set<Mando> mandosReceptor = new HashSet<Mando>();
                mandosReceptor.addAll(mandosQuery);
                receptor.setMandoSet(mandosReceptor);
            }
            resultadosReceptor.add(receptor);
        }
        return resultadosReceptor;
    }
}
