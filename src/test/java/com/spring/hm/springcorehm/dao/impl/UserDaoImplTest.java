package com.spring.hm.springcorehm.dao.impl;

import com.spring.hm.springcorehm.model.User;
import com.spring.hm.springcorehm.model.impl.UserImpl;
import com.spring.hm.springcorehm.repository.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDaoImplTest {

    @InjectMocks
    private UserDaoImpl userDao;

    @Mock
    private Repository repository;

    private final Map<Long, User> users = new HashMap<>();

    @BeforeEach
    void setUp() {
        UserImpl user1 = new UserImpl(1L, "Jim", "jim@gmail.com");
        UserImpl user2 = new UserImpl(2L, "Collin", "collin@gmail.com");
        UserImpl user3 = new UserImpl(3L, "William", "william@gmail.com");
        users.put(1L, user1);
        users.put(2L, user2);
        users.put(3L, user3);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getAllUsers_shouldReturnFullListTest() {
        when(repository.getUsers()).thenReturn(users);
        Collection<User> result = userDao.getAllUsers();
        assertEquals(3, result.size());
        assertFalse(result.isEmpty());
    }

    @Test
    void getUserById_shouldReturnUserTest() {
        when(repository.getUsers()).thenReturn(users);
        final User result = userDao.getUserById(2L);
        assertEquals("Collin", result.getName());
        assertEquals("collin@gmail.com", result.getEmail());
    }

    @Test
    void addUser_shouldSucceedAtAddingUserTest() {
        final User user4 = new UserImpl("Robert", "rob@gmail.com");
        when(repository.getUsers()).thenReturn(users);
        final User user = userDao.createUser(user4);
        assertEquals(4L, user.getId());
        assertEquals("Robert", user.getName());
    }

    @Test
    void updateUser_shouldUpdateUserTest() {
        final User userUpdate = new UserImpl(1L, "Jimmy", "jim@gmail.com");
        when(repository.getUsers()).thenReturn(users);
        final User result = userDao.updateUser(userUpdate);
        assertEquals("Jimmy", result.getName());
        assertEquals("jim@gmail.com", result.getEmail());
    }

    @Test
    void updateUser_shouldReturnNull() {
        final User userUpdate = new UserImpl(4L, "Jimmy", "jim@gmail.com");
        when(repository.getUsers()).thenReturn(users);
        final User result = userDao.updateUser(userUpdate);
        assertNull(result);
    }

    @Test
    void deleteUser_shouldSucceedAtDeletingTest() {
        when(repository.getUsers()).thenReturn(users);
        boolean result = userDao.deleteUser(1L);
        assertTrue(result);
    }

    @Test
    void deleteUser_shouldFailAndReturnFalseTest() {
        when(repository.getUsers()).thenReturn(users);
        boolean result = userDao.deleteUser(4L);
        assertFalse(result);
    }
}
