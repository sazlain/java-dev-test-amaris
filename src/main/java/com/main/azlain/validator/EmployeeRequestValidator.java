package com.main.azlain.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.main.azlain.common.ErrorCodes;
import com.main.azlain.common.SupportMessages;
import com.main.azlain.exception.MainAzlainException;

@Component
public class EmployeeRequestValidator {
	
	private static final String NUMBERS_PATTERN = "\\d+";

	@Autowired
	SupportMessages messages;

	public boolean validate(String id) throws MainAzlainException {

		if (id == null || id.isEmpty()) {
			throw new MainAzlainException(ErrorCodes.ERR003_REQUIRED_BAD_REQUEST_ERROR, messages,
					HttpStatus.BAD_REQUEST, "Id");
		}
		
		Pattern regex = Pattern.compile(NUMBERS_PATTERN);

		if (!regex.matcher(id).matches()) {
			throw new MainAzlainException(ErrorCodes.ERR004_FIELD_FORMAT_BAD_REQUEST_ERROR, messages,
					HttpStatus.BAD_REQUEST, "Id", "Must be only numbers");
		}

		return true;

	}
}
