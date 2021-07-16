package br.com.hereos.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeroResponseDTO {

	private Long id;

	private String name;

	private String heroName;
	
	private String heroIdentification;

	private String description;

	private String birthDate;

	private List<PhoneResponseDTO> phones;

	@Override
	public String toString() {
		return "HeroResponseDTO [id=" + id + ", name=" + name + ", heroName=" + heroName + ", heroIdentification="
				+ heroIdentification + ", description=" + description + ", birthDate=" + birthDate + ", phones="
				+ phones + "]";
	}
	
}
