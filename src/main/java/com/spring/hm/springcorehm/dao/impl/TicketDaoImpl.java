package com.spring.hm.springcorehm.dao.impl;

import com.spring.hm.springcorehm.dao.TicketDao;
import com.spring.hm.springcorehm.model.Ticket;
import com.spring.hm.springcorehm.model.impl.TicketImpl;
import com.spring.hm.springcorehm.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Component
public class TicketDaoImpl implements TicketDao {

    private Repository repository;

    @Autowired
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Ticket createTicket(final long userId,
                               final long eventId,
                               final int place,
                               final Ticket.Category category) {
        long ticketId = getMaxId() + 1;
        return repository.getTickets()
                .put(ticketId, new TicketImpl(ticketId, eventId, userId, category, place));
    }

    @Override
    public Collection<Ticket> getAllTickets() {
        return repository.getTickets().values();
    }

    @Override
    public boolean deleteTicket(final long ticketId) {
        return Objects.nonNull(repository.getTickets()
                .remove(ticketId));
    }

    @Override
    public Ticket getTicketById(final long ticketId) {
        return getAllTickets().stream()
                .filter(ticket -> ticket.getId() == ticketId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Long getMaxId() {
        return repository.getTickets().keySet().isEmpty()
                ? 0L
                : Collections.max(repository.getTickets().keySet());
    }
}
