package br.com.zup.oranges2.mercado.livre.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.zup.oranges2.mercado.livre.entity.Usuario;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

	@Override
	public UserDetails map(Object shouldBeASystemUser) {
		return new UsuarioLogado((Usuario)shouldBeASystemUser);
	}
	

}
