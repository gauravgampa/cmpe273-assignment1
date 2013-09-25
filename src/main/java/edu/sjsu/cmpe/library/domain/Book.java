package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic=false)
public class Book {
	public Book(){
		
	}
    private int isbn;
    @JsonProperty("num-pages")
    private int pages;
    @NotEmpty(message= "Title is required")
    private String title;
    @NotEmpty(message= "Date is required")
    @JsonProperty("publication-date")
    private String date;
    private String language;
    private String status="available";
    private ArrayList<Authors> auth=new ArrayList<Authors>();
    public ArrayList<Reviews> reviews=new ArrayList<Reviews>();
    static int rcnt=1;
    /**
     * @return the isbn
     */
   
    public int getIsbn() {
	return isbn;
    }

    /**
     */
    public void setIsbn(int isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    public String getDate(){
    	return date;
        }
    public void setDate(String date){
    	this.date=date;
        }
    public String getLanguage(){
    	return language;
        }
    public void setLanguage(String language){
    	this.language=language;
        }
    public int getPages(){
    	return pages;
        }
    public void setPages(int pages){
    	this.pages=pages;
        }
    public String getStatus(){
    	return status;
        }
    public void setStatus(String status){
    	this.status=status;
    }
    public ArrayList<Authors> getAuthors() {
    		return auth;
    	}
    public void setAuthors(ArrayList<Authors> authors) {
            Authors a=new Authors();
            a.incr();
    		this.auth = authors;
    	}
    public ArrayList<Reviews> getReviews() {
		return reviews;
	}

public void setReviews(ArrayList<Reviews> reviews) {
	  this.reviews=reviews;
      }

public ArrayList<Reviews> createReviews(Reviews r2) {
	
		r2.setId(rcnt);
		if(reviews.size()==0)
		{
			reviews=new ArrayList<Reviews>();	
		}
		reviews.add(r2);
		rcnt++;
		return reviews;
	}

public Reviews revId(int id) {
	Reviews review=new Reviews();
	for(int r=0;r<reviews.size();r++){
		if(r==id){
			review=reviews.get(id);
		}
	}
	return review;
}

public ArrayList<Reviews> allRevs() {
	ArrayList<Reviews> review=new ArrayList<Reviews>();
	for(int r=0;r<reviews.size();r++){
		review.add(reviews.get(r));
	}
return review;
}

public Authors authId(int id) {
	Authors author=new Authors();
	for(int a=0;a<auth.size();a++){
		if(a==id){
			author=auth.get(id);
		}
	}
	return author;
}

public ArrayList<Authors> allAuths() {
	ArrayList<Authors> author=new ArrayList<Authors>();
	for(int a=0;a<auth.size();a++){
		author.add(auth.get(a));
	}
return author;
}
}