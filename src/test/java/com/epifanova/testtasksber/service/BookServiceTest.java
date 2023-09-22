package com.epifanova.testtasksber.service;

import com.epifanova.testtasksber.model.Book;
import com.epifanova.testtasksber.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

  private static List<Book> books;

  @Mock
  private BookRepository bookRepository;

  @InjectMocks
  private BookService bookService;

  @BeforeAll
  public static void initBooks() {
    Book book1 = new Book();
    book1.setId(1L);
    Book book2 = new Book();
    book2.setId(2L);
    books = new ArrayList<>();
    books.add(book1);
    books.add(book2);
  }

  @Test
  public void getAllTest() {
    when(bookRepository.findAll()).thenReturn(books);

    assertEquals(bookService.getAllBooks(), books);
  }

  @Test
  public void getBookTest() {
    Book book = new Book();
    book.setId(1L);

    when(bookRepository.findBookById(1L)).thenReturn(Optional.of(book));
    when(bookRepository.findBookById(2L)).thenReturn(Optional.empty());

    Optional<Book> presentBook = bookService.getBook(1L);
    Optional<Book> emptyBook = bookService.getBook(2L);

    assertTrue(presentBook.isPresent());
    assertEquals(presentBook.get(), book);
    assertTrue(emptyBook.isEmpty());
  }

  @Test
  public void saveBookTest() {
    Book book = new Book();

    bookService.saveBook(book);

    verify(bookRepository).save(book);
  }

}
