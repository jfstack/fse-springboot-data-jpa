package com.chandanws.fse.springboot.service;

import com.chandanws.fse.springboot.dto.SubjectDto;
import com.chandanws.fse.springboot.persistence.dao.BookRepository;
import com.chandanws.fse.springboot.persistence.dao.SubjectRepository;
import com.chandanws.fse.springboot.persistence.entity.Book;
import com.chandanws.fse.springboot.persistence.entity.Subject;
import com.chandanws.fse.springboot.persistence.exception.RecordAlreadyPresentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SubjectRepository subjectRepository;
    private final BookRepository bookRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository,
                              BookRepository bookRepository) {

        this.subjectRepository = subjectRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void save(SubjectDto subjectDto) {

        logger.info("save(Enter)");

        Subject subject = fromDto(subjectDto);

        subjectRepository.findById(subject.getSubjectId())
                .ifPresent(subject1 -> {
                    throw new RecordAlreadyPresentException(
                            "Subject with given id is already present");
                });

        subjectRepository.save(subject);

        logger.info("save(Exit)");

    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        subjectRepository.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SubjectDto> findById(Long id) {

        Optional<Subject> subject = subjectRepository.findById(id);

        return toDtoOptional(subject);

    }

    private Subject fromDto(SubjectDto subjectDto) {

        Subject subject = new Subject();

        Set<Book> references =
                Stream.of(subjectDto.getBooks())
                        .flatMap(books -> books.stream())
                        .map(book -> bookRepository.findById(book.getBookId()).get())
                        .collect(Collectors.toSet());

        subject.setSubjectId(subjectDto.getSubjectId());
        subject.setSubtitle(subjectDto.getSubtitle());
        subject.setDurationInHrs(subjectDto.getDurationInHrs());
        subject.setReferences(references);

        return subject;
    }

    private SubjectDto toDto(Subject subject) {

        SubjectDto dto = new SubjectDto();

        dto.setSubjectId(subject.getSubjectId());

        dto.setSubtitle(subject.getSubtitle());

        dto.setDurationInHrs(subject.getDurationInHrs());

        dto.setBooks(subject.getReferences().stream().collect(Collectors.toList()));

        return dto;
    }

    private Optional<SubjectDto> toDtoOptional(Optional<Subject> subject) {

        if (subject.isPresent()) {

            Subject entity = subject.get();

            return Optional.of(toDto(entity));

        } else {

            return Optional.empty();
        }

    }
}
