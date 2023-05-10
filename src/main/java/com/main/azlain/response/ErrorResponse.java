package com.main.azlain.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = -1187310565357890122L;
	private String id;
	private String title;
	private String message;
	private String data;
	private transient HttpStatus httpStatus; 
	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String id, String title, String message, String data, HttpStatus httpStatus) {
		super();
		this.id = id;
		this.title = title;
		this.message = message;
		this.data = data;
		this.httpStatus = httpStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
