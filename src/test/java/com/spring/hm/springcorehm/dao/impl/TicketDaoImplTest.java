package com.spring.hm.springcorehm.dao.impl;

import com.spring.hm.springcorehm.model.Ticket;
import com.spring.hm.springcorehm.model.impl.TicketImpl;
import com.spring.hm.springcorehm.repository.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketDaoImplTest {

    @InjectMocks
    private TicketDaoImpl ticketDao;

    @Mock
    private Repository repository;

    private final Map<Long, Ticket> tickets = new HashMap<>();

    @BeforeEach
    void setUp() {
        TicketImpl ticket1 = new TicketImpl(1L, 1L, 1L, Ticket.Category.PREMIUM, 1);
        TicketImpl ticket2 = new TicketImpl(2L, 2L, 2L, Ticket.Category.BAR, 2);
        TicketImpl ticket3 = new TicketImpl(3L, 3L, 3L, Ticket.Category.PREMIUM, 3);
        tickets.put(1L, ticket1);
        tickets.put(2L, ticket2);
        tickets.put(3L, ticket3);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getAllTickets_returnsTicketListTest() {
        when(repository.getTickets()).thenReturn(tickets);
        final Collection<Ticket> result = ticketDao.getAllTickets();
        assertEquals(3, result.size());
        assertFalse(result.isEmpty());
    }

    @Test
    void getTicketByIdWhenIdExists_returnsTicketTest() {
        when(repository.getTickets()).thenReturn(tickets);
        final Ticket result = ticketDao.getTicketById(1L);
        assertEquals(Ticket.Category.PREMIUM, result.getCategory());
    }

    @Test
    void getTicketByIdWhenIdNotExists_returnsNullTest() {
        when(repository.getTickets()).thenReturn(tickets);
        final Ticket result = ticketDao.getTicketById(5L);
        assertNull(result);
    }

    @Test
    void deleteTicketWhenTicketIdExists_returnsTrueTest() {
        when(repository.getTickets()).thenReturn(tickets);
        final boolean result = ticketDao.deleteTicket(1L);
        assertTrue(result);
    }

    @Test
    void deleteTicketWhenTicketIdNotExist_returnsTrueTest() {
        when(repository.getTickets()).thenReturn(tickets);
        final boolean result = ticketDao.deleteTicket(4L);
        assertFalse(result);
    }
}
