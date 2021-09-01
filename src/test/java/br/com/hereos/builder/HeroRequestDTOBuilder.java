package br.com.hereos.builder;

import java.util.Arrays;
import java.util.List;

import br.com.hereos.dto.request.HeroRequestDTO;
import br.com.hereos.dto.request.PhoneResquestDTO;
import br.com.hereos.enums.PhoneType;
import lombok.Builder;

@Builder
public class HeroRequestDTOBuilder{

	@Builder.Default
	private Long id = 1l;

	@Builder.Default
	private String name = "Midoriya Izuku";

	@Builder.Default
	private String heroName = "Deku";

	@Builder.Default
	private String heroIdentification = "1";

	@Builder.Default
	private String description = "futuro numero 1";

	@Builder.Default
	private String birthDate = "15-07-2006";

	public HeroRequestDTO toHeroRequestDTO() {
		
		PhoneResquestDTO phones = new PhoneResquestDTO(1L, PhoneType.MOBILE , "232323232323232");
		
		List<PhoneResquestDTO> listPhones = Arrays.asList(phones);
		
		return new HeroRequestDTO(id, name, heroName, heroIdentification, description, birthDate,listPhones);
	}

}
