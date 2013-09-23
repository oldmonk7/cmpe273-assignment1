package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.domain.Review;

@JsonPropertyOrder(alphabetic = true)

public class ReviewDto extends LinksDto {
	
	
	private Review review;

    /**
     * @param Review
     */
    public ReviewDto(Review review) {
	super();
	this.review = review;
    }

    /**
     * @return the Review
     */
    public Review getReview() {
	return review;
    }

    /**
     * @param Review
     *            the Review to set
     */
    public void setReview(Review review) {
	this.review = review;
    }
	
}
