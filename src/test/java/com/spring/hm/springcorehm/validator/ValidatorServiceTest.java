package com.spring.hm.springcorehm.validator;

import com.spring.hm.springcorehm.dao.EventDao;
import com.spring.hm.springcorehm.dao.TicketDao;
import com.spring.hm.springcorehm.dao.UserDao;
import com.spring.hm.springcorehm.exception.EventNotFoundException;
import com.spring.hm.springcorehm.exception.PlaceIsBookedException;
import com.spring.hm.springcorehm.exception.TicketNotFoundException;
import com.spring.hm.springcorehm.exception.UserNotFoundException;
import com.spring.hm.springcorehm.model.Ticket;
import com.spring.hm.springcorehm.model.impl.EventImpl;
import com.spring.hm.springcorehm.model.impl.TicketImpl;
import com.spring.hm.springcorehm.model.impl.UserImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidatorServiceTest {

    @InjectMocks
    private ValidatorService validatorService;

    @Mock
    private TicketDao ticketDao;

    @Mock
    private UserDao userDao;

    @Mock
    private EventDao eventDao;

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(ticketDao, userDao, eventDao);
    }

    @Test
    void validateUser_shouldThrowExceptionTest() {
        final long userId = 1L;
        final String expectedMessage = "User is not found";
        when(userDao.getUserById(userId)).thenReturn(null);
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            validatorService.validateUser(userId);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void validateUser_shouldValidateSuccessfullyTest() {
        final long userId = 1L;
        final UserImpl user = new UserImpl();
        user.setId(userId);
        when(userDao.getUserById(userId)).thenReturn(user);
        validatorService.validateUser(userId);
        verify(userDao).getUserById(userId);
    }

    @Test
    void validateTicket_shouldThrowExceptionTest() {
        final long ticketId = 1L;
        final String expectedMessage = "Ticket not found";
        when(ticketDao.getTicketById(ticketId)).thenReturn(null);
        Exception exception = assertThrows(TicketNotFoundException.class, () -> {
            validatorService.validateTicket(ticketId);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void validateTicket_shouldValidateSuccessfullyTest() {
        final long ticketId = 1L;
        final TicketImpl ticket = new TicketImpl();
        ticket.setId(ticketId);
        when(ticketDao.getTicketById(ticketId)).thenReturn(ticket);
        validatorService.validateTicket(ticketId);
        verify(ticketDao).getTicketById(ticketId);
    }

    @Test
    void validatePlace_shouldThrowExceptionTest() {
        final int place = 1;
        final String expectedMessage = "The place is already booked";
        final Map<Long, Ticket> tickets = new HashMap<>();
        final TicketImpl ticket1 = new TicketImpl(1L, 1L, 1L, Ticket.Category.PREMIUM, 1);
        final TicketImpl ticket2 = new TicketImpl(2L, 2L, 1L, Ticket.Category.BAR, 2);
        final TicketImpl ticket3 = new TicketImpl(3L, 2L, 3L, Ticket.Category.PREMIUM, 3);
        tickets.put(1L, ticket1);
        tickets.put(2L, ticket2);
        tickets.put(3L, ticket3);
        when(ticketDao.getAllTickets()).thenReturn(tickets.values());
        Exception exception = assertThrows(PlaceIsBookedException.class, () -> {
            validatorService.validatePlace(place);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void validatePlace_shouldValidateSuccessfullyTest() {
        final int place = 12;
        final Map<Long, Ticket> tickets = new HashMap<>();
        final TicketImpl ticket1 = new TicketImpl(1L, 1L, 1L, Ticket.Category.PREMIUM, 1);
        final TicketImpl ticket2 = new TicketImpl(2L, 2L, 1L, Ticket.Category.BAR, 2);
        final TicketImpl ticket3 = new TicketImpl(3L, 2L, 3L, Ticket.Category.PREMIUM, 3);
        tickets.put(1L, ticket1);
        tickets.put(2L, ticket2);
        tickets.put(3L, ticket3);
        when(ticketDao.getAllTickets()).thenReturn(tickets.values());
        validatorService.validatePlace(place);
        verify(ticketDao).getAllTickets();
    }

    @Test
    void validateEvent_shouldThrowExceptionTest() {
        final long eventId = 1L;
        final String expectedMessage = "Event is not found";
        when(eventDao.getEventById(eventId)).thenReturn(null);
        Exception exception = assertThrows(EventNotFoundException.class, () -> {
            validatorService.validateEvent(eventId);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void validateEvent_shouldValidateSuccessfullyTest() {
        final long eventId = 1L;
        final EventImpl event = new EventImpl();
        event.setId(eventId);
        when(eventDao.getEventById(eventId)).thenReturn(event);
        validatorService.validateEvent(eventId);
        verify(eventDao).getEventById(eventId);
    }
}
