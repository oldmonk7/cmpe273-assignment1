package edu.sjsu.cmpe.library.repository;
import java.util.ArrayList;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;

public interface ReviewRepositoryInterface {
	
	Review saveReview(Review newReview, Long id);
    Review getReviewByReviewId(Long reviewId, Long isbn);
    ArrayList<Review> getAllReviews(Long isbn);
	
}
