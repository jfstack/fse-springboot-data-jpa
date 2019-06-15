package com.chandanws.fse.springboot.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subjects2")
public class Subject {

    @NotNull
    @Id
    private Long subjectId;

    @NotNull
    @Size(min = 5, max = 30)
    private String subtitle;

    private int durationInHrs;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject")
    private Set<Book> references;


    public Subject() {
        this.references = new HashSet<>();
    }

    public Subject(Long subjectId) { this.subjectId = subjectId;}

    public Subject(Long subjectId, String subtitle, int durationInHrs, Set<Book> references) {
        this.subjectId = subjectId;
        this.subtitle = subtitle;
        this.durationInHrs = durationInHrs;
        this.references = references;
    }

    public Subject(Long subjectId, String subtitle, int durationInHrs) {
        this.subjectId = subjectId;
        this.subtitle = subtitle;
        this.durationInHrs = durationInHrs;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getDurationInHrs() {
        return durationInHrs;
    }

    public Set<Book> getReferences() {
        return references;
    }

    public void addReference(Book book) {
        if(Objects.isNull(this.references))
            this.references = new HashSet<>();
        if(Objects.nonNull(book))
            this.references.add(book);
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setDurationInHrs(int durationInHrs) {
        this.durationInHrs = durationInHrs;
    }

    public void setReferences(Set<Book> references) {
        this.references = references;
    }

//    public List<Book> getBooks() {
//        return books;
//    }

//    public void setBooks(List<Book> books) {
//        this.books = books;
//    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (!subjectId.equals(subject.subjectId)) return false;
        return subtitle.equals(subject.subtitle);
    }

//    @Override
//    public int hashCode() {
//        int result = subjectId.hashCode();
//        result = 31 * result + subtitle.hashCode();
//        return result;
//    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subtitle='" + subtitle + '\'' +
                ", durationInHrs=" + durationInHrs +
                ", references=" + references +
//                ", books=" + books +
                '}';
    }
}
