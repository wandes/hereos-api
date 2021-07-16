package br.com.hereos.utils;

import java.time.LocalDate;
import java.util.Collections;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.hereos.dto.HeroDTO;
import br.com.hereos.model.Hero;

public class HeroUtils {

	private static final Long HERO_ID = 1L;

	private static final String NAME = "Toshinori Yagi";

	private static final String HERO_NAME = "ALL MIGHT";

	private static final String HERO_IDENTIFICATION = "1";

	private static final String DESCRIPTION = "HEROI NUMERO 1";

	private static final LocalDate BIRTH_DATE = LocalDate.of(1971, 06, 10);
	
	public static Hero createMockModel() {
		return Hero.builder()
				.id(HERO_ID)
				.name(NAME)
				.heroName(HERO_NAME)
				.heroIdentification(HERO_IDENTIFICATION)
				.description(DESCRIPTION)
				.birthDate(BIRTH_DATE)
				.phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
				.build();
	}
	
	public static HeroDTO createMockDto() {
		return HeroDTO.builder()
				.id(HERO_ID)
				.name(NAME)
				.heroName(HERO_NAME)
				.heroIdentification(HERO_IDENTIFICATION)
				.description(DESCRIPTION)
				.birthDate("10-06-1971")
				.phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
				.build();
	}
	
	public static String asJsonString(HeroDTO heroDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(heroDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
