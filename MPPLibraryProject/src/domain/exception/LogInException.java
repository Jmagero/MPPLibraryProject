package domain.exception;

import java.io.Serializable;

public class LogInException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1022353883438629463L;
	
	public LogInException() {
		super();
	}
	public LogInException(String msg) {
		super(msg);
	}
	public LogInException(Throwable t) {
		super(t);
	}

}
