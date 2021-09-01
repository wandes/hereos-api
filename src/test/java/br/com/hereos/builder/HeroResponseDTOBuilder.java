package br.com.hereos.builder;

import java.util.Arrays;
import java.util.List;

import br.com.hereos.dto.response.HeroResponseDTO;
import br.com.hereos.dto.response.PhoneResponseDTO;
import br.com.hereos.enums.PhoneType;
import lombok.Builder;

@Builder
public class HeroResponseDTOBuilder {

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

	public HeroResponseDTO toHeroResponseDTO() {
		
		PhoneResponseDTO phones = new PhoneResponseDTO(1L, PhoneType.MOBILE , "232323232323232");
		
		List<PhoneResponseDTO> listPhones = Arrays.asList(phones);
		
		return new HeroResponseDTO(id, name, heroName, heroIdentification, description, birthDate, listPhones);
	}

}
