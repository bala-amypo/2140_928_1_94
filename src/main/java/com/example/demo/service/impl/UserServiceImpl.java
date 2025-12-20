package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        User existing = getUserById(id);
        existing.setFullName(user.getFullName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        existing.setRole(user.getRole());
        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        User existing = getUserById(id);
        userRepository.delete(existing);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
