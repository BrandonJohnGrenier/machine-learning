package ai.brandon.commons;

public class JsonParsingException extends RuntimeException {

	private static final long serialVersionUID = -7877741986269575099L;

	public JsonParsingException(Exception e) {
		super(e);
	}

}
