package com.example.app.service;

import com.example.app.model.User;
import com.example.app.repo.InMemoryUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final InMemoryUserRepository repo = new InMemoryUserRepository();

    public boolean signup(String username, String password, String email) {
        if (repo.exists(username)) return false;
        repo.save(new User(username, password, email));
        return true;
    }

    public boolean login(String username, String password) {
        var u = repo.findByUsername(username);
        return u != null && u.getPassword().equals(password);
    }
}