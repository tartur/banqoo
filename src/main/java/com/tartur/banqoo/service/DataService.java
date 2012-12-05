package com.tartur.banqoo.service;

import com.tartur.banqoo.model.Account;
import com.tartur.banqoo.model.Identifiable;
import com.tartur.banqoo.model.Member;
import com.tartur.banqoo.model.User;

import javax.persistence.*;

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
    private InheritableThreadLocal<EntityManager> em = new InheritableThreadLocal<>();

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    protected void beginTransaction() {
        createEM().getTransaction().begin();
    }

    protected EntityManager createEM() {
        em.set(emf.createEntityManager());
        return em.get();
    }

    protected void commitTransaction() {
        em.get().getTransaction().commit();
    }

    protected void endTransaction() {
        EntityManager tem = em.get();
        if (tem.getTransaction().isActive())
            tem.getTransaction().rollback();
        tem.close();
    }

    protected <T extends Identifiable<?>> T save_(T e) {
        EntityManager tem = em.get();
        if (tem.contains(e)) {
            tem.flush();
        } else {
            if (entityExists(e))
                e = tem.merge(e);
            else
                tem.persist(e);
        }
        return e;
    }

    private <T extends Identifiable<?>> boolean entityExists(T e) {
        EntityManager tem = em.get();
        try {
            Identifiable ref = tem.getReference(e.getClass(), e.getId());
            return ref != null && ref.getId() != null;
        } catch (EntityNotFoundException | IllegalArgumentException ex) {
            return false;
        }
    }

    public <T extends Identifiable<?>> T create(T entity) {
        try {
            beginTransaction();
            entity = save_(entity);
            commitTransaction();
            return entity;
        } finally {
            endTransaction();
        }
    }

    public Account createAccount(Account account) {
        try {
            beginTransaction();
            User owner = account.getOwner();
            account.put(new Member(owner, Member.MemberRole.Admin, account));
            account = save_(account);
            commitTransaction();
            return account;
        } finally {
            endTransaction();
        }

    }

    public User findUserByUsername(String username) {
        return createEM().find(User.class, username);
    }

    public User findUserByEmail(String email) {
        TypedQuery<User> query = createEM().createQuery("from User where emailAddress = :email", User.class);
        query.setParameter("email", email);
        return oneOrNull(query);
    }

    private <T> T oneOrNull(TypedQuery<T> query) {
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public <T extends Identifiable<?>> void update(T entity) {
        create(entity);
    }

    public Account findAccountByOwnerAndName(User owner, String accountName) {
        TypedQuery<Account> query = createEM().createQuery("from " + Account.class.getSimpleName() + " where owner = :owner and name = :name", Account.class);
        query.setParameter("owner", owner);
        query.setParameter("name", accountName);
        return oneOrNull(query);
    }
}
