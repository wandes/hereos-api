package br.com.hereos.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.hereos.dto.request.HeroRequestDTO;
import br.com.hereos.dto.response.HeroResponseDTO;
import br.com.hereos.model.Hero;

@Mapper(componentModel = "spring")
public interface HeroMapper {

	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
	Hero toModel(HeroRequestDTO heroRequestDTO);
	
	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
	HeroResponseDTO toDTO(Hero heroDTO);
	
}
