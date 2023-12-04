package org.example.mapper;

import org.example.entity.Book;
import org.example.model.BookModel;
import org.example.model.create.BookCreateModel;
import org.example.model.update.BookUpdateModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    Book toEntityFromDto(BookCreateModel createModel);

    Book toEntityFromDto(BookUpdateModel updateModel);

    BookModel toModelFromEntity(Book book);
}
