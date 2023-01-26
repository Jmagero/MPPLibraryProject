package domain.exception;

import java.io.Serializable;

public class NewMemberException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4213135867154941383L;
	
	public NewMemberException() {
		super();
	}
	public NewMemberException(String msg) {
		super(msg);
	}
	public NewMemberException(Throwable t) {
		super(t);
	}

}
