package com.tartur.banqoo.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 * Created with IntelliJ IDEA.
 * User: tartur
 * Date: 12/2/12
 * Time: 12:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class CRUDService {

    @PersistenceContext
    private EntityManagerFactory emf;
    private UserTransaction tx;

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public <T> T create(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(entity);
                tx.commit();
                return entity;
            } finally {
                if (tx.isActive())
                    tx.rollback();
            }
        } finally {
            em.close();
        }
    }
}
