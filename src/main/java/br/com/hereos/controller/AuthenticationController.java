package br.com.hereos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hereos.dto.TokenDto;
import br.com.hereos.dto.requestd.LoginRequestDTO;
import br.com.hereos.security.TokenService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Auth Controller")
@Log4j2
public class AuthenticationController {
	
	private AuthenticationManager authManager;
	
	private TokenService tokenService;
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginRequestDTO login){
		
		UsernamePasswordAuthenticationToken dateLogin = login.convert();
		
		try {
			Authentication authentication = authManager.authenticate(dateLogin);		
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));		
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	
	}
}
