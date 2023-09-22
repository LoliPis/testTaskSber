package com.epifanova.testtasksber.DTO;

import com.epifanova.testtasksber.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
  private Long id;
  private String title;
  private String author;
  private int year;
  private String genre;

  public static BookDTO from(Book book) {
    BookDTO bookDTO = new BookDTO();
    bookDTO.setId(book.getId());
    bookDTO.setTitle(book.getTitle());
    bookDTO.setAuthor(book.getAuthor());
    if (book.getYear() <= LocalDate.now().getYear()) {
      bookDTO.setYear(book.getYear());
    }
    bookDTO.setGenre(book.getGenre());
    return bookDTO;
  }

  public Book toBook() {
    Book book = new Book();
    book.setId(this.id);
    book.setTitle(this.title);
    book.setAuthor(this.author);
    if (this.year <= LocalDate.now().getYear()) {
      book.setYear(this.year);
    }
    book.setGenre(this.genre);
    return book;
  }
}
