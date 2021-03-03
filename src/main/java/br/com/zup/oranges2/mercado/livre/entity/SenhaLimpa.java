package br.com.zup.oranges2.mercado.livre.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class SenhaLimpa {

	private String senha;

	public SenhaLimpa(@NotBlank @Length(min = 6) String senha) {
		Assert.hasLength(senha, "Senha não pode ser em branco");
		Assert.isTrue(senha.length() >= 6, "Senha tem que ter no mínimo 6 caracteres");

		this.senha = senha;
	}

	public String hash() {

		return new BCryptPasswordEncoder().encode(senha);
	}
}
