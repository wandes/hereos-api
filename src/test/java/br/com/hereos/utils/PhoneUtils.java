package br.com.hereos.utils;

import br.com.hereos.dto.PhoneDTO;
import br.com.hereos.enums.PhoneType;
import br.com.hereos.model.Phone;

public class PhoneUtils {
	 private static final String PHONE_NUMBER = "(11)999999999";
	    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
	    private static final long PHONE_ID = 1L;

	    public static PhoneDTO createFakeDTO() {
	        return PhoneDTO.builder()
	                .number(PHONE_NUMBER)
	                .type(PHONE_TYPE)
	                .build();
	    }

	    public static Phone createFakeEntity() {
	        return Phone.builder()
	                .id(PHONE_ID)
	                .number(PHONE_NUMBER)
	                .type(PHONE_TYPE)
	                .build();
	    }
}
