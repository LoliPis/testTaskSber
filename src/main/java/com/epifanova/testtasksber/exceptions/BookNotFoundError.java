package com.epifanova.testtasksber.exceptions;

public class BookNotFoundError extends Error{
  public BookNotFoundError(String message) {
    super(message);
  }
}
