package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.example.utils.enums.BookStatus;

@Data
@Builder
public class BookModel {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("author")
    private String author;

    @NotNull
    @JsonProperty("bookId")
    private Long bookId;

    @NotNull
    @JsonProperty("description")
    private String description;

    @NotNull
    @JsonProperty("bookStatus")
    private BookStatus bookStatus;

    @NotNull
    @JsonProperty("minimumAge")
    private Integer minimumAge;

}
