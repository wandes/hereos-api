package br.com.hereos.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import br.com.hereos.builder.HeroRequestDTOBuilder;
import br.com.hereos.builder.HeroResponseDTOBuilder;
import br.com.hereos.dto.mapper.HeroMapper;
import br.com.hereos.dto.request.HeroRequestDTO;
import br.com.hereos.dto.response.HeroResponseDTO;
import br.com.hereos.model.Hero;
import br.com.hereos.repository.HeroRepository;
import br.com.hereos.service.impl.HeroServiceImpl;
import br.com.hereos.utils.JsonConvertionUtils;

@ExtendWith(MockitoExtension.class)
public class HeroControllerTest {
	

	private HeroMapper heroMapper = HeroMapper.INSTANCE;
	private static final String HERO_API_URL_PATH = "/api/v1/hero";
	private static final Long VALID_HERO_ID = 1L;

	private MockMvc mockMvc;
	
	@Mock
	private HeroRepository heroRespository;
	
	@Mock
	private HeroServiceImpl heroService;

	@InjectMocks
	private HeroController heroController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(heroController)
				// .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())//
				// testar paginação
				.setViewResolvers((s, locale) -> new MappingJackson2JsonView()) // simular que o retorno sempre vai ser
																				// um json
				.build();
	}
	
	@Test
	void whenGETCalledAndInformedIdIsThenHeroIsReturned() throws Exception {
		// given
		HeroResponseDTO heroResponseDTO = HeroResponseDTOBuilder.builder().build().toHeroResponseDTO();

		// when
		Mockito.when(heroService.findById(Mockito.anyLong())).thenReturn(heroResponseDTO);

		// then
		mockMvc.perform(MockMvcRequestBuilders.get(HERO_API_URL_PATH + "/" + VALID_HERO_ID).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(heroResponseDTO.getName())))
				.andExpect(jsonPath("$.description", is(heroResponseDTO.getDescription())));
	}


	@Test
	void whenGETIsCalledThenAllHereosAreReturned() throws Exception {
		// given
		HeroResponseDTO expectedHero = HeroResponseDTOBuilder.builder().build().toHeroResponseDTO();

		// when
		Mockito.when(heroService.listAll()).thenReturn(Collections.singletonList(expectedHero));

		// then
		mockMvc.perform(MockMvcRequestBuilders.get(HERO_API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is(expectedHero.getName())))
				.andExpect(jsonPath("$[0].description", is(expectedHero.getDescription())));
	}

	@Test
	void whenPOSTIsCalledAndInformedHeroThenHeroIsCreated() throws Exception {
		// given
		HeroResponseDTO heroResponseDTO = HeroResponseDTOBuilder.builder().build().toHeroResponseDTO();

		// when
		Mockito.when(heroService.create(Mockito.any(HeroRequestDTO.class))).thenReturn(heroResponseDTO);

		// then
		mockMvc.perform(MockMvcRequestBuilders.post(HERO_API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonConvertionUtils.asJsonString(heroResponseDTO)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is(heroResponseDTO.getName())))
				.andExpect(jsonPath("$.description", is(heroResponseDTO.getDescription())));
	}
	
	@Test
	void whenDELETEIsCalledAndInformedIdTheHeroThenHeroIsDeletedAndNoContentStatusIsReturned() throws Exception {
		// given
		HeroResponseDTO heroResponseDTO = HeroResponseDTOBuilder.builder().build().toHeroResponseDTO();

		// when
		doNothing().when(heroService).delete(VALID_HERO_ID);

		// then
		mockMvc.perform(MockMvcRequestBuilders.delete(HERO_API_URL_PATH + "/" + VALID_HERO_ID).contentType(MediaType.APPLICATION_JSON)
				.content(JsonConvertionUtils.asJsonString(heroResponseDTO)))
				.andExpect(status().isNoContent());
	}
//	
//	@Test
//	void whenPUTIsCalledAndHeroHeroIdInformedThenHeroAndOkStatusIsReturned() throws Exception {
//		
//		// given
//		HeroRequestDTO heroRequestDTO = HeroRequestDTOBuilder.builder().build().toHeroRequestDTO();
//		Hero expectedHero = heroMapper.toModel(heroRequestDTO);
//		HeroResponseDTO heroResponseDTO = heroMapper.toDTO(expectedHero);
//		
//		// when
//		Mockito.when(heroRespository.findById(expectedHero.getId())).thenReturn(Optional.of(expectedHero));
//		Mockito.when(heroService.update(heroRequestDTO, VALID_HERO_ID)).thenReturn(heroResponseDTO);
//
//		// then
//		mockMvc.perform(MockMvcRequestBuilders.put(HERO_API_URL_PATH + "/" + VALID_HERO_ID)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(JsonConvertionUtils.asJsonString(heroResponseDTO)))
//				.andExpect(status().isOk());
//	}
}
