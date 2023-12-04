package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.Book;
import org.example.mapper.BookFilterMapper;
import org.example.mapper.BookMapper;
import org.example.model.BookModel;
import org.example.model.basic.PageModel;
import org.example.model.create.BookCreateModel;
import org.example.model.filter.BookFilterModel;
import org.example.model.update.BookUpdateModel;
import org.example.repository.BookRepository;
import org.example.repository.filter.BookFilter;
import org.example.repository.specification.BookSpecification;
import org.example.service.BooksService;
import org.example.utils.enums.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.example.utils.constans.Constants.MAX_BOOKS_IN_PAGE;
import static org.example.utils.exception.NotFoundException.Code.BOOK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BooksService {

    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    private final BookFilterMapper bookFilterMapper;

    @Override
    @Transactional
    public BookModel createBook(BookCreateModel bookCreateModel) {
        Book book = bookMapper.toEntityFromDto(bookCreateModel);
        book.setBookStatus(BookStatus.FREE);
        bookRepository.save(book);
        return bookMapper.toModelFromEntity(book);
    }

    @Override
    @Transactional
    public BookModel updateArticle(BookUpdateModel bookUpdateModel) {
        Book book = bookRepository
                .findById(bookUpdateModel.id())
                .orElseThrow(BOOK_NOT_FOUND::get);
        if(bookUpdateModel.description()!=null) {
            book.setDescription(bookUpdateModel.description());
        }
        if(bookUpdateModel.minimumAge()!=null) {
            book.setMinimumAge(book.getMinimumAge());
        }
        Book savedBook = bookRepository.save(book);
        return bookMapper.toModelFromEntity(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public PageModel<BookModel> getAllFiltered(BookFilterModel filterModel, Integer page, Integer limit) {

        BookFilter filter = bookFilterMapper.toFilter(filterModel);

        BookSpecification specification = new BookSpecification(filter);
        var pageable = PageRequest.of(
                page != null ? page : 0,
                limit != null ? limit : MAX_BOOKS_IN_PAGE
        );
        Page<Book> foundBooks = bookRepository.findAll(specification, pageable);

        return PageModel.<BookModel>builder()
                .content(
                        foundBooks
                                .stream()
                                .map(bookMapper::toModelFromEntity)
                                .toList()
                )
                .currentPage(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .totalPages(foundBooks.getTotalPages())
                .totalElements(foundBooks.getTotalElements())
                .build();
    }
}
