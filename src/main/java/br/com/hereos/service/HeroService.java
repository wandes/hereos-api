package br.com.hereos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hereos.dto.HeroDTO;
import br.com.hereos.exception.HeroNotFoundException;
import br.com.hereos.mapper.HeroMapper;
import br.com.hereos.model.Hero;
import br.com.hereos.repository.HeroRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HeroService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HeroService.class);

	private HeroRepository heroRespository;

	private HeroMapper heroMapper = HeroMapper.INSTANCE;

//	@Autowired
//	public HeroService(HeroRepository heroRespository) {
//		
//		this.heroRespository = heroRespository;
//	}

	public Hero createHero(HeroDTO heroDTO) {

		logger.warn("Criando: " + heroDTO.toString());

		Hero heroToSave = heroMapper.toModel(heroDTO);

		return heroRespository.save(heroToSave);

	}

	public List<HeroDTO> listAll() {

		logger.warn("Listando herois");

		List<Hero> allHereos = heroRespository.findAll();
		
		return allHereos.stream().map(heroMapper::toDTO).collect(Collectors.toList());
	}

	public HeroDTO findById(Long id) throws HeroNotFoundException {
		
		Hero hero = verifyIfExists(id);
		
		logger.warn("Pesquisando heroi por id " + id);
		
		return heroMapper.toDTO(hero);
	}

	public void delete(Long id) throws HeroNotFoundException {
		
		verifyIfExists(id);
		
		logger.warn("Excluindo heroi com id " + id);
		
		heroRespository.deleteById(id);
	}

	public Hero update(Long id, HeroDTO heroDTO) throws HeroNotFoundException {
		
		verifyIfExists(id);
		
		logger.warn("Atualizando o heroi de id: " + id);
		
		Hero heroToUpdate = heroMapper.toModel(heroDTO);

		return heroRespository.save(heroToUpdate);
	}
	
	private Hero verifyIfExists(Long id) throws HeroNotFoundException {
		
		return heroRespository.findById(id)
				.orElseThrow(()-> new HeroNotFoundException(id));
	}
}
