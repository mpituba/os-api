package br.com.mpituba.osapi.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "tipo_pessoa")
	private TipoPessoa tipoPessoa;
	
	@Column(name = "tratamento")
	private String tratamento;
	
	@Column(name = "nome")
	private String nome;
	
	@Embedded
	private Endereco endereco;
	
	@Column(name = "ativo")
	private Boolean ativo;
	
	@Column(name = "data_criacao")
	private OffsetDateTime dataCriacao;
	
	@Column(name = "data_ultima_desativacao")
	private OffsetDateTime dataUltimaDesativacao;
	
	
}



