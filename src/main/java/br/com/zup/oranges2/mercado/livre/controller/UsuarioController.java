package br.com.zup.oranges2.mercado.livre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.oranges2.mercado.livre.dto.UsuarioDto;
import br.com.zup.oranges2.mercado.livre.entity.Usuario;

@RestController
public class UsuarioController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping("/usuarios")
	@Transactional

	public String cadastrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {

		Usuario usuario = usuarioDto.toUsuario();
		manager.persist(usuario);
		return usuario.toString();
	}
}
