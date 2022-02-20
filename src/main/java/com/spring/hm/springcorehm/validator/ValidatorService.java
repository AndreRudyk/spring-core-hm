package com.spring.hm.springcorehm.validator;

import com.spring.hm.springcorehm.dao.EventDao;
import com.spring.hm.springcorehm.dao.TicketDao;
import com.spring.hm.springcorehm.dao.UserDao;
import com.spring.hm.springcorehm.exception.EventNotFoundException;
import com.spring.hm.springcorehm.exception.PlaceIsBookedException;
import com.spring.hm.springcorehm.exception.TicketNotFoundException;
import com.spring.hm.springcorehm.exception.UserNotFoundException;
import com.spring.hm.springcorehm.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Validator class used to validate business model ids.
 */
@Component
public class ValidatorService {

    private TicketDao ticketDao;

    private UserDao userDao;

    private EventDao eventDao;

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    /**
     * Validates user id.
     * @param userId user id
     */
    public void validateUser(final long userId){
        validate(userDao.getUserById(userId), Objects::isNull, UserNotFoundException::new);
    }

    /**
     * Validates ticket id.
     * @param ticketId ticket id
     */
    public void validateTicket(final long ticketId){
        validate(ticketDao.getTicketById(ticketId), Objects::isNull, TicketNotFoundException::new);
    }

    /**
     * Validates that place is not booked.
     * @param placeId place id
     */
    public void validatePlace(final int placeId) {
        Ticket ticketToValidate = ticketDao.getAllTickets().stream()
                .filter(ticket -> ticket.getPlace() == placeId)
                .findFirst()
                .orElse(null);
        validate(ticketToValidate, Objects::nonNull, PlaceIsBookedException::new);
    }

    /**
     * Validates event id.
     * @param eventId event id
     */
    public void validateEvent(final long eventId){
        validate(eventDao.getEventById(eventId), Objects::isNull, EventNotFoundException::new);
    }

    /**
     *Generic validation method.
     * @param <T> businessModel model
     * @param invalidValueVerifier predicate function
     * @param exceptionSupplier supplier function with corresponding exception
     */
    private static <T> void validate(final T businessModel,
                                    final Predicate<T> invalidValueVerifier,
                                    final Supplier<RuntimeException> exceptionSupplier) {
        if (invalidValueVerifier.test(businessModel)) {
            throw exceptionSupplier.get();
        }
    }
}

