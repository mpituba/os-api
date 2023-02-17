package br.com.mpituba.osapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mpituba.osapi.api.assembler.EstadoToApiModelAssembler;
import br.com.mpituba.osapi.api.model.EstadoApiModel;
import br.com.mpituba.osapi.domain.model.Estado;
import br.com.mpituba.osapi.domain.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoToApiModelAssembler estadoToApiModelAssembler;
	
	@GetMapping
	public List<EstadoApiModel> listar() {
		
		List<Estado> estados = estadoRepository.findAll();
		
		List<EstadoApiModel> estadoListApiModel = estadoToApiModelAssembler
					.toCollectionModel(estados);
		
		return estadoListApiModel;
		
	}
	
	
	
}
