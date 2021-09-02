package br.com.hereos.dto.mapper;

import br.com.hereos.dto.request.HeroRequestDTO;
import br.com.hereos.dto.request.PhoneResquestDTO;
import br.com.hereos.dto.response.HeroResponseDTO;
import br.com.hereos.dto.response.PhoneResponseDTO;
import br.com.hereos.model.Hero;
import br.com.hereos.model.Phone;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-02T20:41:51-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class HeroMapperImpl implements HeroMapper {

    @Override
    public Hero toModel(HeroRequestDTO heroRequestDTO) {
        if ( heroRequestDTO == null ) {
            return null;
        }

        Hero hero = new Hero();

        if ( heroRequestDTO.getBirthDate() != null ) {
            hero.setBirthDate( LocalDate.parse( heroRequestDTO.getBirthDate(), DateTimeFormatter.ofPattern( "dd-MM-yyyy" ) ) );
        }
        hero.setId( heroRequestDTO.getId() );
        hero.setName( heroRequestDTO.getName() );
        hero.setHeroName( heroRequestDTO.getHeroName() );
        hero.setHeroIdentification( heroRequestDTO.getHeroIdentification() );
        hero.setDescription( heroRequestDTO.getDescription() );
        hero.setPhones( phoneResquestDTOListToPhoneList( heroRequestDTO.getPhones() ) );

        return hero;
    }

    @Override
    public HeroResponseDTO toDTO(Hero heroDTO) {
        if ( heroDTO == null ) {
            return null;
        }

        HeroResponseDTO heroResponseDTO = new HeroResponseDTO();

        if ( heroDTO.getBirthDate() != null ) {
            heroResponseDTO.setBirthDate( DateTimeFormatter.ofPattern( "dd-MM-yyyy" ).format( heroDTO.getBirthDate() ) );
        }
        heroResponseDTO.setId( heroDTO.getId() );
        heroResponseDTO.setName( heroDTO.getName() );
        heroResponseDTO.setHeroName( heroDTO.getHeroName() );
        heroResponseDTO.setHeroIdentification( heroDTO.getHeroIdentification() );
        heroResponseDTO.setDescription( heroDTO.getDescription() );
        heroResponseDTO.setPhones( phoneListToPhoneResponseDTOList( heroDTO.getPhones() ) );

        return heroResponseDTO;
    }

    protected Phone phoneResquestDTOToPhone(PhoneResquestDTO phoneResquestDTO) {
        if ( phoneResquestDTO == null ) {
            return null;
        }

        Phone phone = new Phone();

        phone.setId( phoneResquestDTO.getId() );
        phone.setType( phoneResquestDTO.getType() );
        phone.setNumber( phoneResquestDTO.getNumber() );

        return phone;
    }

    protected List<Phone> phoneResquestDTOListToPhoneList(List<PhoneResquestDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Phone> list1 = new ArrayList<Phone>( list.size() );
        for ( PhoneResquestDTO phoneResquestDTO : list ) {
            list1.add( phoneResquestDTOToPhone( phoneResquestDTO ) );
        }

        return list1;
    }

    protected PhoneResponseDTO phoneToPhoneResponseDTO(Phone phone) {
        if ( phone == null ) {
            return null;
        }

        PhoneResponseDTO phoneResponseDTO = new PhoneResponseDTO();

        phoneResponseDTO.setId( phone.getId() );
        phoneResponseDTO.setType( phone.getType() );
        phoneResponseDTO.setNumber( phone.getNumber() );

        return phoneResponseDTO;
    }

    protected List<PhoneResponseDTO> phoneListToPhoneResponseDTOList(List<Phone> list) {
        if ( list == null ) {
            return null;
        }

        List<PhoneResponseDTO> list1 = new ArrayList<PhoneResponseDTO>( list.size() );
        for ( Phone phone : list ) {
            list1.add( phoneToPhoneResponseDTO( phone ) );
        }

        return list1;
    }
}
