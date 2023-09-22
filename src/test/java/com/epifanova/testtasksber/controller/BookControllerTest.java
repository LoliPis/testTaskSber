package com.epifanova.testtasksber.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class BookControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void createBookTest() throws Exception {
    JSONObject json = new JSONObject();
    json.put("id", "1");
    json.put("title", "Little women");
    json.put("author", "Louisa May Alcott");
    json.put("year", "1866");
    json.put("genre", "novel");
    mockMvc.perform(post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk());
  }

  @Test
  public void getAllBooksTest() throws Exception {
    mockMvc.perform(get("/books"))
        .andExpect(status().isNoContent());

    JSONObject json = new JSONObject();
    json.put("id", "1");
    json.put("title", "Little women");
    json.put("author", "Louisa May Alcott");
    json.put("year", "1866");
    json.put("genre", "novel");
    mockMvc.perform(post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk());

    mockMvc.perform(get("/books"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].id").value("1"));
  }

  @Test
  public void editBookTest() throws Exception {
    JSONObject json = new JSONObject();
    json.put("id", "1");
    json.put("title", "Little women");
    json.put("author", "Louisa May Alcott");
    json.put("year", "1866");
    json.put("genre", "novel");

    mockMvc.perform(put("/books/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isNotFound());

    mockMvc.perform(post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.id").value(1));

    json.put("title", "Tihii Don");
    json.put("author", "Mikhail Sholohov");
    mockMvc.perform(put("/books/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.title").value("Tihii Don"))
        .andExpect(jsonPath("$.author").value("Mikhail Sholohov"));
  }

  @Test
  public void getBookTest() throws Exception {
    mockMvc.perform(get("/books/1"))
        .andExpect(status().isNotFound());

    JSONObject json = new JSONObject();
    json.put("id", "1");
    json.put("title", "Little women");
    json.put("author", "Louisa May Alcott");
    json.put("year", "1866");
    json.put("genre", "novel");
    mockMvc.perform(post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk());

    mockMvc.perform(get("/books/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1"));
  }

  @Test
  public void deleteBookTest() throws Exception {
    mockMvc.perform(delete("/books/1"))
        .andExpect(status().isNotFound());

    JSONObject json = new JSONObject();
    json.put("id", "1");
    json.put("title", "Little women");
    json.put("author", "Louisa May Alcott");
    json.put("year", "1866");
    json.put("genre", "novel");
    mockMvc.perform(post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk());

    mockMvc.perform(delete("/books/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1"));
  }
}
