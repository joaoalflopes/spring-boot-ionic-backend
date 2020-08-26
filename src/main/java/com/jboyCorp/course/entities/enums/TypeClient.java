package com.jboyCorp.course.entities.enums;

public enum TypeClient {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int code;
	private String description;
	
	
	private TypeClient(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static TypeClient valueOf(int code) {
		for(TypeClient value : TypeClient.values()) {
			if(value.getCode() == code ) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid TypeClient code");
		
	}
}
