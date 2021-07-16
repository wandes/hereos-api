package br.com.hereos.mapper;

import br.com.hereos.dto.HeroDTO;
import br.com.hereos.dto.PhoneDTO;
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
    date = "2021-07-15T21:32:17-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class HeroMapperImpl implements HeroMapper {

    @Override
    public Hero toModel(HeroDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Hero hero = new Hero();

        if ( dto.getBirthDate() != null ) {
            hero.setBirthDate( LocalDate.parse( dto.getBirthDate(), DateTimeFormatter.ofPattern( "dd-MM-yyyy" ) ) );
        }
        hero.setId( dto.getId() );
        hero.setName( dto.getName() );
        hero.setHeroName( dto.getHeroName() );
        hero.setHeroIdentification( dto.getHeroIdentification() );
        hero.setDescription( dto.getDescription() );
        hero.setPhones( phoneDTOListToPhoneList( dto.getPhones() ) );

        return hero;
    }

    @Override
    public HeroDTO toDTO(Hero dto) {
        if ( dto == null ) {
            return null;
        }

        HeroDTO heroDTO = new HeroDTO();

        heroDTO.setId( dto.getId() );
        heroDTO.setName( dto.getName() );
        heroDTO.setHeroName( dto.getHeroName() );
        heroDTO.setHeroIdentification( dto.getHeroIdentification() );
        heroDTO.setDescription( dto.getDescription() );
        if ( dto.getBirthDate() != null ) {
            heroDTO.setBirthDate( DateTimeFormatter.ISO_LOCAL_DATE.format( dto.getBirthDate() ) );
        }
        heroDTO.setPhones( phoneListToPhoneDTOList( dto.getPhones() ) );

        return heroDTO;
    }

    protected Phone phoneDTOToPhone(PhoneDTO phoneDTO) {
        if ( phoneDTO == null ) {
            return null;
        }

        Phone phone = new Phone();

        phone.setId( phoneDTO.getId() );
        phone.setType( phoneDTO.getType() );
        phone.setNumber( phoneDTO.getNumber() );

        return phone;
    }

    protected List<Phone> phoneDTOListToPhoneList(List<PhoneDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Phone> list1 = new ArrayList<Phone>( list.size() );
        for ( PhoneDTO phoneDTO : list ) {
            list1.add( phoneDTOToPhone( phoneDTO ) );
        }

        return list1;
    }

    protected PhoneDTO phoneToPhoneDTO(Phone phone) {
        if ( phone == null ) {
            return null;
        }

        PhoneDTO phoneDTO = new PhoneDTO();

        phoneDTO.setId( phone.getId() );
        phoneDTO.setType( phone.getType() );
        phoneDTO.setNumber( phone.getNumber() );

        return phoneDTO;
    }

    protected List<PhoneDTO> phoneListToPhoneDTOList(List<Phone> list) {
        if ( list == null ) {
            return null;
        }

        List<PhoneDTO> list1 = new ArrayList<PhoneDTO>( list.size() );
        for ( Phone phone : list ) {
            list1.add( phoneToPhoneDTO( phone ) );
        }

        return list1;
    }
}
