package domain.exception;

import java.io.Serializable;

public class BookNotFoundException extends Exception implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public BookNotFoundException(String msg) {
		super(msg);
	}

}
