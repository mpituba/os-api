package br.com.mpituba.osapi.domain.exception;


public class OSPropertyNotFoundException extends OSBusinessException{
	
private static final long serialVersionUID = 1L;
	
	public OSPropertyNotFoundException (String message) {
		
		super(message);
		
	}
	
	public OSPropertyNotFoundException (Object object, OSPropertyNotFoundException ex) {
		
		this(String.format("A(s) propriedade(s)  %s da entidade %s não foram preenchidas.",
				ex.getClass().getFields().toString(), ex.getClass().getName()));
	}

	
}
