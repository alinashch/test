package org.example.repository.filter;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.utils.enums.BookStatus;

@Getter
@Setter
public class BookFilter {

    private String title;

    private String author;

    private Long bookId;

    private BookStatus bookStatus;

    private Integer minimumAge;
}
