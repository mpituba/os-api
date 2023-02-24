package br.com.mpituba.osapi.domain.exception;

public class OSEntityInUseException extends OsBusinessException {

	private static final long serialVersionUID = 1L;
	
	public OSEntityInUseException (String message) {
		
		super(message);
		
	}
	
	
}
