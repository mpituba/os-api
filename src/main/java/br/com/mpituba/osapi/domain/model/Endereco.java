package br.com.mpituba.osapi.domain.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {
	
	@EqualsAndHashCode.Include
	private Long id;
	
	private String cep;
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	private Cidade cidade;
	
	private Estado estado;
	
	private String pais;
	
}
