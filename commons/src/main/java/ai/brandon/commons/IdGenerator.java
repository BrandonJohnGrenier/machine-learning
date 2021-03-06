package ai.brandon.commons;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;

public final class IdGenerator {

	private static SecureRandom random = new SecureRandom();

	private IdGenerator() {

	}

	public static String generateId(String prefix) {
		return isBlank(prefix) ? generateId() : prefix + "_" + generateId();
	}

	public static String generateId() {
		return generateId(16);
	}

	public static String generateId(Integer length) {
		String secure = RandomStringUtils.random((length - 1), 0, 0, true, true, null, random);
		return RandomStringUtils.randomAlphabetic(1).toLowerCase() + secure.toLowerCase();
	}

}
