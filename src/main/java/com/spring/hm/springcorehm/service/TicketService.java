package com.spring.hm.springcorehm.service;

import com.spring.hm.springcorehm.model.Event;
import com.spring.hm.springcorehm.model.Ticket;
import com.spring.hm.springcorehm.model.User;
import java.util.List;

public interface TicketService {

    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);

    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

    boolean cancelTicket(long ticketId);
}
