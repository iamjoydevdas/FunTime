package com.example.shared.util;

import java.lang.reflect.Field;

public class CommonUtilTest {
	public static void setPrivateField(Object target, String fieldName, Object value){
		try{
			Field privateField = target.getClass().getDeclaredField(fieldName);
			privateField.setAccessible(true);
			privateField.set(target, value);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
