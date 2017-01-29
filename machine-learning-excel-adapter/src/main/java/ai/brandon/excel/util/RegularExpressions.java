package ai.brandon.excel.util;

import java.util.regex.Pattern;

public class RegularExpressions {

	private static final Pattern sizePattern = Pattern.compile("([1-9]|1[0-9]|2[0-5])");
	private static final Pattern numberPattern = Pattern.compile("^[0-9-_,$\\.\\s]+$");

	public static boolean matchesSize(String source) {
		return sizePattern.matcher(source.trim()).matches();
	}

	public static boolean matchesNumber(String source) {
		return numberPattern.matcher(source.trim()).matches();
	}

}
