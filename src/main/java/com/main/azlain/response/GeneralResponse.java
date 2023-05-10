package com.main.azlain.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

public class GeneralResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String status;
	private String message;
	private transient HttpStatus statusCode;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
