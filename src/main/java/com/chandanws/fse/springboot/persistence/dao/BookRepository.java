package com.chandanws.fse.springboot.persistence.dao;

import com.chandanws.fse.springboot.persistence.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
