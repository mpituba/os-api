package br.com.mpituba.osapi.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
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
@EqualsAndHashCode(callSuper=false)
public class Executor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "tipo_pessoa")
	private String tipoPessoa;
	
	@Column(name = "tratamento")
	private String tratamento;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "titulo")
	private String titulo;
	
	//private Endereco endereco;
	
	@Column(name = "ativo")
	private Boolean ativo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "documento")
	private String documento;
	
	@Column(name = "tipo_documento",nullable = false)
	private String tipoDocumento;
	
	@Column(name = "data_cadastro",nullable = false)
	private OffsetDateTime dataCadastro;
	
}
