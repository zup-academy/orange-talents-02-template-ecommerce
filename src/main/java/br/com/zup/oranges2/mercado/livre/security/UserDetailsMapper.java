package br.com.zup.oranges2.mercado.livre.security;

import org.springframework.security.core.userdetails.UserDetails;


public interface UserDetailsMapper {
	
	UserDetails map(Object shouldBeASystemUser);
}
