package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Book;
import org.example.model.BookModel;
import org.example.model.basic.PageModel;
import org.example.model.basic.SuccessModel;
import org.example.model.create.BookCreateModel;
import org.example.model.filter.BookFilterModel;
import org.example.model.update.BookUpdateModel;
import org.example.service.BooksService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/book")
@RestController
public class BookController {

    private final BooksService booksService;

    @PostMapping
    private SuccessModel<BookModel> createBook(
            @RequestBody BookCreateModel bookCreateModel
    ) {
        var createdBook = booksService.createBook(bookCreateModel);
        return SuccessModel.okSuccessModel(createdBook, "Create book");
    }

    @PutMapping
    private SuccessModel<BookModel> updateBook(
            @RequestBody BookUpdateModel bookUpdateModel
    ) {
        var createdBook = booksService.updateArticle(bookUpdateModel);
        return SuccessModel.okSuccessModel(createdBook, "Update book");
    }

    @GetMapping
    private PageModel<BookModel> getBookFiltered(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Long bookId,
            @RequestParam(required = false) Integer minimumAge,

            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit
    ) {
        BookFilterModel filterModel = new BookFilterModel(title, author, bookId, minimumAge);

        return booksService.getAllFiltered(filterModel, page, limit);
    }
}
