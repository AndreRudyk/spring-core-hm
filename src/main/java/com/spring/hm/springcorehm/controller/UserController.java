package com.spring.hm.springcorehm.controller;

import com.spring.hm.springcorehm.facade.BookingFacade;
import com.spring.hm.springcorehm.model.User;
import com.spring.hm.springcorehm.model.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private BookingFacade bookingFacade;

    @Autowired
    public void setBookingFacade(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") final long id) {
        return bookingFacade.getUserById(id);
    }

    @ResponseStatus(OK)
    @GetMapping(params = {"email"})
    public User getUserByEmail(@RequestParam("email") final String email) {
        return bookingFacade.getUserByEmail(email);
    }

    @ResponseStatus(OK)
    @GetMapping(params = {"name", "size", "number"})
    public List<User> getUsersByName(@RequestParam("name") final String name,
                                       @RequestParam("size") final int size,
                                       @RequestParam("number") final int number) {
        return bookingFacade.getUsersByName(name, size, number);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public User createUser(@RequestBody final UserImpl user) {
        return bookingFacade.createUser(user);
    }

    @ResponseStatus(ACCEPTED)
    @PutMapping
    public User updateUser(@RequestBody final UserImpl user) {
        return bookingFacade.updateUser(user);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") final long id) {
        return bookingFacade.deleteUser(id);
    }
}
