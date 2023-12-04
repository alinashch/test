package org.example.model.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import org.example.utils.enums.BookStatus;

@Builder
public record BookCreateModel(

        @JsonProperty("title") String title,

        @JsonProperty("author") String author,

        @JsonProperty("bookId") Long bookId,

        @JsonProperty("description") String description,

        @JsonProperty("minimumAge") @Min( 0) @Max(18) Integer minimumAge
) {
}
