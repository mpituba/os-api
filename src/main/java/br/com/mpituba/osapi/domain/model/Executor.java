package br.com.mpituba.osapi.domain.model;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public final class Executor extends Pessoa{
	
	@EqualsAndHashCode.Include
	private Long id;
	
	private String titulo;
	
	private String email;
	
	private String documento;
	
	private String tipoDocumento;
	
	private OffsetDateTime dataCadastro;
	
}
