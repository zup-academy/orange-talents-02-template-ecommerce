package br.com.zup.oranges2.mercado.livre.dto;

import br.com.zup.oranges2.mercado.livre.entity.Produto;

public class UsuarioLogadoDto {

	private String email;

	private String nome;

	private String senha;

	public UsuarioLogadoDto(Produto novoProduto) {

	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

}
