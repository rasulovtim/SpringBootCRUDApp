package com.example.SpringBootCRUDApp.service;


import com.example.SpringBootCRUDApp.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void add(User user);

    void update(User user);

    User readUser(long id);

    void delete(long id);

}
