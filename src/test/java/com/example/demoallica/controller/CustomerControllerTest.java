package com.example.demoallica.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class CustomerControllerTest {

    public static final String BASE_PATH = "/v1/customers";

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void saveCustomerTest() throws Exception {

        String body = """ 
                {
                "first_name": "Jestin",
                "last_name": "Mathew",
                "date_of_birth": "1993-02-02"
                }
                """;
        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Jestin")));
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        String body = """ 
                {
                "first_name": "Jestin",
                "last_name": "Mathew",
                "date_of_birth": "1993-02-02"
                }
                """;
        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Jestin")));

        mockMvc.perform(get(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Jestin")));
    }

    @Test
    public void getCustomerTest() throws Exception {
        String body = """ 
                {
                "first_name": "Jestin",
                "last_name": "Mathew",
                "date_of_birth": "1993-02-02"
                }
                """;
        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Jestin")));

        mockMvc.perform(get(BASE_PATH.concat("/1"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Jestin")));
    }

    @Test
    public void getCustomerNotFoundTest() throws Exception {
        mockMvc.perform(get(BASE_PATH.concat("/100"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Not Found")));
    }
}