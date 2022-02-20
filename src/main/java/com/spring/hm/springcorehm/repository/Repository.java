package com.spring.hm.springcorehm.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.hm.springcorehm.model.Event;
import com.spring.hm.springcorehm.model.Ticket;
import com.spring.hm.springcorehm.model.User;
import com.spring.hm.springcorehm.model.impl.EventImpl;
import com.spring.hm.springcorehm.model.impl.UserImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Repository {

    @Value("${repository.users}")
    private String usersFilePath;

    @Value("${repository.events}")
    private String eventFilePath;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Map<Long, Event> events;
    private Map<Long, Ticket> tickets;
    private Map<Long, User> users;

    public Map<Long, Event> getEvents() {
        return events;
    }

    public Map<Long, Ticket> getTickets() {
        return tickets;
    }

    public Map<Long, User> getUsers() {
        return users;
    }

    @PostConstruct
    private void initializeRepository() throws IOException {
        List<UserImpl> userList = objectMapper.readValue(getJsonString(usersFilePath), new TypeReference<List<UserImpl>>() {
        });
        List<EventImpl> eventList = objectMapper.readValue(getJsonString(eventFilePath), new TypeReference<List<EventImpl>>() {
        });
        users = userList.stream().collect(Collectors.toMap(UserImpl::getId, Function.identity()));
        events = eventList.stream().collect(Collectors.toMap(EventImpl::getId, Function.identity()));
        tickets = new HashMap<>();
    }

    private String getJsonString(final String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
