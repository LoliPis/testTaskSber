package com.epifanova.testtasksber.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Класс, представляющий сущность "Книга".
 */
@Entity
@Data
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String author;

  private int year;

  private String genre;
}
