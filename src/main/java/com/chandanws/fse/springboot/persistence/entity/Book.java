package com.chandanws.fse.springboot.persistence.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "books2")
public class Book {

    @NotNull
    @Id
    private Long bookId;

    @NotNull
    @Size(min = 5, max = 30)
    private String title;

    @NotNull
    private Double price;

    @NotNull
    private LocalDate publishDate;

    private int volume;

    private Long subject;

    public Book() {
    }

    public Book(Long bookId, String title, Double price, LocalDate publishDate, int volume) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.publishDate = publishDate;
        this.volume = volume;
    }

    public Book(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!bookId.equals(book.bookId)) return false;
        return title.equals(book.title);
    }

//    @Override
//    public int hashCode() {
//        int result = bookId.hashCode();
//        result = 31 * result + title.hashCode();
//        return result;
//    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", publishDate=" + publishDate +
                ", volume=" + volume +
                '}';
    }
}
