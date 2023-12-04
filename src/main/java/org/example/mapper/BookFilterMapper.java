package org.example.mapper;

import org.example.model.filter.BookFilterModel;
import org.example.repository.filter.BookFilter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookFilterMapper {

    BookFilter toFilter(BookFilterModel model);

}
