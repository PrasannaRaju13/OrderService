package com.order.service.dto;


import java.util.Objects;

public class CommonUtils {
	public static String checkStrNotNull(String arg) {
		return Objects.nonNull(arg) ? arg :"";
	}
	public static int checkIntNotNull(int arg) {
		return Objects.nonNull(arg) ? arg :0;
	}
	public static String SUCCESS ="SUCCESS";
	public static String FAIL ="FAIL";
}
