package br.com.hereos.dto.mapper;

import br.com.hereos.dto.request.HeroRequestDTO;
import br.com.hereos.dto.request.PhoneResquestDTO;
import br.com.hereos.dto.response.HeroResponseDTO;
import br.com.hereos.dto.response.HeroResponseDTO.HeroResponseDTOBuilder;
import br.com.hereos.dto.response.PhoneResponseDTO;
import br.com.hereos.dto.response.PhoneResponseDTO.PhoneResponseDTOBuilder;
import br.com.hereos.model.Hero;
import br.com.hereos.model.Hero.HeroBuilder;
import br.com.hereos.model.Phone;
import br.com.hereos.model.Phone.PhoneBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-01T17:23:15-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.3.1300.v20210419-1022, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class HeroMapperImpl implements HeroMapper {

    @Override
    public Hero toModel(HeroRequestDTO heroRequestDTO) {
        if ( heroRequestDTO == null ) {
            return null;
        }

        HeroBuilder hero = Hero.builder();

        if ( heroRequestDTO.getBirthDate() != null ) {
            hero.birthDate( LocalDate.parse( heroRequestDTO.getBirthDate(), DateTimeFormatter.ofPattern( "dd-MM-yyyy" ) ) );
        }
        hero.description( heroRequestDTO.getDescription() );
        hero.heroIdentification( heroRequestDTO.getHeroIdentification() );
        hero.heroName( heroRequestDTO.getHeroName() );
        hero.id( heroRequestDTO.getId() );
        hero.name( heroRequestDTO.getName() );
        hero.phones( phoneResquestDTOListToPhoneList( heroRequestDTO.getPhones() ) );

        return hero.build();
    }

    @Override
    public HeroResponseDTO toDTO(Hero heroDTO) {
        if ( heroDTO == null ) {
            return null;
        }

        HeroResponseDTOBuilder heroResponseDTO = HeroResponseDTO.builder();

        if ( heroDTO.getBirthDate() != null ) {
            heroResponseDTO.birthDate( DateTimeFormatter.ofPattern( "dd-MM-yyyy" ).format( heroDTO.getBirthDate() ) );
        }
        heroResponseDTO.description( heroDTO.getDescription() );
        heroResponseDTO.heroIdentification( heroDTO.getHeroIdentification() );
        heroResponseDTO.heroName( heroDTO.getHeroName() );
        heroResponseDTO.id( heroDTO.getId() );
        heroResponseDTO.name( heroDTO.getName() );
        heroResponseDTO.phones( phoneListToPhoneResponseDTOList( heroDTO.getPhones() ) );

        return heroResponseDTO.build();
    }

    protected Phone phoneResquestDTOToPhone(PhoneResquestDTO phoneResquestDTO) {
        if ( phoneResquestDTO == null ) {
            return null;
        }

        PhoneBuilder phone = Phone.builder();

        phone.id( phoneResquestDTO.getId() );
        phone.number( phoneResquestDTO.getNumber() );
        phone.type( phoneResquestDTO.getType() );

        return phone.build();
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

        PhoneResponseDTOBuilder phoneResponseDTO = PhoneResponseDTO.builder();

        phoneResponseDTO.id( phone.getId() );
        phoneResponseDTO.number( phone.getNumber() );
        phoneResponseDTO.type( phone.getType() );

        return phoneResponseDTO.build();
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
