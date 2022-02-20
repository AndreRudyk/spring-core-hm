package com.spring.hm.springcorehm.dao.impl;

import com.spring.hm.springcorehm.model.Event;
import com.spring.hm.springcorehm.model.impl.EventImpl;
import com.spring.hm.springcorehm.repository.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventDaoImplTest {

    @InjectMocks
    private EventDaoImpl eventDao;

    @Mock
    private Repository repository;

    private final Map<Long, Event> events = new HashMap<>();

    @BeforeEach
    void setUp() {
        EventImpl event1 = new EventImpl(1L, "NBA game", new Date(2022-7-1));
        EventImpl event2 = new EventImpl(2L, "Boxing event", new Date(2022-7-1));
        EventImpl event3 = new EventImpl(3L, "MMF fight", new Date(2022-7-2));
        events.put(1L, event1);
        events.put(2L, event2);
        events.put(3L, event3);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getAllEvents_returnsEventList() {
        when(repository.getEvents()).thenReturn(events);
        Collection<Event> result = eventDao.getAllEvents();
        assertEquals(3, result.size());
        assertFalse(result.isEmpty());
    }

    @Test
    void getEventById_returnsEventTest() {
        when(repository.getEvents()).thenReturn(events);
        Event result = eventDao.getEventById(3L);
        assertEquals("MMF fight", result.getTitle());
    }

    @Test
    void addEvent_shouldAddNewEventTest() {
        Event event4 = new EventImpl(4L, "NBA All Stars Game", new Date(2022-7 -3));
        when(repository.getEvents()).thenReturn(events);
        Event result = eventDao.addEvent(event4);
        assertEquals("NBA All Stars Game", result.getTitle());
        assertEquals(new Date(2022-7-3), result.getDate());
    }

    @Test
    void updateEvent_shouldSucceedTest() {
        when(repository.getEvents()).thenReturn(events);
        Event event4 = new EventImpl(1, "NBA All Stars Game", new Date(2022-7 -3));
        final Event result = eventDao.updateEvent(event4);
        assertEquals("NBA All Stars Game", result.getTitle());
        assertEquals(new Date(2022-7-3), result.getDate());
    }

    @Test
    void updateEventWhenEventIdIsInvalid_shouldReturnNull() {
        when(repository.getEvents()).thenReturn(events);
        Event event4 = new EventImpl(5L, "NBA All Stars Game", new Date(2022-7 -3));
        final Event result = eventDao.updateEvent(event4);
        assertNull(result);
    }

    @Test
    void deleteEvent_returnsTrueTest() {
        when(repository.getEvents()).thenReturn(events);
        final boolean result = eventDao.deleteEvent(1L);
        assertTrue(result);
    }

    @Test
    void deleteEventWhenEventIdIsInvalid_returnsFalseTest() {
        when(repository.getEvents()).thenReturn(events);
        final boolean result = eventDao.deleteEvent(5L);
        assertFalse(result);
    }
}
