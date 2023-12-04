package org.example.controller.book;

import lombok.extern.slf4j.Slf4j;
import org.example.controller.BaseControllerTest;
import org.example.entity.Book;
import org.example.mapper.BookMapper;
import org.example.model.BookModel;
import org.example.model.create.BookCreateModel;
import org.example.repository.BookRepository;
import org.example.utils.enums.BookStatus;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class BookBaseTest extends BaseControllerTest {

    @Autowired
   protected BookRepository bookRepository;

    @Autowired
    protected BookMapper bookMapper;

    @AfterEach
    void deleteAllBookAfterEachTest() {
        testDatabaseManager.runInTransaction(() -> {
            bookRepository.deleteAll(bookRepository.findAll());

        });
    }

    protected Book saveBook(
            String title,
            String author,
            Long bookId,
            String description,
            Integer minimumAge){
        BookCreateModel book= new BookCreateModel(title, author, bookId, description, minimumAge);
        Book book1 = bookMapper.toEntityFromDto(book);
        book1.setBookStatus(BookStatus.FREE);
       return bookRepository.save(book1);
    }

}
