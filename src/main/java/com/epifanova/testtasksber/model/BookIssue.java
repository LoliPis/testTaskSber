package com.epifanova.testtasksber.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "book_issue")
@NoArgsConstructor
@Getter
@Setter
public class BookIssue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="book_ID", nullable=false)
  private Book book;

  @ManyToOne
  @JoinColumn(name="user_ID", nullable=false)
  private User user;

  @Column(name = "issue_date")
  private Date issueDate;

  @Column(name = "return_date")
  private Date returnDate;

}
