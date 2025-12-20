package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    User save(User user);

    boolean exists(String email);

    User findByEmail(String email);
}
