package org.example.model.update;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookUpdateModel(

        @JsonProperty("id") Long id,

        @JsonProperty("bookId") Long bookId,

        @JsonProperty("description") String description,

        @JsonProperty("minimumAge") Integer minimumAge
)
{ }
