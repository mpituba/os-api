package br.com.mpituba.osapi.domain.model;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
abstract class Pessoa {
	
	@EqualsAndHashCode.Include
	protected Long id;
	
	protected TipoPessoa tipoPessoa;
	
	protected String tratamento;
	
	protected String nome;
	
	protected Endereco endereco;
	
	protected Boolean ativo;
	
	protected OffsetDateTime dataCriacao;
	
	protected OffsetDateTime dataUltimaDesativacao;
	
}
