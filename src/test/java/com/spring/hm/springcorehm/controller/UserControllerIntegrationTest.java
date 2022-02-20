package com.spring.hm.springcorehm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.hm.springcorehm.facade.BookingFacade;
import com.spring.hm.springcorehm.model.impl.UserImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingFacade bookingFacade;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                                                        MediaType.APPLICATION_JSON.getSubtype(),
                                                                        StandardCharsets.UTF_8);

    @Test
    void getUserById() throws Exception {
        long id = 3L;
        this.mockMvc
                .perform(get("/users/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getUserByEmail() throws Exception {
        this.mockMvc
                .perform(get("/users/")
                .param("email", "john.smith@email.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Smith"))
                .andExpect(jsonPath("$.email").value("john.smith@email.com"));
    }

    @Test
    void getUsersByName() throws Exception {
        this.mockMvc
                .perform(get("/users/")
                        .param("email", "collin.williams@email.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Collin Williams"))
                .andExpect(jsonPath("$.email").value("collin.williams@email.com"));
    }

    @Test
    void createUser() throws Exception {
        UserImpl user = new UserImpl();
        user.setName("Mark Steinberg");
        user.setEmail("mark.steinberg@gmail.com");
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(user);
        this.mockMvc
                .perform(post("/users/")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(jsonBody)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Mark Steinberg"))
                .andExpect(jsonPath("$.email").value("mark.steinberg@gmail.com"));
    }

    @Test
    void updateUser() throws Exception {
        UserImpl user = new UserImpl();
        user.setId(1L);
        user.setName("Markus Steinberg");
        user.setEmail("markus.steinberg@gmail.com");
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(user);
        this.mockMvc
                .perform(put("/users/")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(jsonBody)
                )
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Markus Steinberg"))
                .andExpect(jsonPath("$.email").value("markus.steinberg@gmail.com"));
    }

    @Test
    void deleteUser() throws Exception {
        int userIdToDelete = 1;
        this.mockMvc
                .perform(delete("/users/{id}", userIdToDelete))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
