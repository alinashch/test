package org.example.model.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.utils.enums.BookStatus;

@Data
@AllArgsConstructor
public class BookFilterModel{
        @JsonProperty("title")
        private String title;

        @JsonProperty("author")
        private String author;

        @JsonProperty("bookId")
        private Long bookId;

        @JsonProperty("minimumAge")
        private Integer minimumAge;
}
