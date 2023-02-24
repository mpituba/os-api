package br.com.mpituba.osapi.domain.exception;

public abstract class OsEntityNotFoundException extends OsBusinessException {

	private static final long serialVersionUID = 1L;
	
	public OsEntityNotFoundException (String message) {
		
		super (message); 
		
	}
	
}
