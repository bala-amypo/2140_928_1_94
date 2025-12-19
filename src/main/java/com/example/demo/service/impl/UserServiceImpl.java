package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User getById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElse(null); // simple CRUD, no exceptions
    }

    @Override
    public User update(Long id, User user) {
        Optional<User> existing = repository.findById(id);
        if (existing.isPresent()) {
            User u = existing.get();
            u.setName(user.getName()); // copy fields as needed
            u.setEmail(user.getEmail());
            return repository.save(u);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
