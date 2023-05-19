package com.datalink.service;

import com.datalink.entity.User;

public interface UserService {
    User findByUsername(String username);
    void saveUser(User user);
}
