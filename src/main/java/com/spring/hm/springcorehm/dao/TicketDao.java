package com.spring.hm.springcorehm.dao;

import com.spring.hm.springcorehm.model.Ticket;
import java.util.Collection;


public interface TicketDao {

    Ticket createTicket(long userId, long eventId, int place, Ticket.Category category);

    Collection<Ticket> getAllTickets();

    boolean deleteTicket(long ticketId);

    Ticket getTicketById(final long id);

    public Long getMaxId();
}
