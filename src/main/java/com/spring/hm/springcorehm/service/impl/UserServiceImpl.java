package com.spring.hm.springcorehm.service.impl;

import com.spring.hm.springcorehm.dao.UserDao;
import com.spring.hm.springcorehm.model.User;
import com.spring.hm.springcorehm.service.UserService;
import com.spring.hm.springcorehm.validator.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private ValidatorService validatorService;

    @Autowired
    public void setValidatorService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getUserById(final long userId) {
        LOGGER.info("getUserById method has been called with user id {}", userId);
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByEmail(final String email) {
        LOGGER.info("getUserByEmail method is being called with email {}", email);
        return userDao.getAllUsers().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getUsersByName(final String name,
                                     final int pageSize,
                                     final int pageNum) {
        LOGGER.info("getUsersByName method has been called");
        return userDao.getAllUsers().stream()
                .filter(user -> user.getName().contains(name))
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(final User user) {
            LOGGER.info("Calling createUser method with user {}", user);
            return userDao.createUser(user);
    }

    @Override
    public User updateUser(final User user) {
        LOGGER.info("updateUser method has been called with user {}", user);
        validatorService.validateUser(user.getId());
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(final long userId) {
        LOGGER.info("deleteUser method has been called with user id {}", userId);
        validatorService.validateUser(userId);
        return userDao.deleteUser(userId);
    }
}
