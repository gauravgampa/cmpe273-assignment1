package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.Reviews;

public class ReviewsDto extends LinksDto{
private Reviews reviews;
    
    public ReviewsDto(Reviews reviews) {
	super();
	this.setReviews(reviews);
    }

	public ReviewsDto() {
		// TODO Auto-generated constructor stub
	}

	public Reviews getReviews() {
		return reviews;
	}

	public void setReviews(Reviews reviews) {
		this.reviews = reviews;
	}

}
