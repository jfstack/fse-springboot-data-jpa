package com.chandanws.fse.springboot.service;

import com.chandanws.fse.springboot.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void save(BookDto bookDto);

    void deleteById(Long id);

    Optional<BookDto> findById(Long id);

    List<BookDto> findAll();
}
