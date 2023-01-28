package domain.exception;

import java.io.Serializable;

public class NewBookException extends Exception implements  Serializable {
	private static final long serialVersionUID = 6463511870068860204L;
	public NewBookException() {
		super();
	}
	public NewBookException(String msg) {
		super(msg);
	}
	public NewBookException(Throwable t) {
		super(t);
	}

}
