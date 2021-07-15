package br.com.hereos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hereos.dto.MessageResponseDTO;
import br.com.hereos.model.Hero;
import br.com.hereos.service.HeroService;

@RestController
@RequestMapping("/api/v1/hero")
public class HeroController {

	private HeroService heroService;

	@Autowired
	public HeroController(HeroService heroService) {
		this.heroService = heroService;
	}

	@PostMapping
	public ResponseEntity<MessageResponseDTO> createHero(@RequestBody Hero hero) {

		Hero savedHero = heroService.createHero(hero);

		return new ResponseEntity<MessageResponseDTO>(
				MessageResponseDTO.builder()
					.message(String.format("Create Hero with Id %s", savedHero.getId()))
						.build(), HttpStatus.CREATED);

	}
}
