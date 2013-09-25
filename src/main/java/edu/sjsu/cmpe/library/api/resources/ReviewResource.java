package edu.sjsu.cmpe.library.api.resources;
import java.util.ArrayList;
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
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;
import edu.sjsu.cmpe.library.repository.ReviewRepositoryInterface;

@Path("/v1/books/{isbn}/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ReviewResource {
	
	private final ReviewRepositoryInterface reviewRepository;
	
	public ReviewResource(ReviewRepositoryInterface reviewRepository) {
		this.reviewRepository = reviewRepository;
	    }
	
	@POST
    @Timed(name = "create-review")
    public Response createReview(Review request,@PathParam("isbn") LongParam isbn) {
	
	Review savedReview = reviewRepository.saveReview(request, isbn.get());
    
	String location = "/books/"+isbn.get()+"/reviews"+savedReview.getId();
	
	ReviewDto reviewResponse = new ReviewDto(savedReview);
	reviewResponse.addLink(new LinkDto("view-review", location, "GET"));
	
	// Add other links if needed

	return Response.status(201).entity(reviewResponse).build();
    }
    @GET
    @Timed(name = "view-review")
    @Path("/{review-id}")
    public Response getReviewByReviewId(@PathParam("review-id") LongParam reviewId, @PathParam("isbn") LongParam isbn) {
    	
    	Review review = reviewRepository.getReviewByReviewId(reviewId.get(),isbn.get());
    	ReviewDto reviewResponse = new ReviewDto(review);
    	reviewResponse.addLink(new LinkDto("view-review", "/books/"+review.getId()+"/reviews/" + reviewId.get(),
    		"GET"));
    	// add more links

    	return Response.status(200).entity(reviewResponse).build();
        }
    
    @GET
    @Timed(name = "view-all-reviews")
    public Response getAllReviews( @PathParam("isbn") LongParam isbn) {
    
     ArrayList<Review> reviews = reviewRepository.getAllReviews(isbn.get());
     ReviewsDto reviewResponse = new ReviewsDto(reviews);
     return Response.status(200).entity(reviewResponse).build();
     
    	
    }
}
