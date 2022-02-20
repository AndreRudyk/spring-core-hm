package com.spring.hm.springcorehm.dao;

import com.spring.hm.springcorehm.model.Event;
import java.util.Collection;

public interface EventDao {

    Collection<Event> getAllEvents();

    Event getEventById(long eventId);

    Event addEvent(Event event);

    Event updateEvent(Event event);

    boolean deleteEvent(long eventId);

    public Long getMaxId();
}
