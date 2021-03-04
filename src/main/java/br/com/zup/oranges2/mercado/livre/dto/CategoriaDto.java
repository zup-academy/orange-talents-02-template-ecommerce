package br.com.zup.oranges2.mercado.livre.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zup.oranges2.mercado.livre.entity.Categoria;
import br.com.zup.oranges2.mercado.livre.validation.ExistsId;
import br.com.zup.oranges2.mercado.livre.validation.UniqueValue;

public class CategoriaDto {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;

	@Positive
	@ExistsId(domainClass = Categoria.class, fieldName = "idCategoriaMae")
	private Long idCategoriaMae;

	public CategoriaDto(@NotBlank String nome, @Positive Long idCategoriaMae) {
		super();
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}

	@Deprecated
	public CategoriaDto() {
		
	}
	@Override
	public String toString() {
		return "CategoriaDto [nome=" + nome + ", idCategoriaMae=" + idCategoriaMae + "]";
	}

	public Categoria toModel(EntityManager manager) {
		Categoria categoria = new Categoria(nome);
		if (idCategoriaMae != null) {
			Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
			Assert.notNull(categoriaMae, "O id da categoria tem que ser válido");
			categoria.setMae(categoriaMae);
		}
		return categoria;
	}

}
