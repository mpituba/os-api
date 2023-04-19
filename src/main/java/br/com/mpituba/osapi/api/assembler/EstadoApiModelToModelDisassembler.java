package br.com.mpituba.osapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mpituba.osapi.api.model.input.EstadoApiModelInput;
import br.com.mpituba.osapi.domain.model.Estado;

@Component
public class EstadoApiModelToModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * @MPituba
	 * This method transform a EstadoApiModelInput object in a Estado object.
	 */
	
	public Estado  toModel(EstadoApiModelInput estadoApiModelInput) {
		return modelMapper.map(estadoApiModelInput, Estado.class);
	}
	
	/**
	 * @MPituba
	 * This method copies a EstadoApiModelInput object into a Estado object.
	 */
	
	public void copyToModel(EstadoApiModelInput estadoApiModelInput, Estado estado) {
		modelMapper.map(estadoApiModelInput, estado);
	}
	
}
