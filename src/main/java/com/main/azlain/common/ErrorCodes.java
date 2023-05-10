package com.main.azlain.common;

public enum ErrorCodes {
	ERR001_INTERNAL_ERROR("ERR001", "general.error.title", "general.error.message"),
	ERR002_EXTERNAL_CLIENT_ACCESS_ERROR("ERR002", "external.client.access.error.title", "external.client.access.error.message"),
	ERR003_REQUIRED_BAD_REQUEST_ERROR("ERR003", "required.field.bad.request.error.title", "required.field.bad.request.error.message"),
	ERR004_FIELD_FORMAT_BAD_REQUEST_ERROR("ERR004", "required.field.format.bad.request.error.title", "required.field.format.bad.request.error.message");
	
	String id;
	String codeTitle;
	String codeMessage;

	private ErrorCodes(String id, String codeTitle, String codeMessage) {
		this.id = id;
		this.codeTitle = codeTitle;
		this.codeMessage = codeMessage;
	}
	
	public static ErrorCodes getEnum(String id) {
		for (ErrorCodes codigo : ErrorCodes.values()) {
			if (codigo.getId().equals(id)) {
				return codigo;
			}
		}
		return null;
	}

	public String getId() {
		return id;
	}

	public String getCodeMessage() {
		return codeMessage;
	}

	public String getCodeTitle() {
		return codeTitle;
	}
}
