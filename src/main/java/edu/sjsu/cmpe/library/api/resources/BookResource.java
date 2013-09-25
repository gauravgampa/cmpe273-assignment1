package edu.sjsu.cmpe.library.api.resources;

import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Authors;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
	static int inc=0;
	private static HashMap <Integer,Book> bhm = new HashMap <Integer,Book>();

    public BookResource() {
    }
    
    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    
    public BookDto getBk(@PathParam("isbn") int isbn) {
	Book bk =bhm.get(isbn);	
	BookDto bkResponse = new BookDto(bk);
	bkResponse.addLink(new LinkDto("view-book", "/books/" + bk.getIsbn(),"GET"));
	bkResponse.addLink(new LinkDto("update-book","/books/" + bk.getIsbn(), "PUT"));
	bkResponse.addLink(new LinkDto("delete-book", "/books/" + bk.getIsbn(),"DELETE"));	
	bkResponse.addLink(new LinkDto("create-review","/books/" + bk.getIsbn()+"/reviews", "POST"));
	if(bk.reviews.size()>0)
	bkResponse.addLink(new LinkDto("view-all-review","/books/" + bk.getIsbn()+"/reviews", "GET"));
	return bkResponse;
    }
    
    @POST
    @Timed(name = "create-book")
    
    public Response CreateBook(@Valid Book bk){
    inc++;
    bk.setIsbn(inc);
    bhm.put(new Integer(inc),bk);
    /*Authors a=new Authors();
    a.incr();*/
    LinksDto bkResponse = new LinksDto();
    bkResponse.addLink(new LinkDto("view-book", "/books/" + bk.getIsbn(),"GET"));
    bkResponse.addLink(new LinkDto("update-book","/books/" + bk.getIsbn(), "PUT"));
    bkResponse.addLink(new LinkDto("delete-book","/books/" +bk.getIsbn(),"DELETE"));
    bkResponse.addLink(new LinkDto("create-review","/books/" +bk.getIsbn()+ "/reviews", "POST"));
    return Response.status(201).entity(bkResponse).build();
}
    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response UpdateBook(@PathParam("isbn") int isbn, @QueryParam("status") String status){
     Book bk=bhm.get(isbn);
     bk.setStatus(status);
     LinksDto bkResponse = new LinksDto();
     bkResponse.addLink(new LinkDto("view-book", "/books/" + bk.getIsbn(),"GET"));
     bkResponse.addLink(new LinkDto("update-book","/books/" + bk.getIsbn(), "PUT"));
     bkResponse.addLink(new LinkDto("delete-book", "/books/" + bk.getIsbn(),"DELETE"));	
     bkResponse.addLink(new LinkDto("create-review","/books/" + bk.getIsbn()+"/reviews", "POST"));
     if(bk.reviews.size()>0)
     bkResponse.addLink(new LinkDto("view-all-review","/books/" + bk.getIsbn()+"/reviews", "GET"));
   	 return Response.status(200).entity(bkResponse).build();
  }
    
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public Response DeleteBook(@PathParam("isbn") int isbn){ 
     Book bk=bhm.get(isbn);
     bhm.remove(isbn);
     LinksDto bkResponse=new LinksDto();
     bkResponse.addLink(new LinkDto("create-book", "/books/"+bk.getIsbn(),"POST"));
     return Response.status(200).entity(bkResponse).build();
}
    @POST
    @Path("/{isbn}/reviews")
    @Timed(name = "create-review")    
    public Response createReview(@PathParam("isbn") int isbn,Reviews reviews) {
	 Book bk =bhm.get(isbn);
	 ArrayList<Reviews> review=bk.createReviews(reviews);
	 LinksDto bkResponse = new LinksDto();
	 bkResponse.addLink(new LinkDto("view-review", "/books/" + bk.getIsbn()+"/reviews/"+reviews.getId(),"GET"));
	 return Response.status(201).entity(bkResponse).build();
    }
    @GET
    @Path("/{isbn}/reviews/{id}")
    @Timed(name="view-reviews")
    public Response viewReview(@PathParam("isbn") int isbn,@PathParam("id") int id){
    	Book bk =bhm.get(isbn);	
    	Reviews reviews=new Reviews();
    	reviews=bk.revId(id);
    	ReviewsDto revResponse = new ReviewsDto(reviews);
    	revResponse.addLink(new LinkDto("view-review", "/books/" + bk.getIsbn()+"/reviews/"+reviews.getId(),"GET"));
    	return Response.status(200).entity(revResponse).build();	
    }
    @GET
    @Path("/{isbn}/reviews")
    @Timed(name="view-reviews")
    public Response viewAllReviews(@PathParam("isbn") int isbn){
    	Book bk =bhm.get(isbn);	
    	ArrayList<Reviews> reviews=new ArrayList<Reviews>();
    	reviews=bk.allRevs();
		ReviewsDto revResponse = new ReviewsDto();
    	for(int r=0;r<reviews.size();r++)
    	{
    		revResponse=new ReviewsDto(reviews.get(r));
    		
    	}
    	return Response.status(200).entity(reviews).build();	
    }
    @GET
    @Path("/{isbn}/authors/{id}")
    @Timed(name="view-author")
    public Response viewAuthor(@PathParam("isbn") int isbn,@PathParam("id") int id){
    	Book bk =bhm.get(isbn);	
    	Authors authors=new Authors();
    	authors=bk.authId(id);
    	AuthorsDto authResponse=new AuthorsDto(authors);
    	authResponse.addLink(new LinkDto("view-author", "/books/" + bk.getIsbn()+"/authors/"+authors.getId(),"GET"));
    	return Response.status(200).entity(authResponse).build();
    	
    }
    @GET
    @Path("/{isbn}/authors")
    @Timed(name="view-authors")
    public Response viewAllAuthors(@PathParam("isbn") int isbn){
    	Book bk =bhm.get(isbn);	
    	ArrayList<Authors> authors=new ArrayList<Authors>();
    	authors=bk.allAuths();
		AuthorsDto authResponse = new AuthorsDto();
    	for(int a=0;a<authors.size();a++)
    	{
    		authResponse=new AuthorsDto(authors.get(a));	
    	}
    	return Response.status(200).entity(authors).build();	
    }
}