package br.com.mpituba.osapi.domain.exception;

public class OsBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public OsBusinessException (String message) {
		
		super(message);
		
	}
	
	public OsBusinessException (String message, Throwable cause) {
		
		super(message, cause);
		
	}
	
}
