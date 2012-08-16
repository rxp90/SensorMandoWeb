/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.facades;

import com.pfc.sensormando.entity.Receptor;
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
    
}
