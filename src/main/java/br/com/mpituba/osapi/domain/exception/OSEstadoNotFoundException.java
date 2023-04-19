package br.com.mpituba.osapi.domain.exception;

public class OSEstadoNotFoundException extends OSEntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public OSEstadoNotFoundException (String message) {
		
		super(message);
		
	}
	
	public OSEstadoNotFoundException (Long estadoId) {
		
		this(String.format("A Estado with code %d does not exist.", estadoId));
		
	}
	
}
