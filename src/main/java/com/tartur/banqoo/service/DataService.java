package com.tartur.banqoo.service;

import com.google.common.collect.ImmutableMap;
import com.tartur.banqoo.model.User;

import javax.persistence.*;
import javax.transaction.UserTransaction;

/**
 * Created with IntelliJ IDEA.
 * User: tartur
 * Date: 12/2/12
 * Time: 12:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataService {

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

    public User findUserByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        return em.find(User.class, username);
    }

    public User findUserByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("from User where emailAddress = :email", User.class);
        query.setParameter("email",email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public <T> void update(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.merge(entity);
                tx.commit();
            } finally {
                if (tx.isActive())
                    tx.rollback();
            }
        } finally {
            em.close();
        }
    }
}
