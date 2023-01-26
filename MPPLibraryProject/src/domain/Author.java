package domain;

import java.io.Serializable;

public class Author extends Person implements Serializable{
	private static final long serialVersionUID = -3813141402215634413L;
	private String authorId;
	private String bio;
	
	public String getBio() {
		return bio;
	}
	
	public String getAuthorId() {
		return authorId;
	}
	
	public Author(String authorId, String firstName, String lastName, String telephone, Address address, String bio) {
		super(firstName, lastName, telephone, address);
		this.authorId = authorId;
		this.bio = bio;
		
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", bio=" + bio + "]";
	}
	
}
