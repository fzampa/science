package br.com.lab.exception;

public class NoCategoryFoundException extends RuntimeException {
	private static final long serialVersionUID = -7177619774079194970L;

	public NoCategoryFoundException() {
		super();
	}

	public NoCategoryFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoCategoryFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoCategoryFoundException(String message) {
		super(message);
	}

	public NoCategoryFoundException(Throwable cause) {
		super(cause);
	}

}
