package br.com.hereos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.hereos.dto.request.HeroRequestDTO;
import br.com.hereos.dto.response.HeroResponseDTO;
import br.com.hereos.exception.HeroNotFoundException;

public interface HeroService {
	
	HeroResponseDTO findById(Long heroId) throws HeroNotFoundException;
	
	Page<HeroResponseDTO> getPageable(Pageable pageable);
	
	HeroResponseDTO create(HeroRequestDTO hero);
	
	HeroResponseDTO update(HeroRequestDTO hero, Long heroId) throws HeroNotFoundException;
	
	void delete(Long id) throws HeroNotFoundException;
	
}
