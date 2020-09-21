package com.jboyCorp.course.entities.enums;

public enum UserProfile {
	
	ADMIN(1, "ROLE_ADMIN"),
	USER_ONE(2, "ROLE_USER1");
	
	private int code;
	private String description;
	
	
	private UserProfile(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static UserProfile valueOf(int code) {
		for(UserProfile value : UserProfile.values()) {
			if(value.getCode() == code ) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid UserProfile code");
		
	}
}
