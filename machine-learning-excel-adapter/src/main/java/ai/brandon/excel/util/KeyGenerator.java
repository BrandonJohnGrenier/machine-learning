package ai.brandon.excel.util;

import org.apache.commons.lang3.RandomStringUtils;

public final class KeyGenerator {

	private KeyGenerator() {

	}

	public static String generateKey() {
		return RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomAlphanumeric(10).toUpperCase();
	}

}
