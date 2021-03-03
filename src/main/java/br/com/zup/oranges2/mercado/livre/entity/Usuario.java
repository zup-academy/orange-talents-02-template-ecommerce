package br.com.zup.oranges2.mercado.livre.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	@Column(unique = true)
	private String email;

	@NotBlank
	@Length(min = 6)
	@Column
	private String senha;

	// @NotNull
	@PastOrPresent
	@Column
	private LocalDateTime instanteCriacao;

	@Deprecated
	public Usuario() {

	}

	public Usuario(@NotBlank @Email String email, @Valid @NotNull SenhaLimpa senhaLimpa) {
		Assert.isTrue(StringUtils.hasLength(email), "O email não pode ser em branco");
		Assert.notNull(senhaLimpa, "O objeto senha limpa não pode ser nulo");
		this.email = email;
		this.senha = senhaLimpa.hash();
		this.instanteCriacao = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + "]";
	}

}
