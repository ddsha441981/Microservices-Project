package com.cwc.user.service;

public enum Gender {
	MALE, FEMALE;

	public static Gender getByName(String genderType) {
		try {
            return valueOf(genderType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
	}


}
