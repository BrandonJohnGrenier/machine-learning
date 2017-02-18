package ai.brandon.commons;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
public final class JSON {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}

	public static JsonNode read(String input) {
		try {
			return objectMapper.readTree(input);
		}
		catch (Exception e) {
			throw new JsonParsingException(e);
		}
	}

	public static <T> T objectify(JsonNode parser, Class<T> type) {
		try {
			return objectMapper.readValue(parser.traverse(), type);
		}
		catch (Exception e) {
			throw new JsonParsingException(e);
		}
	}

	public static <T> T objectify(String source, Class<T> type) {
		try {
			return objectMapper.readValue(source, type);
		}
		catch (Exception e) {
			throw new JsonParsingException(e);
		}
	}

	public static Map<String, Object> map(Object object) {
		try {
			return objectMapper.convertValue(object, Map.class);
		}
		catch (Exception e) {
			throw new JsonParsingException(e);
		}
	}

	public static String stringify(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		}
		catch (Exception e) {
			throw new JsonParsingException(e);
		}
	}

}
