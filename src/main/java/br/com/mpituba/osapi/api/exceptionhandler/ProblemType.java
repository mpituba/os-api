package br.com.mpituba.osapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	INVALID_DATA("/invalid-data", "Invalid Data"),
	SYSTEM_ERROR("/system-error", "System error"),
	INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
	INVALID_MESSAGE("/invalid-message", "Invalid message"),
	RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),
	ENTITY_IN_USE("/entity-in-use", "Entity in use"),
	BUSINESS_RULE_VIOLATION("/business-rule-violation", "Business rule violation"),
	PROPERTY_NOT_FOUND("/property-not-found", "Property not found");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://os.mpituba.com.br" + path;
		this.title = title;
	}
	
}
