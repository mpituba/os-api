package br.com.mpituba.osapi.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import br.com.mpituba.osapi.core.validation.EstadoPostControllerValidationGroup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoApiModelInput {
	
	
	private Long id;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = EstadoPostControllerValidationGroup.EstadoApiModelInput.class)
	private String nome;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = EstadoPostControllerValidationGroup.EstadoApiModelInput.class)
	private String uf;
	
}
