package com.spring.hm.springcorehm.dao;

import com.spring.hm.springcorehm.model.User;

import java.util.Collection;

public interface UserDao {

    User getUserById(long userId);

    Collection<User> getAllUsers();

    User createUser(User user);

    User updateUser(User user);

    boolean deleteUser(long userId);

    public Long getMaxId();
}
