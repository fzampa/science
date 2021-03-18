package br.com.lab.exception;

public class NoAttributeFoundException extends RuntimeException {

	private static final long serialVersionUID = -3595036381657144624L;

	public NoAttributeFoundException() {
		super();
	}

	public NoAttributeFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoAttributeFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoAttributeFoundException(String message) {
		super(message);
	}

	public NoAttributeFoundException(Throwable cause) {
		super(cause);
	}

}
