package domain;

import java.io.Serializable;

public class LibraryMember extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7271709922942752329L;
	private String memberID;

	public LibraryMember(String memberID,String firstName, String lastName, String phoneNumber, Address address) {
		super(firstName, lastName, phoneNumber, address);
		this.memberID = memberID;
	}

	public String getMemberId() {
		return memberID;
	}
	
	
	
	

}
