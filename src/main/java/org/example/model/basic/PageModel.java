package org.example.model.basic;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class PageModel<T> {
    private List<? extends T> content;

    private Integer currentPage;

    private Integer pageSize;

    private Integer totalPages;

    private Long totalElements;
}
