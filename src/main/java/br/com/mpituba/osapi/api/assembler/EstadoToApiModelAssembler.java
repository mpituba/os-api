package br.com.mpituba.osapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mpituba.osapi.api.model.EstadoApiModel;
import br.com.mpituba.osapi.domain.model.Estado;

@Component
public class EstadoToApiModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * @MPituba
	 * This method transforms a Estado entity to a EstadoAPiModel using ModelMapper.
	*/
	
	public EstadoApiModel toApiModel(Estado estado) {
		return modelMapper.map(estado, EstadoApiModel.class);
	}
	
	/**
	 * @MPituba
	 * This method transforms a Estado list collection in a EstadoApiModel list collection.
	 */
	
	public List<EstadoApiModel> toCollectionModel(List<Estado> estados) {
		return estados.stream()
				.map(estado -> toApiModel(estado))
				.collect(Collectors.toList());
	}
	
	
	
}
