package com.example.app.repo;

import com.example.app.model.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public boolean exists(String username) { return users.containsKey(username); }
    public void save(User user) { users.put(user.getUsername(), user); }
    public User findByUsername(String username) { return users.get(username); }
}