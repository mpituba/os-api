package br.com.mpituba.osapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mpituba.osapi.api.assembler.EstadoApiModelToModelDisassembler;
import br.com.mpituba.osapi.api.assembler.EstadoToApiModelAssembler;
import br.com.mpituba.osapi.api.model.EstadoApiModel;
import br.com.mpituba.osapi.api.model.input.EstadoApiModelInput;
import br.com.mpituba.osapi.domain.model.Estado;
import br.com.mpituba.osapi.domain.repository.EstadoRepository;
import br.com.mpituba.osapi.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@Autowired
	private EstadoToApiModelAssembler estadoToApiModelAssembler;
	
	@Autowired
	private EstadoApiModelToModelDisassembler estadoDisassembler;
	
	@GetMapping
	public List<EstadoApiModel> listar() {
		
		List<Estado> estados = estadoRepository.findAll();
		
		List<EstadoApiModel> estadoListApiModel = estadoToApiModelAssembler
					.toCollectionModel(estados);
		
		return estadoListApiModel;
		
	}
	
	@GetMapping("/{estadoId}")
	public EstadoApiModel buscar (@PathVariable Long estadoId) {
		
		Estado estado = cadastroEstado.searchEstadoOrFail(estadoId);
		
		return estadoToApiModelAssembler.toApiModel(estado);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoApiModel adicionar(@RequestBody  	@Valid
			EstadoApiModelInput estadoApiModelInput) {
								
			Estado estado = estadoDisassembler.toModel(estadoApiModelInput);
				
			estado = cadastroEstado.salvar(estado);
			
			return estadoToApiModelAssembler.toApiModel(estado);
		} 
	
	@PutMapping("/{estadoId}")
	public EstadoApiModel atualizar (@PathVariable Long estadoId,
				@RequestBody @Valid EstadoApiModelInput estadoInput) {
		
		Estado estado = estadoDisassembler.toModel(estadoInput);
		
		cadastroEstado.salvar(estado);
				
		return estadoToApiModelAssembler.toApiModel(estado);
		
	} 
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover (@PathVariable Long estadoId) {
		
		cadastroEstado.excluir(estadoId);
		
	}
	
}
