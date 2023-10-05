package com.epifanova.testtasksber.exceptions;

public class NotFoundBooks extends RuntimeException {
  public NotFoundBooks(String message) {
    super(message);
  }
}
