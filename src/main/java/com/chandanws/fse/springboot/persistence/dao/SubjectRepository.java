package com.chandanws.fse.springboot.persistence.dao;

import com.chandanws.fse.springboot.persistence.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
