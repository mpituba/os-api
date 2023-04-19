package br.com.mpituba.osapi.domain.model;


public enum TipoPessoa {

	FISICA("Pessoa Física"),
	JURIDICA("Pessoa Jurídica");
	
	private String descricao;

	TipoPessoa(String descricao) {
	}

	public String getDescricao() {
		return descricao;
	}
	
}