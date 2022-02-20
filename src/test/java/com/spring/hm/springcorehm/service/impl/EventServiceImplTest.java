package com.spring.hm.springcorehm.service.impl;

import com.spring.hm.springcorehm.dao.EventDao;
import com.spring.hm.springcorehm.exception.EventNotFoundException;
import com.spring.hm.springcorehm.model.Event;
import com.spring.hm.springcorehm.model.impl.EventImpl;
import com.spring.hm.springcorehm.validator.ValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventDao eventDao;

    @Mock
    private ValidatorService validatorService;

    private static final int LIST_FIRST_INDEX = 0;

    private static final int LIST_SECOND_INDEX = 1;

    @Test
    void createEvent_returnsNewlyCreatedEventTest() {
        final EventImpl event = new EventImpl();
        event.setTitle("Mayweather vs McGregor");
        event.setDate(new Date(2017-8-26));
        when(eventDao.addEvent(event)).thenReturn(event);
        final Event result = eventService.createEvent(event);
        assertEquals("Mayweather vs McGregor", result.getTitle());
    }

    @Test
    void getEventById_returnsEventTest() {
        final long eventId = 1L;
        final EventImpl event = new EventImpl();
        event.setTitle("Mayweather vs McGregor");
        event.setDate(new Date(2017-8-26));
        when(eventDao.getEventById(eventId)).thenReturn(event);
        final Event result = eventService.getEventById(eventId);
        assertEquals("Mayweather vs McGregor", result.getTitle());
        assertEquals(new Date(2017-8-26), result.getDate());
    }

    @Test
    void getEventsByTitle_returnsEventListByTitleTest() {
        final Map<Long, Event> events = new HashMap<>();
        final EventImpl event1 = new EventImpl(1L, "NBA game", new Date(2022-7-1));
        final EventImpl event2 = new EventImpl(2L, "Boxing event", new Date(2022-7-1));
        final EventImpl event3 = new EventImpl(3L, "NBA game", new Date(2022-7-2));
        events.put(1L, event1);
        events.put(2L, event2);
        events.put(3L, event3);
        when(eventDao.getAllEvents()).thenReturn(events.values());
        final List<Event> result = eventService.getEventsByTitle("NBA game", 1, 1);
        assertEquals("NBA game", result.get(LIST_FIRST_INDEX).getTitle());
    }

    @Test
    void getEventsForDay_returnsEventListByDateTest() {
        final Map<Long, Event> events = new HashMap<>();
        final EventImpl event1 = new EventImpl(1L, "NBA game", new Date(2022-7-1));
        final EventImpl event2 = new EventImpl(2L, "Boxing event", new Date(2022-7-1));
        final EventImpl event3 = new EventImpl(3L, "NBA game", new Date(2022-7-2));
        events.put(1L, event1);
        events.put(2L, event2);
        events.put(3L, event3);
        when(eventDao.getAllEvents()).thenReturn(events.values());
        final List<Event> result = eventService.getEventsForDay(new Date(2022-7-1), 2, 2);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(new Date(2022-7-1), result.get(LIST_FIRST_INDEX).getDate());
        assertEquals(new Date(2022-7-1), result.get(LIST_SECOND_INDEX).getDate());
    }

    @Test
    void updateEventWhenEventIdExists_returnsUpdatedEventTest() {
        final EventImpl event = new EventImpl();
        event.setTitle("Mayweather vs McGregor");
        event.setDate(new Date(2017-8-26));
        doNothing().when(validatorService).validateEvent(event.getId());
        when(eventService.updateEvent(event)).thenReturn(event);
        final Event result = eventService.updateEvent(event);
        assertEquals("Mayweather vs McGregor", result.getTitle());
        assertEquals(new Date(2017-8-26), result.getDate());
    }

    @Test
    void updateEventWhenEventIdNotExist_throwsExceptionTest() {
        final EventImpl event = new EventImpl();
        event.setTitle("Mayweather vs McGregor");
        event.setDate(new Date(2017-8-26));
        doThrow(EventNotFoundException.class).when(validatorService).validateEvent(event.getId());
        assertThrows(EventNotFoundException.class, () -> {
            eventService.updateEvent(event);
        });
    }

    @Test
    void deleteEvent_returnsTrueTest() {
        final long eventId = 1L;
        doNothing().when(validatorService).validateEvent(eventId);
        when(eventService.deleteEvent(eventId)).thenReturn(true);
        final boolean result = eventService.deleteEvent(eventId);
        assertTrue(result);
    }

    @Test
    void deleteEvent_throwsExceptionTest() {
        final long eventId = 1L;
        doThrow(EventNotFoundException.class).when(validatorService).validateEvent(eventId);
        assertThrows(EventNotFoundException.class, () -> {
            eventService.deleteEvent(eventId);
        });
    }
}
