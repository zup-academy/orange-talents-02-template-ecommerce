package br.com.zup.oranges2.mercado.livre.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zup.oranges2.mercado.livre.dto.CaracteristicaDto;
import br.com.zup.oranges2.mercado.livre.dto.CaracteristicaProduto;
import io.jsonwebtoken.lang.Assert;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotNull
	@Positive
	private BigDecimal valor;

	@Positive
	private int quantidade;

	@NotBlank
	@Length(max = 1000)
	private String descricao;

	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario dono;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @Positive int quantidade,
			@NotBlank @Size(max = 1000) String descricao, @NotNull @Valid Categoria categoria,
			@NotNull @Valid Usuario dono, @Size(min = 3) @Valid Collection<CaracteristicaDto> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas.addAll(caracteristicas.stream()
				.map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));

		Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa de no mínimo três características");

	}

	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade
				+ ", descricao=" + descricao + ", categoria=" + categoria + ", dono=" + dono + ", caracteristicas="
				+ caracteristicas + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
