package com.spring.hm.springcorehm.controller;

import com.spring.hm.springcorehm.facade.BookingFacade;
import com.spring.hm.springcorehm.model.Ticket;
import com.spring.hm.springcorehm.model.impl.EventImpl;
import com.spring.hm.springcorehm.model.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private BookingFacade bookingFacade;

    @Autowired
    public void setBookingFacade(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public Ticket bookTicket(@RequestParam("userId") final long userId,
                             @RequestParam("eventId") final long eventId,
                             @RequestParam("place") final int place,
                             @RequestParam("category") final String category) {
        return bookingFacade.bookTicket(userId, eventId, place, Ticket.Category.valueOf(category));
    }

    @ResponseStatus(OK)
    @GetMapping(params = {"size", "number"})
    public List<Ticket> getBookedTickets(@RequestBody final UserImpl user,
                                         @RequestParam("size") final int size,
                                         @RequestParam("number") final int number) {
        return bookingFacade.getBookedTickets(user, size, number);
    }

    @ResponseStatus(OK)
    @GetMapping("/get_by_event")
    public List<Ticket> getBookedTickets(@RequestBody final EventImpl event,
                                         @RequestParam("size") final int size,
                                         @RequestParam("number") final int number) {
        return bookingFacade.getBookedTickets(event, size, number);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public boolean cancelTicket(@PathVariable("id") final long id) {
        return bookingFacade.cancelTicket(id);
    }
}
