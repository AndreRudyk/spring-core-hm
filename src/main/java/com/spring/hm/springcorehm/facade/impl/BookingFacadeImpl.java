package com.spring.hm.springcorehm.facade.impl;

import com.spring.hm.springcorehm.facade.BookingFacade;
import com.spring.hm.springcorehm.model.Event;
import com.spring.hm.springcorehm.model.Ticket;
import com.spring.hm.springcorehm.model.User;
import com.spring.hm.springcorehm.service.EventService;
import com.spring.hm.springcorehm.service.TicketService;
import com.spring.hm.springcorehm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class BookingFacadeImpl implements BookingFacade {

    private final EventService eventService;

    private final TicketService ticketService;

    private final UserService userService;

    @Autowired
    public BookingFacadeImpl(EventService eventService, TicketService ticketService, UserService userService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingFacadeImpl.class);

    @Override
    public Event getEventById(final long eventId) {
        LOGGER.info("Calling getEventById method has been called with event id {}", eventId);
        return eventService.getEventById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(final String title,
                                        final int pageSize,
                                        final int pageNum) {
        LOGGER.info("Calling getEventsByTitle with string title {}", title);
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(final Date day,
                                       final int pageSize,
                                       final int pageNum) {
        LOGGER.info("Calling getEventsForDay with date {}", day);
        return eventService.getEventsForDay(day, pageSize, pageNum);
    }

    @Override
    public Event createEvent(final Event event) {
        LOGGER.info("Calling createEvent method with event {}", event);
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(final Event event) {
        LOGGER.info("Calling updateEvent with event {}", event);
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(final long eventId) {
        LOGGER.info("Calling deleteEvent method with event id {}", eventId);
        return eventService.deleteEvent(eventId);
    }

    @Override
    public User getUserById(final long userId) {
        LOGGER.info("Calling getUserById method with user id {}", userId);
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(final String email) {
        LOGGER.info("Calling getUserByEmail method with email {}", email);
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(final String name,
                                     final int pageSize,
                                     final int pageNum) {
        LOGGER.info("Calling getUsersByName with name {}", name);
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public User createUser(final User user) {
        LOGGER.info("Calling createUser method with user {}", user);
        return userService.createUser(user);
    }

    @Override
    public User updateUser(final User user) {
        LOGGER.info("Calling updateUser method with user {}", user);
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(final long userId) {
        LOGGER.info("Calling deleteUser user id {}", userId);
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(final long userId,
                             final long eventId,
                             final int place,
                             final Ticket.Category category) {
        LOGGER.info("Calling bookTicket method");
        return ticketService.bookTicket(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(final User user,
                                         final int pageSize,
                                         final int pageNum) {
        LOGGER.info("Calling getBookedTickets method with user {}", user);
        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(final Event event,
                                         final int pageSize,
                                         final int pageNum) {
        LOGGER.info("Calling getBookedTickets with event {}", event);
        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(final long ticketId) {
        LOGGER.info("Calling cancelTicket with ticked id {}", ticketId);
        return ticketService.cancelTicket(ticketId);
    }
}
