package com.main.azlain.exception;

import org.springframework.http.HttpStatus;

import com.main.azlain.common.ErrorCodes;
import com.main.azlain.common.SupportMessages;
import com.main.azlain.response.ErrorResponse;

public class MainAzlainException extends Exception  {
	
	private static final long serialVersionUID = 2977144086681254609L;
	private final ErrorResponse error;
	
	public MainAzlainException(ErrorCodes errorCode, SupportMessages message, HttpStatus httpStatus, String ... customMessage) {
		super(message.getMessage(errorCode.getCodeTitle()) + ". " + message.getMessage(errorCode.getCodeMessage(), customMessage));
		error = new ErrorResponse();
		error.setId(errorCode.getId());
		error.setTitle(message.getMessage(errorCode.getCodeTitle()));
		error.setMessage(message.getMessage(errorCode.getCodeMessage(), customMessage));
		error.setHttpStatus(httpStatus);
	}
	
	public ErrorResponse getError() {
		return error;
	}
}