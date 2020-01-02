package com.fresco.tester;

public class Helpers {

	public static String decodeCaptcha(String captch) {
		String filter = captch.replaceAll("[^0-9\\+]", "");
		int opr1 = Integer.parseInt(String.valueOf(filter.charAt(0)));
		int opr2 = Integer.parseInt(String.valueOf(filter.charAt(2)));
		return String.valueOf(opr1 + opr2);
	}
}
