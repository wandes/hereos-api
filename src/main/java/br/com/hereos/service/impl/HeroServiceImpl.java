package br.com.hereos.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.hereos.dto.mapper.HeroMapper;
import br.com.hereos.dto.request.HeroRequestDTO;
import br.com.hereos.dto.response.HeroResponseDTO;
import br.com.hereos.exception.HeroNotFoundException;
import br.com.hereos.model.Hero;
import br.com.hereos.repository.HeroRepository;
import br.com.hereos.service.HeroService;
import lombok.AllArgsConstructor;

@Service
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HeroServiceImpl implements HeroService {
	
	@Autowired
	private HeroRepository heroRespository;
	
	private HeroMapper heroMapper = HeroMapper.INSTANCE;

	public List<HeroResponseDTO> listAll() {

		List<Hero> allHereos = heroRespository.findAll();

		return allHereos.stream().map(heroMapper::toDTO).collect(Collectors.toList());
	}

	public HeroResponseDTO findById(Long id) throws HeroNotFoundException {

		Hero hero = verifyIfExists(id);
		HeroResponseDTO response = heroMapper.toDTO(hero);
		return response;
	}
	
	@Override
	public HeroResponseDTO create(HeroRequestDTO hero) {
		
		Hero heroToSave = heroMapper.toModel(hero);

		return heroMapper.toDTO(heroRespository.save(heroToSave));
	}

	@Override
	public HeroResponseDTO update(HeroRequestDTO hero, Long heroId) throws HeroNotFoundException {
		
		verifyIfExists(heroId);

		Hero heroToUpdate = heroMapper.toModel(hero);

		return heroMapper.toDTO(heroRespository.save(heroToUpdate));
	}
	
	public void delete(Long id) throws HeroNotFoundException {

		verifyIfExists(id);

		heroRespository.deleteById(id);
	}

//	@Override
//	public Page<HeroResponseDTO> getPageable(org.springframework.data.domain.Pageable pageable) {
//		return null;
//	}

	private Hero verifyIfExists(Long id) throws HeroNotFoundException {

		return heroRespository.findById(id).orElseThrow(() -> new HeroNotFoundException(id));
	}

	@Override
	public Page<HeroResponseDTO> getPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
