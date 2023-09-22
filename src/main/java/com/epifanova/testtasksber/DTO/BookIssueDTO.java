package com.epifanova.testtasksber.DTO;

import com.epifanova.testtasksber.model.BookIssue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookIssueDTO {
  private Long id;
  private BookDTO book;
  private UserDTO user;
  private Long issueDate;
  private Long returnDate;


  public static BookIssueDTO from(BookIssue bookIssue) {
    BookIssueDTO bookIssueDTO = new BookIssueDTO();
    bookIssueDTO.setId(bookIssue.getId());
    bookIssueDTO.setBook(BookDTO.from(bookIssue.getBook()));
    bookIssueDTO.setUser(UserDTO.from(bookIssue.getUser()));
    bookIssueDTO.setIssueDate(bookIssue.getIssueDate().getTime()); // Преобразование даты в число
    if (bookIssue.getReturnDate() != null) {
      bookIssueDTO.setReturnDate(bookIssue.getReturnDate().getTime()); // Преобразование даты в число
    }
    return bookIssueDTO;
  }

  // Метод для преобразования DTO-объекта в сущность BookIssue
  public BookIssue toBookIssue() {
    BookIssue bookIssue = new BookIssue();
    bookIssue.setId(this.id);
    bookIssue.setBook(this.book.toBook());
    bookIssue.setUser(this.user.toUser());
    bookIssue.setIssueDate(new Date(this.issueDate)); // Преобразование числа в дату
    if (this.returnDate != null) {
      bookIssue.setReturnDate(new Date(this.returnDate)); // Преобразование числа в дату
    }
    return bookIssue;
  }

}
