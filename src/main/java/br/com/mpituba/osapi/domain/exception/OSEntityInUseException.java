package br.com.mpituba.osapi.domain.exception;

public class OSEntityInUseException extends OSBusinessException {

	private static final long serialVersionUID = 1L;
	
	public OSEntityInUseException (String message) {
		
		super(message);
		
	}
	
	
}
