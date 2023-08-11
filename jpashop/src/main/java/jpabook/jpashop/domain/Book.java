package jpabook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Book extends Item{
    private String author;
    private String Isbn;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }
}
