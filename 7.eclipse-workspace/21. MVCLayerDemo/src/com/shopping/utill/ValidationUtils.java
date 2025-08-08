package com.shopping.utill;

public class ValidationUtils {

	/*
	 * 문자열(str) 빈 값 체크 (null )
	 */
	public static void requireNonEmpty(String str, String message) {
		if(str == null || str.trim().isEmpty()) {
			throw new IllegalArgumentException(message);
		}
		
	}

	public static void requireMinLength(String str, int minLength, String message) {
		
		requireNonNull(str, message);
		if(str.length() < minLength) {
			throw new IllegalArgumentException(message);
		}
		
	}

	//null 체크
	private static void requireNonNull(Object obj, String message) {
		if(obj == null) {
			throw new IllegalArgumentException(message);
		}
		
	}

}
