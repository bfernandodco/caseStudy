package br.com.fiap.store.exception;

public class DatabaseException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DatabaseException() {
		super();
	}
	
	public DatabaseException(String message, Throwable cause, Boolean enableSupppression, Boolean writableStackTrace) {
		super(message, cause, enableSupppression, writableStackTrace);
	}
	
	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DatabaseException(String message) {
		super(message);
	}
	
	public DatabaseException(Throwable cause) {
		super(cause);
	}
}
