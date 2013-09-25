package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.Authors;

public class AuthorsDto extends LinksDto{
private Authors Authors;
    
    public AuthorsDto(Authors Authors) {
	super();
	this.setAuthors(Authors);
    }

	public AuthorsDto() {
		// TODO Auto-generated constructor stub
	}

	public Authors getAuthors() {
		return Authors;
	}

	public void setAuthors(Authors Authors) {
		this.Authors = Authors;
	}

}
