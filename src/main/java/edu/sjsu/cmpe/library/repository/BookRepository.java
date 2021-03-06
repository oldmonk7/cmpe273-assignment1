package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;

public class BookRepository implements BookRepositoryInterface {
    /** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
    private final ConcurrentHashMap<Long, Book> bookInMemoryMap;
    private static long authID;
    /** Never access this key directly; instead use generateISBNKey() */
    private long isbnKey;

    public BookRepository(ConcurrentHashMap<Long, Book> bookMap) {
	checkNotNull(bookMap, "bookMap must not be null for BookRepository");
	bookInMemoryMap = bookMap;
	isbnKey = 0;
    }

    /**
     * This should be called if and only if you are adding new books to the
     * repository.
     * 
     * @return a new incremental ISBN number
     */
    private final Long generateISBNKey() {
	// increment existing isbnKey and return the new value
	return Long.valueOf(++isbnKey);
    }

    /**
     * This will auto-generate unique ISBN for new books.
     */
    @Override
    public Book saveBook(Book newBook) {
	checkNotNull(newBook, "newBook instance must not be null");
	// Generate new ISBN
	Long isbn = generateISBNKey();
	newBook.setTitle(newBook.getTitle());
	newBook.setIsbn(isbn);
	newBook.setLanguage(newBook.getLanguage());
	newBook.setNumPages(newBook.getNumPages());
	newBook.setPublicationDate(newBook.getPublicationDate());
	newBook.setStatus(newBook.getStatus());
	for (int i=0; i< newBook.getAuthor().size(); i++)
	{
		newBook.getAuthor().get(i).setId(++authID);
		
	}
	
	newBook.setAuthor(newBook.getAuthor());
	
	
	// TODO: create and associate other fields such as author

	// Finally, save the new book into the map
	bookInMemoryMap.putIfAbsent(isbn, newBook);

	return newBook;
    }

    /**
     * @see edu.sjsu.cmpe.library.repository.BookRepositoryInterface#getBookByISBN(java.lang.Long)
     */
    @Override
    public Book getBookByISBN(Long isbn) {
	checkArgument(isbn > 0,
		"ISBN was %s but expected greater than zero value", isbn);
	return bookInMemoryMap.get(isbn);
    }
    
    @Override
    public void deleteBook(Long isbn) {
    	checkArgument(isbn > 0,
    			"ISBN was %s but expected greater than zero value", isbn);
    	bookInMemoryMap.remove(isbn);
    }
    
    @Override
    public Book updateBookStatus(String status, Long isbn){
    	checkNotNull(status, "New status must not be null");
    	Book tempBook = bookInMemoryMap.get(isbn);
    	tempBook.setStatus(status);
    	bookInMemoryMap.replace(isbn, tempBook);
    	return bookInMemoryMap.get(isbn);	
    	
    }
    
    @Override
    public Author getAuthorById(int authorId, Long isbn){
    	int position=0;
    	for(int i=0; i< bookInMemoryMap.get(isbn).getAuthor().size();i++){
    		
    		if(bookInMemoryMap.get(isbn).getAuthor().get(i).getId() !=authorId)
    		position=i;
        	
    }
    	return bookInMemoryMap.get(isbn).getAuthor().get(position-1);
    }
    	public List<Author> getAllAuthors(Long isbn){
    		
        return bookInMemoryMap.get(isbn).getAuthor();
         	
     }
    
    }



