package br.com.hereos.dto.requestd;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
	
	private String email;
	private String password;
	
	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
	
	@Override
	public String toString() {
		return "LoginRequestDTO [email=" + email + ", password=" + password + "]";
	}
	
	
}
