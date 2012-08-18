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
public class MandoFacade extends AbstractFacade<Mando> implements MandoFacadeLocal {

    @PersistenceContext(unitName = "SensorMando-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MandoFacade() {
        super(Mando.class);
    }

    @Override
    public List<Mando> findByParameters(Integer id, Integer direccion, String nombre, Integer idReceptor) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Mando m WHERE 1=1");
        if (id != null) {
            queryBuilder.append(" AND m.id = ").append(id);
        }

        if (direccion != null) {
            queryBuilder.append(" AND m.direccion = ").append(direccion);
        }
        if (nombre != null) {
            queryBuilder.append(" AND m.nombre LIKE '").append(nombre).append("'");
        }
        if (idReceptor != null) {
            queryBuilder.append(" AND m.idReceptor = ").append(idReceptor);
        }


        // NativeQuery devuelve los resultados en un ArrayList de Object[]
        List<Object[]> resultados = em.createNativeQuery(queryBuilder.toString()).getResultList();
        List<Mando> resultadosMando = new ArrayList<Mando>();

        for (Object[] object : resultados) {
            Mando mando = new Mando((Integer) object[0]);
            mando.setDireccion((Integer) object[1]);
            mando.setNombre((String) object[2]);

            Integer idReceptorQuery = (Integer) object[3];
            Receptor receptorMando = (Receptor) em.createNamedQuery("Receptor.findById").setParameter("id", idReceptorQuery).getSingleResult();

            mando.setIdReceptor(receptorMando);

            resultadosMando.add(mando);
        }
        return resultadosMando;
    }
}
