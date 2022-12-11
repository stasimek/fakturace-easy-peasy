package cz.stasimek.fakturaceeasypeasy.util;

import org.apache.commons.lang3.StringUtils;


public class AppStringUtils {

	private AppStringUtils() {
	}

	public static String camelCaseToWords(String text) {
		if (StringUtils.isEmpty(text)) {
			return text;
		}
		String firstLetter = text.substring(0, 1).toLowerCase();
		String restLetters = text.substring(1).replaceAll("([A-Z])", " $1").toLowerCase();
		return firstLetter + restLetters;
	}
}
