package com.spring.hm.springcorehm.controller;

import com.spring.hm.springcorehm.facade.BookingFacade;
import com.spring.hm.springcorehm.model.Event;
import com.spring.hm.springcorehm.model.impl.EventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private BookingFacade bookingFacade;

    @Autowired
    public void setBookingFacade(final BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }
    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable("id") final long id) {
        return bookingFacade.getEventById(id);
    }

    @ResponseStatus(OK)
    @GetMapping(params = {"title", "size", "number"})
    public List<Event> getEventsByTitle(@RequestParam("title") final String title,
                                        @RequestParam("size") final int size,
                                        @RequestParam("number") final int number) {
        return bookingFacade.getEventsByTitle(title, size, number);
    }

    @ResponseStatus(OK)
    @GetMapping(params = {"date", "size", "number"})
    public List<Event> getEventsForDay(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                       @RequestParam("size") final int size,
                                       @RequestParam("number") final int number) {
        return bookingFacade.getEventsForDay(date, size, number);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public Event createEvent(@RequestBody final EventImpl event) {
        return bookingFacade.createEvent(event);
    }

    @ResponseStatus(ACCEPTED)
    @PutMapping
    public Event updateEvent(@RequestBody final EventImpl event) {
        return bookingFacade.updateEvent(event);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public boolean deleteEvent(@PathVariable("id") final long id) {
        return bookingFacade.deleteEvent(id);
    }
}
