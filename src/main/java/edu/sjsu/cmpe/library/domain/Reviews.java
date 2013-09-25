package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;

public class Reviews {
private  int id;
private int rating;
private String comment;
ArrayList<Reviews> reviews=new ArrayList<Reviews>();
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
public int getRating() {
	return rating;
}

public void setRating(int rating) {
	this.rating = rating;
}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
