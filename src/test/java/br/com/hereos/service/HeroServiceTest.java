//package br.com.hereos.service;
//
////import static br.com.hereos.utils.HeroUtils.createMockDto;
////import static br.com.hereos.utils.HeroUtils.createMockModel;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import br.com.hereos.dto.mapper.HeroMapper;
//import br.com.hereos.dto.request.HeroRequestDTO;
//import br.com.hereos.model.Hero;
//import br.com.hereos.repository.HeroRepository;
//import br.com.hereos.service.impl.HeroServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//public class HeroServiceTest {
//	
//	@Mock
//    private HeroRepository heroRepository;
//
//    @Mock
//    private HeroMapper heroMapper;
//
//    @InjectMocks
//    private HeroServiceImpl heroService;
//    
////	@Test
////	void testCreateHero() {
////		
////		HeroRequestDTO heroDTO = createMockDto();
////		
////        Hero expectedSavedHero = createMockModel();
////
////        when(heroMapper.toModel(heroDTO)).thenReturn(expectedSavedHero);
////        when(heroRepository.save(expectedSavedHero)).thenReturn(expectedSavedHero);
////
////        Hero response = heroService.createHero(heroDTO);
////
////        assertEquals(response.getHeroName(), expectedSavedHero.getHeroName());
////	}
//
//	@Test
//	void testListAll() {
//		
//	}
//
//	@Test
//	void testFindById() {
//		
//	}
//
//	@Test
//	void testDelete() {
//		
//	}
//
//	@Test
//	void testUpdate() {
//		
//	}
//
//}
