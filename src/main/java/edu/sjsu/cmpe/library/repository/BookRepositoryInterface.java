package edu.sjsu.cmpe.library.repository;


import java.util.List;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Author;

/**
 * Book repository interface.
 * 
 * What is repository pattern?
 * 
 * @see http://martinfowler.com/eaaCatalog/repository.html
 */
public interface BookRepositoryInterface {
    /**
     * Save a new book in the repository
     * 
     * @param newBook
     *            a book instance to be create in the repository
     * @return a newly created book instance with auto-generated ISBN
     */
    Book saveBook(Book newBook);

    /**
     * Retrieve an existing book by ISBN
     * 
     * @param isbn
     *            a valid ISBN
     * @return a book instance
     */
    Book getBookByISBN(Long isbn);
    
    void deleteBook(Long isbn);
    
    Book updateBookStatus(String status, Long isbn);

    Author getAuthorById(int authorId, Long isbn);

    List<Author> getAllAuthors(Long isbn);
	

    // TODO: add other operations here!
}
