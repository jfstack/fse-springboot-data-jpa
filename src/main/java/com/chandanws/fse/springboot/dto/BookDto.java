package com.chandanws.fse.springboot.dto;


import com.chandanws.fse.springboot.persistence.constraints.NumericConstraint;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class BookDto {

    @NotNull(message = "Id must be provided")
    private Long bookId;

    @NotNull
    @Size(min = 5, max = 30, message = "Title must be between 5 to 30 characters")
    private String title;

    @NotNull(message = "Price must be provided")
    private Double price;

    @NotNull(message = "Publish date must be provided")
    private LocalDate publishDate;

    @NumericConstraint(message = "Volume must be an positive integer")
    private Integer volume;

    public BookDto() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", publishDate=" + publishDate +
                ", volume=" + volume +
                '}';
    }
}
