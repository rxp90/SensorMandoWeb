/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.facades;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author Raul
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;
    private static final Logger logger = Logger.getLogger(AbstractFacade.class.getName());

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public boolean create(T entity) {
        boolean success;
        try {
            getEntityManager().persist(entity);
            success = true;
            logger.log(Level.INFO, "Objeto insertado {0}", entity);
        } catch (Throwable e) {
            success = false;
            logger.log(Level.SEVERE, "Error en la transacción {0}", e.getMessage());
        }
        return success;
    }

    public boolean edit(T entity) {
        boolean success;
        try {
            getEntityManager().merge(entity);
            success = true;
            logger.log(Level.INFO, "Objeto editado {0}", entity);
        } catch (Throwable e) {
            success = false;
            logger.log(Level.SEVERE, "Error en la transacción {0}", e.getMessage());
        }
        return success;
    }

    public boolean remove(T entity) {
        boolean success;
        try {
            getEntityManager().remove(getEntityManager().merge(entity));
            success = true;
            logger.log(Level.INFO, "Objeto eliminado {0}", entity);
        } catch (Throwable e) {
            success = false;
            logger.log(Level.SEVERE, "Error en la transacción {0}", e.getMessage());
        }
        return success;
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findByParameters(Predicate... predicates) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        criteriaQuery.where(criteriaBuilder.and(predicates));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
