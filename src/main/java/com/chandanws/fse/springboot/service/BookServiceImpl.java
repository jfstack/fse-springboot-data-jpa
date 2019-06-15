package com.chandanws.fse.springboot.service;

import com.chandanws.fse.springboot.dto.BookDto;
import com.chandanws.fse.springboot.persistence.dao.BookRepository;
import com.chandanws.fse.springboot.persistence.entity.Book;
import com.chandanws.fse.springboot.persistence.exception.RecordAlreadyPresentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("bookService")
public class BookServiceImpl implements BookService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void save(BookDto bookDto) {
        logger.info("save(Enter)");

        Book book = fromDto(bookDto);

        bookRepository.findById(book.getBookId()).ifPresent(book1 -> {
            throw new RecordAlreadyPresentException("Book with given id is already present");
        });

        bookRepository.save(book);

        logger.info("save(Exit)");
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        logger.info("deleteById(Enter)");

        bookRepository.deleteById(id);

        logger.info("deleteById(Exit)");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookDto> findById(Long id) {
        logger.info("findById(Enter)");

        Optional<Book> found = bookRepository.findById(id);

        logger.info("findById(Exit)");
        return toDtoOptional(found);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        logger.info("findAll(Enter)");

        List<BookDto> list = bookRepository
                .findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        logger.info("findAll(Exit)");
        return list;
    }

    private Book fromDto(BookDto dto) {

        Book book = new Book();

        book.setBookId(dto.getBookId());
        book.setTitle(dto.getTitle());
        book.setPrice(dto.getPrice());
        book.setVolume(dto.getVolume());
        book.setPublishDate(dto.getPublishDate());

        return book;
    }

    private BookDto toDto(Book book) {

        BookDto dto = new BookDto();

        dto.setBookId(book.getBookId());
        dto.setTitle(book.getTitle());
        dto.setPrice(book.getPrice());
        dto.setVolume(book.getVolume());
        dto.setPublishDate(book.getPublishDate());

        return dto;
    }

    private Optional<BookDto> toDtoOptional(Optional<Book> book) {

        if (book.isPresent()) {
            return Optional.of(toDto(book.get()));
        } else {
            return Optional.empty();
        }
    }
}
