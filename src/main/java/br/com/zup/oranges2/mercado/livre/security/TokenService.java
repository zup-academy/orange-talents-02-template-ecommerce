package br.com.zup.oranges2.mercado.livre.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zup.oranges2.mercado.livre.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {

		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date date = new Date();
		Date expirationDate = new Date(date.getTime() + Long.parseLong(expiration));

		return Jwts.builder()
				.setIssuer("Mercado-Livre")
				.setSubject(usuario.getEmail())
				.setIssuedAt(date)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValid(String token) {

		try {
			Jwts.parser()
			.setSigningKey(this.secret)
			.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUsername(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(this.secret)
				.parseClaimsJws(token)
				.getBody();

		return claims.getSubject();
	}
}
