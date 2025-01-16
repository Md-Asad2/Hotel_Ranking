package com.maspez.user_service.services;

import java.util.List;

import com.maspez.user_service.entities.User;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    User updateUser(User user);

    void deleteUser(String userId);
}
