package ai.brandon.excel.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

public class WorkbookResolverResult {

	private final Workbook workbook;
	private final List<String> errors = new ArrayList<String>();

	private WorkbookResolverResult(Workbook workbook, List<String> errors) {
		this.workbook = workbook;
		this.errors.addAll(errors);
	}

	public static WorkbookResolverResult accept(Workbook workbook) {
		return new WorkbookResolverResult(workbook, new ArrayList<String>());
	}

	public static WorkbookResolverResult reject(String error) {
		return new WorkbookResolverResult(null, Arrays.asList(error));
	}

	public static WorkbookResolverResult reject(List<String> errors) {
		return new WorkbookResolverResult(null, errors);
	}

	public boolean accepted() {
		return !rejected();
	}

	public boolean rejected() {
		return !getErrors().isEmpty();
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public List<String> getErrors() {
		return errors;
	}

}
