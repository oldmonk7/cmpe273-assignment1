package edu.sjsu.cmpe.library.repository;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;

public class ReviewRepository implements ReviewRepositoryInterface{

	private final ConcurrentHashMap<Long, Review> reviewInMemoryMap;
	
	private long reviewKey;
	
		
	public ReviewRepository(ConcurrentHashMap<Long,Review> reviewMap) {
			checkNotNull(reviewMap, "bookMap must not be null for BookRepository");
			reviewInMemoryMap = reviewMap;
			reviewKey = 0;
		    }
	 private final Long generateReviewKey() {
			// increment existing isbnKey and return the new value
			return Long.valueOf(++reviewKey);
		    }

	public Review saveReview(Review newReview, Long id){
		
		checkNotNull(newReview, "newReview instance must not be null");
		Long reviewKey = generateReviewKey();
		newReview.setId(id);
		newReview.setRating(newReview.getRating());
		newReview.setComment(newReview.getComment());
	    
		reviewInMemoryMap.putIfAbsent(reviewKey, newReview);
		
		return newReview;
	
	}
	
}