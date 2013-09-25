package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Book;

@JsonPropertyOrder(alphabetic = true)
public class BookDto extends LinksDto {
    private Book bk;
    
    public BookDto(Book bk) {
	super();
	this.bk = bk;
    }

    /**
     * @return the book
     */
    public Book getBook() {
	return bk;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setBook(Book bk) {
	this.bk = bk;
    }

	}
