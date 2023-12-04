package org.example.util;

import lombok.RequiredArgsConstructor;
import org.example.entity.Book;
import org.example.model.BookModel;
import org.example.repository.BookRepository;
import org.example.utils.enums.BookStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.IntStream.rangeClosed;

@RequiredArgsConstructor
@Service
public class TestUserService {

    private final BookRepository bookRepository;


    public List<BookModel> getAllTestUsers() {
        return rangeClosed(1, 5)
                .mapToObj(userIdInt -> getTestUserModel((long) userIdInt))
                .toList();
    }

    public BookModel getTestUserModel(Long userId) {
        var name = "test%d".formatted(userId);

        return BookModel.builder()
                .id(userId)
                .author(name)
                .title(name)
                .bookId(0L)
                .bookStatus(BookStatus.FREE)
                .description(name)
                .minimumAge(0)
                .build();
    }

    public void createTestUsers() {
        rangeClosed(1, 5).forEach(i -> createTestUser("test%d".formatted(i)));
    }

    private void createTestUser(String name) {
        Book userTransient = new Book();

        userTransient.setId(1L);
        userTransient.setMinimumAge(0);
        userTransient.setDescription(name);
        userTransient.setAuthor(name);
        userTransient.setBookId(1L);
        userTransient.setTitle(name);
        userTransient.setBookStatus(BookStatus.FREE);

        bookRepository.save(userTransient);
    }
}
