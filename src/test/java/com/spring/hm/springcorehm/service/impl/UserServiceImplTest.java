package com.spring.hm.springcorehm.service.impl;

import com.spring.hm.springcorehm.dao.impl.UserDaoImpl;
import com.spring.hm.springcorehm.exception.EventNotFoundException;
import com.spring.hm.springcorehm.exception.UserNotFoundException;
import com.spring.hm.springcorehm.model.User;
import com.spring.hm.springcorehm.model.impl.UserImpl;
import com.spring.hm.springcorehm.validator.ValidatorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDaoImpl userDao;

    @Mock
    private ValidatorService validatorService;

    private final Map<Long, User> users = new HashMap<>();

    private static final int LIST_FIRST_INDEX = 0;

    private static final int LIST_SECOND_INDEX = 1;

    @BeforeEach
    void setUp() {
        UserImpl user1 = new UserImpl(1L, "Jim", "jim@gmail.com");
        UserImpl user2 = new UserImpl(2L, "William", "william.jackson@gmail.com");
        UserImpl user3 = new UserImpl(3L, "William", "william@gmail.com");
        users.put(1L, user1);
        users.put(2L, user2);
        users.put(3L, user3);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(userDao);
    }

    @Test
    void getUserById_returnsUserTest() {
        final long userId = 1L;
        final User user = new UserImpl();
        user.setEmail("petro.kovalchuk@gmail.com");
        user.setName("Petro Kovalchuk");
        when(userDao.getUserById(userId)).thenReturn(user);
        final User result = userService.getUserById(userId);
        assertEquals("petro.kovalchuk@gmail.com", result.getEmail());
        assertEquals("Petro Kovalchuk", result.getName());
    }

    @Test
    void getUserByEmail_returnsUserTest() {
        when(userDao.getAllUsers()).thenReturn(users.values());
        final User result = userService.getUserByEmail("william@gmail.com");
        assertEquals("william@gmail.com", result.getEmail());
        assertEquals("William", result.getName());
    }

    @Test
    void getUsersByName_returnsUserListWithSameNameTest() {
        final String userName = "William";
        when(userDao.getAllUsers()).thenReturn(users.values());
        final List<User> result = userService.getUsersByName(userName, 2, 2);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals(userName, result.get(LIST_FIRST_INDEX).getName());
        assertEquals(userName, result.get(LIST_SECOND_INDEX).getName());
    }

    @Test
    void createUser_returnsNewlyCreatedUserTest() {
        final User user = new UserImpl();
        user.setEmail("petro.kovalchuk@gmail.com");
        user.setName("Petro Kovalchuk");
        when(userDao.createUser(user)).thenReturn(user);
        final User result = userService.createUser(user);
        assertEquals("petro.kovalchuk@gmail.com", result.getEmail());
        assertEquals("Petro Kovalchuk", result.getName());
    }

    @Test
    void updateUserWhenIdExists_returnsUpdatedUserTest() {
        final User user = new UserImpl();
        user.setId(1L);
        user.setEmail("petro.kovalchuk@gmail.com");
        user.setName("Petro Kovalchuk");
        when(userDao.updateUser(user)).thenReturn(user);
        doNothing().when(validatorService).validateUser(user.getId());
        final User result = userService.updateUser(user);
        assertEquals("petro.kovalchuk@gmail.com", result.getEmail());
        assertEquals("Petro Kovalchuk", result.getName());
        assertEquals(1L, result.getId());
    }

    @Test
    void updateUserWhenIdNotExists_throwsExceptionTest() {
        final User user = new UserImpl();
        user.setId(1L);
        user.setEmail("petro.kovalchuk@gmail.com");
        user.setName("Petro Kovalchuk");
        doThrow(UserNotFoundException.class).when(validatorService).validateUser(user.getId());
        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser(user);
        });
    }

    @Test
    void deleteUserWhenIdExists_returnsTrueTest() {
        final long userId = 1L;
        when(userDao.deleteUser(userId)).thenReturn(true);
        final boolean result = userService.deleteUser(userId);
        assertTrue(result);
    }

    @Test
    void deleteUserWhenIdNotExist_throwsExceptionTest() {
        final long userId = 1L;
        doThrow(UserNotFoundException.class).when(validatorService).validateUser(userId);
        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(userId);
        });
    }
}
