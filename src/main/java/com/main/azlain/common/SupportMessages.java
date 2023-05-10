package com.main.azlain.common;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class SupportMessages {

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String message, String [] parameters) {
	    Locale locale = new Locale("es-CO");
		return this.messageSource.getMessage(message, parameters, locale);
	}

	public String getMessage(String message) {
	    Locale locale = new Locale("es-CO");
		return this.messageSource.getMessage(message, null, locale);
	}

}
