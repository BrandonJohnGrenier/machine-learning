package ai.brandon.excel.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidationResult {

	private final List<String> errors = new ArrayList<String>();

	private ValidationResult(List<String> errors) {
		this.errors.addAll(errors);
	}

	public static ValidationResult accept() {
		return new ValidationResult(new ArrayList<String>());
	}

	public static ValidationResult reject(String error) {
		return new ValidationResult(Arrays.asList(error));
	}

	public static ValidationResult reject(List<String> errors) {
		return new ValidationResult(errors);
	}

	public boolean accepted() {
		return !rejected();
	}

	public boolean rejected() {
		return !getErrors().isEmpty();
	}

	public List<String> getErrors() {
		return errors != null ? errors : new ArrayList<>();
	}

}
