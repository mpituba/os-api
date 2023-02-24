package br.com.mpituba.osapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.mpituba.osapi.domain.exception.OSEstadoNotFoundException;
import br.com.mpituba.osapi.domain.model.Estado;
import br.com.mpituba.osapi.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String MESSAGE_ESTADO_IN_USE
			= "Estado code %d cannot be removed. It's in use.";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		
		return estadoRepository.save(estado);
		
	}
	
	public void excluir(Long estadoId) {
		
		try {
			
			estadoRepository.deleteById(estadoId);
			
		}catch (EmptyResultDataAccessException e) {
			
			throw new OSEstadoNotFoundException(estadoId);
			
		}catch (DataIntegrityViolationException  e) {
			
			String.format(MESSAGE_ESTADO_IN_USE, estadoId);
			
		}
	}
	
	public Estado searchEstadoOrFail(Long estadoId) {
		
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new OSEstadoNotFoundException(estadoId));
				
		
	}
	
	
}
