package com.epifanova.testtasksber.controller;

import com.epifanova.testtasksber.model.Book;
import com.epifanova.testtasksber.model.User;
import com.epifanova.testtasksber.repository.BookRepository;
import com.epifanova.testtasksber.repository.UserRepository;
import com.epifanova.testtasksber.service.BookService;
import com.epifanova.testtasksber.service.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class BookIssueControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getAllBooksIssueTest() throws Exception {
    mockMvc.perform(get("/booksIssue"))
        .andExpect(status().isNoContent());

    JSONObject json = new JSONObject();
    json.put("id", "1");
    json.put("book_ID", "1");
    json.put("user_ID", "1");
    json.put("issue_date", "24-03-2023");
    json.put("return_date", "25-04-2023");
    mockMvc.perform(post("/booksIssue")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk());

    mockMvc.perform(get("/booksIssue"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].id").value("1"));
  }
}

