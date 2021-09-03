package br.com.hereos.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.hereos.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${security.jwt.expiration}")
	private String expiration;
	
	@Value("${security.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authentication) {
		
		User user = (User) authentication.getPrincipal();
		
		Date today = new Date();
		Date dateExpiration = new Date(today.getTime() + Long.parseLong(expiration));

		return Jwts.builder()
				.setIssuer("API Hereos")
				.setSubject(user.getId().toString())
				.setIssuedAt(today)
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUser(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
