package br.com.hereos.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.hereos.builder.HeroRequestDTOBuilder;
import br.com.hereos.dto.mapper.HeroMapper;
import br.com.hereos.dto.request.HeroRequestDTO;
import br.com.hereos.dto.response.HeroResponseDTO;
import br.com.hereos.exception.HeroNotFoundException;
import br.com.hereos.model.Hero;
import br.com.hereos.repository.HeroRepository;

// pra rodar essa classe utiliza a extensão do mockito
@ExtendWith(MockitoExtension.class)
public class HeroServiceImplTest {

	private static final long INVALID_HERO_ID = 2L;

	private static final long VALID_HERO_ID = 1L;

	// criando mock da classe repository
	@Mock
	private HeroRepository heroRespository;

	private HeroMapper heroMapper = HeroMapper.INSTANCE;

	// injetar um mock do repository na classe HeroServiceImpl
	@InjectMocks
	private HeroServiceImpl heroServiceImpl;

	@Test
	void WhenCalledReturnsAllRegisteredHeroes() {

		// given
		Hero expectedHero = heroMapper.toModel(HeroRequestDTOBuilder.builder().build().toHeroRequestDTO());

		// when
		Mockito.when(heroRespository.findAll()).thenReturn(Collections.singletonList(expectedHero));

		// then
		List<HeroResponseDTO> listAll = heroServiceImpl.listAll();

		assertNotNull(listAll);
		Mockito.verify(heroRespository, Mockito.times(1)).findAll();
	}

	@Test
	void whenHeroInformedThenItShouldBeCreated() {

		// given
		HeroRequestDTO heroDto = HeroRequestDTOBuilder.builder().build().toHeroRequestDTO();
		Hero expectedSavedHero = heroMapper.toModel(heroDto);

		// when
		Mockito.when(heroRespository.save(any(Hero.class))).thenReturn(expectedSavedHero);

		// then
		HeroResponseDTO createdHero = heroServiceImpl.create(heroDto);

		assertEquals(heroDto.getId(), createdHero.getId());
		assertEquals(heroDto.getName(), createdHero.getName());

		Mockito.verify(heroRespository, Mockito.times(1)).save(any(Hero.class));

	}

	@Test
	void whenHeroInformedThenItShouldBeUpdated() throws HeroNotFoundException {

		// given
		HeroRequestDTO heroDto = HeroRequestDTOBuilder.builder().build().toHeroRequestDTO();
		Hero expectedUpdateHero = heroMapper.toModel(heroDto);

		// when
		Mockito.when(heroRespository.findById(expectedUpdateHero.getId())).thenReturn(Optional.of(expectedUpdateHero));
		Mockito.when(heroRespository.save(any(Hero.class))).thenReturn(expectedUpdateHero);

		// then
		HeroResponseDTO updateHero = heroServiceImpl.update(heroDto, heroDto.getId());

		assertEquals(heroDto.getId(), updateHero.getId());
		assertEquals(heroDto.getName(), updateHero.getName());

		Mockito.verify(heroRespository, Mockito.times(1)).save(any(Hero.class));

	}

	@Test
	void whenAlreadyRegisterHeroInformedThenAnExceptionShouldBeThrow() throws HeroNotFoundException {

		// given
		HeroRequestDTO heroDto = HeroRequestDTOBuilder.builder().build().toHeroRequestDTO();

		// when
		Mockito.when(heroRespository.findById(heroDto.getId())).thenReturn(Optional.empty());
		// Optional.of(heroDto))

		// then
		assertThrows(HeroNotFoundException.class, () -> heroServiceImpl.findById(heroDto.getId()));

	}

	@Test
	void whenExclusionIsCalledShouldExcludeHero() throws HeroNotFoundException {

		// given
		HeroRequestDTO heroDto = HeroRequestDTOBuilder.builder().build().toHeroRequestDTO();
		Hero expectedDeleteHero = heroMapper.toModel(heroDto);

		// when
		Mockito.when(heroRespository.findById(expectedDeleteHero.getId())).thenReturn(Optional.of(expectedDeleteHero));
		doNothing().when(heroRespository).deleteById(expectedDeleteHero.getId());

		// then
		heroServiceImpl.delete(expectedDeleteHero.getId());
		verify(heroRespository, Mockito.times(1)).findById(expectedDeleteHero.getId());
		verify(heroRespository, Mockito.times(1)).deleteById(expectedDeleteHero.getId());
	}

}
