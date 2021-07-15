package br.com.hereos.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hereos.model.Hero;
import br.com.hereos.repository.HeroRepository;

@Service
public class HeroService {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HeroService.class);
	
	private HeroRepository heroRespository;

	@Autowired
	public HeroService(HeroRepository heroRespository) {
		this.heroRespository = heroRespository;
	}
	
	public Hero createHero( Hero hero) {

		logger.warn(String.format("Criando: %s", hero.getName()));

		Hero savedHero = heroRespository.save(hero);
		
		return savedHero;

	}
}
