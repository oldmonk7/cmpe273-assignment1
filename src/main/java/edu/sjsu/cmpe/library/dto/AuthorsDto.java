package edu.sjsu.cmpe.library.dto;

import java.util.List;

import edu.sjsu.cmpe.library.domain.Author;

public class AuthorsDto extends LinksDto{

	private List<Author> author;

	public AuthorsDto(List<Author> author) {
		super();
		this.author = author;
	    }
	
	 public List<Author> getauthor() {
			return author;
		    }

		    /**
		     * @param author
		     *            the author to set
		     */
		    public void setauthor(List<Author> author) {
			this.author = author;
		    }
	
}
