package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.utils.enums.BookStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books", schema = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "author", nullable = false)
    private String author;

    @NotNull
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "book_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @Column(name = "minimum_age", nullable = false)
    @Min(value = 0, message = "age is not valid")
    @Max(value = 18, message = "age is not valid")
    private Integer minimumAge;

    public Book(String title, String author, Long bookId, String description, BookStatus bookStatus, Integer minimumAge) {
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.description = description;
        this.bookStatus = bookStatus;
        this.minimumAge = minimumAge;
    }

    public Book(String title, String author, Long bookId, String description, Integer minimumAge) {
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.description = description;
        this.minimumAge = minimumAge;
    }
}
