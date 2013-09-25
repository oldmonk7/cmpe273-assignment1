package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.Author;


public class AuthorDto extends LinksDto {
	
	
	private Author author;

    /**
     * @param author
     */
    public AuthorDto(Author author) {
	super();
	this.author = author;
    }

    /**
     * @return the author
     */
    public Author getauthor() {
	return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setauthor(Author author) {
	this.author = author;
    }
	
}
