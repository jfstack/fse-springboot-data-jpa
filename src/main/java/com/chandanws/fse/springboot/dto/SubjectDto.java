package com.chandanws.fse.springboot.dto;

import com.chandanws.fse.springboot.persistence.constraints.NumericConstraint;
import com.chandanws.fse.springboot.persistence.entity.Book;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class SubjectDto {

    @NotNull(message = "Id must be provided")
    private Long subjectId;

    @NotNull
    @Size(min = 5, max = 30, message = "Title must be between 5 to 30 characters")
    private String subtitle;

    @NumericConstraint(message = "DuationInHrs must be an positive integer")
    private Integer durationInHrs;

    @NotNull
    private List<Book> books = new ArrayList<>();

    @AssertTrue(message = "At least one book must be selected")
    public boolean isValidBooks() {
        return books.size() > 0;
    }

    public SubjectDto() {
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getDurationInHrs() {
        return durationInHrs;
    }

    public void setDurationInHrs(Integer durationInHrs) {
        this.durationInHrs = durationInHrs;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "SubjectDto{" +
                "subjectId=" + subjectId +
                ", subtitle='" + subtitle + '\'' +
                ", durationInHrs=" + durationInHrs +
                ", books=" + books +
                '}';
    }
}
