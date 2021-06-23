/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln.service;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author alex4
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public String create(T entity) {
        try {
            getEntityManager().persist(entity);
            return "Ingreso Correcto";
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.AbstractFacade.create() " + ex);
            return "Error Ingreso" + ex.toString();
        }
    }

    public String edit(T entity) {
        try {
            getEntityManager().merge(entity);
            return "Edicion Correcta";
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.AbstractFacade.create() " + ex);
            return "Error Edicion" + ex.toString();
        }
    }

    public String remove(T entity) {
        try {
            getEntityManager().remove(getEntityManager().merge(entity));
            return "Eliminacion Correcta";
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.AbstractFacade.create() " + ex);
            return "Error Eliminacion " + ex.toString();
        }
    }

    public T find(Object id) {
        try {
            return getEntityManager().find(entityClass, id);
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.AbstractFacade.find() " + ex);
            return null;
        }
    }

    public List<T> findAll() {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            return getEntityManager().createQuery(cq).getResultList();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.AbstractFacade.findAll() " + ex);
            return null;
        }
    }

    public List<T> findRange(int[] range) {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            q.setMaxResults(range[1] - range[0] + 1);
            q.setFirstResult(range[0]);
            return q.getResultList();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.AbstractFacade.findRange() " + ex);
            return null;
        }
    }

    public int count() {
        try {
            javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
            cq.select(getEntityManager().getCriteriaBuilder().count(rt));
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            System.out.println("com.comedorln.service.AbstractFacade.count() " + ex);
            return -1;
        }
    }

}
