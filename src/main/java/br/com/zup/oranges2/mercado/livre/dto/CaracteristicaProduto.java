package br.com.zup.oranges2.mercado.livre.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.oranges2.mercado.livre.entity.Produto;

@Entity
public class CaracteristicaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String descricao;

	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;

	public CaracteristicaProduto(@NotBlank String nome, @NotBlank String descricao, @NotNull @Valid Produto produto) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}

	@Deprecated
	public CaracteristicaProduto() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		CaracteristicaProduto other = (CaracteristicaProduto) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

}
