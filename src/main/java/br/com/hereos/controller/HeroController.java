package br.com.hereos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hero")
public class HeroController {
	
	@GetMapping
	public String getBook(){
		return "Bem vindo ao gerenciador de Herois UA";
	}
}
