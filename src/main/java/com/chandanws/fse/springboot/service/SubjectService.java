package com.chandanws.fse.springboot.service;

import com.chandanws.fse.springboot.dto.SubjectDto;

import java.util.Optional;

public interface SubjectService {

    void save(SubjectDto subject);

    void deleteById(Long id);

    Optional<SubjectDto> findById(Long id);

}
