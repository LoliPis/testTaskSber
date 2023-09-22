package com.epifanova.testtasksber.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void createUserTest() throws Exception {
    JSONObject json = new JSONObject();
    json.put("id", "1");
    json.put("name", "Ivan");
    json.put("email", "ivan@mail.ru");
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk());
  }

  @Test
  public void getAllUsersTest() throws Exception {
    mockMvc.perform(get("/users"))
        .andExpect(status().isNoContent());

    JSONObject json = new JSONObject();
    json.put("id", "1");
    json.put("name", "Ivan");
    json.put("email", "ivan@mail.ru");
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk());

    mockMvc.perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].id").value("1"));
  }

  @Test
  public void editUserTest() throws Exception {
    JSONObject json = new JSONObject();
    json.put("id", "1");
    json.put("name", "Ivan");
    json.put("email", "ivan@mail.ru");

    mockMvc.perform(put("/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isNotFound());

    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.id").value(1));

    json.put("name", "Petia");
    json.put("email", "petia@mail.ru");
    mockMvc.perform(put("/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("Petia"))
        .andExpect(jsonPath("$.email").value("petia@mail.ru"));
  }

  @Test
  public void getUserTest() throws Exception {
    mockMvc.perform(get("/users/1"))
        .andExpect(status().isNotFound());

    JSONObject json = new JSONObject();
    json.put("id", "1");
    json.put("name", "Ivan");
    json.put("email", "ivan@mail.ru");

    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk());

    mockMvc.perform(get("/users/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1"));
  }

  @Test
  public void deleteBookTest() throws Exception {
    mockMvc.perform(delete("/users/1"))
        .andExpect(status().isNotFound());

    JSONObject json = new JSONObject();
    json.put("id", "1");
    json.put("name", "Ivan");
    json.put("email", "ivan@mail.ru");
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString()))
        .andExpect(status().isOk());

    mockMvc.perform(delete("/users/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1"));
  }
}
