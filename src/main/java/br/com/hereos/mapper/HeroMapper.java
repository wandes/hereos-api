package br.com.hereos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.hereos.dto.HeroDTO;
import br.com.hereos.model.Hero;

@Mapper(componentModel = "spring")
public interface HeroMapper {

	HeroMapper INSTANCE = Mappers.getMapper(HeroMapper.class);

	@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
	Hero toModel(HeroDTO dto);

	HeroDTO toDTO(Hero dto);
}
