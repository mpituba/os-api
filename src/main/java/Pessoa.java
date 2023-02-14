import java.time.OffsetDateTime;

import br.com.mpituba.osapi.domain.model.Endereco;
import br.com.mpituba.osapi.domain.model.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa {
	
	@EqualsAndHashCode.Include
	private Long id;
	
	private TipoPessoa tipoPessoa;
	
	private String tratamento;
	
	private String nome;
	
	private Endereco endereco;
	
	private Boolean ativo;
	
	private OffsetDateTime dataCriacao;
	
	private OffsetDateTime dataUltimaDesativacao;
	
}
