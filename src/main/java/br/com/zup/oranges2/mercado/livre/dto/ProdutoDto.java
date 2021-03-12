package br.com.zup.oranges2.mercado.livre.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.oranges2.mercado.livre.entity.Categoria;
import br.com.zup.oranges2.mercado.livre.entity.Produto;
import br.com.zup.oranges2.mercado.livre.entity.Usuario;
import br.com.zup.oranges2.mercado.livre.validation.ExistsId;
import br.com.zup.oranges2.mercado.livre.validation.UniqueValue;

public class ProdutoDto {

	@NotBlank
	@UniqueValue(domainClass = Produto.class, fieldName = "nome")
	private String nome;

	@NotNull
	@Positive
	private BigDecimal valor;

	@Positive
	private int quantidade;

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	@Size(min = 3)
	@Valid
	private List<CaracteristicaDto> caracteristicas = new ArrayList<>();

	public ProdutoDto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @Positive int quantidade,
			@NotBlank @Size(max = 1000) String descricao, @NotNull Long idCategoria,
			List<CaracteristicaDto> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public Produto toModel(EntityManager manager, Usuario dono) {
		Optional<Categoria> categoria = Optional.ofNullable(manager.find(Categoria.class, idCategoria));
		return new Produto(this.nome, this.valor, this.quantidade, this.descricao, categoria.get(), dono,
				this.caracteristicas);
	}

	@Override
	public String toString() {
		return "ProdutoDto [nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade + ", descricao="
				+ descricao + ", idCategoria=" + idCategoria + ", caracteristicas=" + caracteristicas + "]";
	}

	@Deprecated
	public ProdutoDto() {

	}

	public boolean temCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		for (CaracteristicaDto caracteristica : caracteristicas) {
			if (!nomesIguais.add(caracteristica.getNome())) {
				return true;
			}
		}

		return false;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public List<CaracteristicaDto> getCaracteristicas() {
		return caracteristicas;
	}

}
