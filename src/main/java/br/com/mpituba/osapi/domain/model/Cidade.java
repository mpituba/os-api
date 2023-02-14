package br.com.mpituba.osapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {
	
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nome;
	
	private Estado estado;
	
	
	
	
	
	
}
