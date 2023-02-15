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
public final class Executor extends Pessoa{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column
	private String titulo;
	
	@Column(name = "email")
	private String email;
	
	@Column
	private String documento;
	
	@Column(nullable = false)
	private String tipoDocumento;
	
	@Column(nullable = false)
	private OffsetDateTime dataCadastro;
	
}
