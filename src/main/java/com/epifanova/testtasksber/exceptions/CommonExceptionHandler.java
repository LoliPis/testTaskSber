package com.epifanova.testtasksber.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {


  @ExceptionHandler(BookNotFoundError.class)
  public ResponseEntity<RestError> handleActionForbiddenException(
      BookNotFoundError ex) {
    return configureResponse(HttpStatus.NOT_FOUND, ex);
  }

  @ExceptionHandler(NotFoundBooks.class)
  public ResponseEntity<RestError> handleActionForbiddenException(
      NotFoundBooks ex) {
    return configureResponse(HttpStatus.NO_CONTENT, ex);
  }

  private ResponseEntity<RestError> configureResponse(
      HttpStatus httpStatus,
      Throwable ex) {
    RestError error = new RestError(httpStatus.toString(), ex.getMessage());

    return ResponseEntity
        .status(httpStatus)
        .body(error);
  }
}
