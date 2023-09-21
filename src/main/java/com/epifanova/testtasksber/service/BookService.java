package com.epifanova.testtasksber.service;

import com.epifanova.testtasksber.model.Book;
import com.epifanova.testtasksber.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public  Book saveBook(Book book) {
    return bookRepository.save(book);
  }

  public Optional<Book> getBook(Long id){
    return bookRepository.findBookById(id);
  }

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public void deleteBook(Long id){
    bookRepository.deleteBookById(id);
  }
}
