package com.example.SpringBootCRUDApp.dao;

import com.example.SpringBootCRUDApp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public User readUser(long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("User with id = " + id + " not exist");
        }
        return user;
    }

    @Override
    public void delete(long id) {
        User user = readUser(id);
        if (null == user) {
            throw new EntityNotFoundException("User not found");
        }
        entityManager.remove(user);
        entityManager.flush();
    }
}
