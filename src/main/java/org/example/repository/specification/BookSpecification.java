package org.example.repository.specification;

import jakarta.persistence.criteria.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.example.entity.Book;
import org.example.repository.filter.BookFilter;
import org.example.utils.enums.BookStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BookSpecification implements Specification<Book> {

    private BookFilter filter;

    @NotNull
    @Override
    public Specification<Book> and(Specification<Book> other) {
        return Specification.super.and(other);
    }

    @NotNull
    @Override
    public Specification<Book> or(Specification<Book> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(
            @NotNull Root<Book> root,
            @NotNull CriteriaQuery<?> query,
            @NotNull CriteriaBuilder builder
    ) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(
                builder.notEqual(root.get("bookStatus"), BookStatus.TAKEN)
        );

        Long booksIds = filter.getBookId();
        if (booksIds != null) {
            predicates.add(
                    root.get("bookId").in(booksIds)
            );
        }

        String title = filter.getTitle();
        if (title != null) {
            predicates.add(
                    builder.equal(root.get("title"), title)
            );
        }

        Integer minimumAge = filter.getMinimumAge();
        if (minimumAge != null) {
            predicates.add(
                    builder.equal(root.get("minimumAge"), minimumAge)
            );
        }


        String author = filter.getAuthor();
        if (author != null) {
            predicates.add(
                    builder.equal(root.get("author"), author)
            );
        }

        BookStatus bookStatus = filter.getBookStatus();
        if (bookStatus != null && bookStatus != BookStatus.TAKEN) {
            predicates.add(
                    builder.equal(root.get("bookStatus"), bookStatus)
            );
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
