package br.com.mpituba.osapi.domain.exception;

public class OSBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public OSBusinessException (String message) {
		
		super(message);
		
	}
	
	public OSBusinessException (String message, Throwable cause) {
		
		super(message, cause);
		
	}
	
}
