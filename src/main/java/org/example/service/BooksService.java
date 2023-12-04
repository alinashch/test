package org.example.service;

import org.example.model.BookModel;
import org.example.model.basic.PageModel;
import org.example.model.create.BookCreateModel;
import org.example.model.filter.BookFilterModel;
import org.example.model.update.BookUpdateModel;
import org.example.service.impl.BookServiceImpl;

public interface BooksService {

    BookModel createBook(BookCreateModel bookCreateModel);

    BookModel updateArticle(BookUpdateModel bookUpdateModel);

    PageModel<BookModel> getAllFiltered(BookFilterModel filterModel, Integer page, Integer limit);
}
