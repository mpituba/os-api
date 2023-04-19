package br.com.mpituba.osapi.domain.exception;

public abstract class OSEntityNotFoundException extends OSBusinessException {

	private static final long serialVersionUID = 1L;
	
	public OSEntityNotFoundException (String message) {
		
		super (message); 
		
	}
	
}
