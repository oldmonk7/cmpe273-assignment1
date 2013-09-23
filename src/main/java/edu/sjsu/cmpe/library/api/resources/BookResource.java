package edu.sjsu.cmpe.library.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




import com.google.common.collect.Lists;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /**
     * BookResource constructor
     * 
     * @param bookRepository
     *            a BookRepository instance
     */
    public BookResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) {
	Book book = bookRepository.getBookByISBN(isbn.get());
	BookDto bookResponse = new BookDto(book);
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),
		"GET"));
	bookResponse.addLink(new LinkDto("update-book",
		"/books/" + book.getIsbn(), "POST"));
	// add more links

	return bookResponse;
    }

    @POST
    @Timed(name = "create-book")
    public Response createBook(Book request) {
	// Store the new book in the BookRepository so that we can retrieve it.
	Book savedBook = bookRepository.saveBook(request);

	String location = "/books/" + savedBook.getIsbn();
	BookDto bookResponse = new BookDto(savedBook);
	bookResponse.addLink(new LinkDto("view-book", location, "GET"));
	bookResponse.addLink(new LinkDto("update-book", location, "POST"));
	// Add other links if needed

	return Response.status(201).entity(bookResponse).build();
    }
    
    @DELETE
    @Path("/{isbn}")
    public Response deleteBook(@PathParam("isbn") final LongParam isbn){
    	bookRepository.deleteBook(isbn.get());
    	
    	LinksDto links = new LinksDto();
    	links.addLink(new LinkDto("create-book", "/books", "POST"));
        
    	return Response.status(200).entity(links).build();
    }
    @PUT
    @Path( "/{isbn}")
    public Response updateBook(@PathParam("isbn") LongParam isbn, @QueryParam("status") String newStatus){
    
    	Book updatedBook= bookRepository.updateBookStatus(newStatus, isbn.get());
    	
    	String location = "/books/" + updatedBook.getIsbn();
    	BookDto bookResponse = new BookDto(updatedBook);
    	
    	bookResponse.addLink(new LinkDto("view-book", location, "GET"));
    	bookResponse.addLink(new LinkDto("update-book", location, "PUT"));
    	bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
    	bookResponse.addLink(new LinkDto("create-book", location, "POST	"));
    	
    	return Response.status(200).entity(bookResponse).build();
    	
    
    
    }
}
