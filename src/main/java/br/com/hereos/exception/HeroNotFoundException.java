package br.com.hereos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HeroNotFoundException extends Exception {
	
	public HeroNotFoundException(Long id) {
		super(String.format("Heroi com o id %s não localizado!", id));
	}
}
