package com.example.SpringBootCRUDApp.service;

import com.example.SpringBootCRUDApp.dao.UserDao;
import com.example.SpringBootCRUDApp.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User readUser(long id) {
        try {
            return userDao.readUser(id);
        } catch (EntityNotFoundException ex) {
            throw ex;
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        try {
            userDao.delete(id);
        } catch (EntityNotFoundException ex) {
            throw ex;
        }
    }

}
