package iuh.fit.se.daos.impl;


import iuh.fit.se.daos.UserDAO;
import iuh.fit.se.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();

            if(transaction!=null && transaction.isActive()) {
                transaction.rollback();
            }
        }

        return null;
    }

    @Override
    public User update(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();

            if(transaction!=null && transaction.isActive()) {
                transaction.rollback();
            }
        }

        return null;
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user = entityManager.find(User.class, id);
            if (user != null) {
                entityManager.remove(user);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if(transaction!=null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public User findById(int id) {
        try {
            User user = entityManager.find(User.class, id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        try {
            return entityManager
                    .createQuery("SELECT u FROM User u", User.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
