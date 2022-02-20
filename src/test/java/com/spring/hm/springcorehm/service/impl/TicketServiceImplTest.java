package com.spring.hm.springcorehm.service.impl;

import com.spring.hm.springcorehm.dao.TicketDao;
import com.spring.hm.springcorehm.exception.EventNotFoundException;
import com.spring.hm.springcorehm.exception.TicketNotFoundException;
import com.spring.hm.springcorehm.model.Event;
import com.spring.hm.springcorehm.model.Ticket;
import com.spring.hm.springcorehm.model.User;
import com.spring.hm.springcorehm.model.impl.EventImpl;
import com.spring.hm.springcorehm.model.impl.TicketImpl;
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
class TicketServiceImplTest {

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private TicketDao ticketDao;

    @Mock
    private ValidatorService validatorService;

    private final Map<Long, Ticket> tickets = new HashMap<>();

    @BeforeEach
    void setUp() {
        TicketImpl ticket1 = new TicketImpl(1L, 1L, 1L, Ticket.Category.PREMIUM, 1);
        TicketImpl ticket2 = new TicketImpl(2L, 2L, 1L, Ticket.Category.BAR, 2);
        TicketImpl ticket3 = new TicketImpl(3L, 2L, 3L, Ticket.Category.PREMIUM, 3);
        tickets.put(1L, ticket1);
        tickets.put(2L, ticket2);
        tickets.put(3L, ticket3);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(ticketDao, validatorService);
    }

    @Test
    void bookTicket() {
    }

    @Test
    void getBookedTickets_returnsBookedTicketsListWithSameUserIdTest() {
        final User user = new UserImpl();
        user.setId(1L);
        when(ticketDao.getAllTickets()).thenReturn(tickets.values());
        final List<Ticket> result = ticketService.getBookedTickets(user, 2, 2);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    void getBookedTickets_returnsBookedTicketsListWithSameEventIdTest() {
        final Event event = new EventImpl();
        event.setId(2L);
        when(ticketDao.getAllTickets()).thenReturn(tickets.values());
        final List<Ticket> result = ticketService.getBookedTickets(event, 2, 2);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    void cancelTicketWhenTicketIdExists_returnsTrueTest() {
        final long ticketId = 1L;
        doNothing().when(validatorService).validateTicket(ticketId);
        when(ticketDao.deleteTicket(ticketId)).thenReturn(true);
        final boolean result = ticketService.cancelTicket(ticketId);
        assertTrue(result);
    }

    @Test
    void cancelTicketWhenTicketIdNotExist_throwsExceptionTest() {
        final long ticketId = 1L;
        doThrow(TicketNotFoundException.class).when(validatorService).validateTicket(ticketId);
        assertThrows(TicketNotFoundException.class, () -> {
            ticketService.cancelTicket(ticketId);
        });
    }
}
