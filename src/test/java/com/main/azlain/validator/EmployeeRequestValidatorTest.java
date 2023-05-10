package com.main.azlain.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.main.azlain.AzlainDevTestApplication;
import com.main.azlain.common.ErrorCodes;
import com.main.azlain.common.SupportMessages;
import com.main.azlain.exception.MainAzlainException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AzlainDevTestApplication.class)
class EmployeeRequestValidatorTest {

	@Mock
	EmployeeRequestValidator employeeRequestValidator;
	
	@Mock
	SupportMessages messages;
	
	@Test
	void requiredField() throws MainAzlainException {
		doThrow(new MainAzlainException(ErrorCodes.ERR003_REQUIRED_BAD_REQUEST_ERROR, messages, HttpStatus.BAD_REQUEST, "Id")).when(employeeRequestValidator).validate("");
		MainAzlainException ex = assertThrows(MainAzlainException.class, ()-> {
			employeeRequestValidator.validate("");
		});
		
		assertEquals(ErrorCodes.ERR003_REQUIRED_BAD_REQUEST_ERROR.getId(), ex.getError().getId());
	}
	
	@Test
	void invalidFormatField() throws MainAzlainException {
		doThrow(new MainAzlainException(ErrorCodes.ERR004_FIELD_FORMAT_BAD_REQUEST_ERROR, messages, HttpStatus.BAD_REQUEST, "Id")).when(employeeRequestValidator).validate("abc");
		MainAzlainException ex = assertThrows(MainAzlainException.class, ()-> {
			employeeRequestValidator.validate("abc");
		});
		
		assertEquals(ErrorCodes.ERR004_FIELD_FORMAT_BAD_REQUEST_ERROR.getId(), ex.getError().getId());
	}

}
