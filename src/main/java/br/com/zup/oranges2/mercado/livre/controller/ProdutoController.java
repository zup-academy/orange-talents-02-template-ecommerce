package br.com.zup.oranges2.mercado.livre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.oranges2.mercado.livre.dto.ProdutoDto;
import br.com.zup.oranges2.mercado.livre.entity.Produto;
import br.com.zup.oranges2.mercado.livre.entity.Usuario;
import br.com.zup.oranges2.mercado.livre.repository.UsuarioRepository;
import br.com.zup.oranges2.mercado.livre.validation.ProibeNomeIgualDeCaracteristicaValidator;

@RestController
public class ProdutoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeNomeIgualDeCaracteristicaValidator());
	}
	
	@PostMapping("/produtos")
	@Transactional
	public String cadastraProduto(@RequestBody @Valid ProdutoDto produtoDto) {
		
		Usuario dono = usuarioRepository.findByEmail("dayana@email.com.br").get();
		Produto novoproduto = produtoDto.toModel(manager, dono);
		manager.persist(novoproduto);
		return novoproduto.toString();
	}
	
	
	

}
