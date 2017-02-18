package ai.brandon.excel.util;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import ai.brandon.excel.model.WorkbookResolverResult;

public class ExcelWorkbookResolver {

	public ExcelWorkbookResolver() {

	}

	public static WorkbookResolverResult open(String filePath) {
		if (isBlank(filePath)) {
			return WorkbookResolverResult.reject("Unable to read file, the file path is null or empty.");
		}
		return open(new File(filePath));
	}

	public static WorkbookResolverResult open(File file) {
		if (file == null) {
			return WorkbookResolverResult.reject("Unable to read file, the file is null.");
		}
		if (!file.canRead()) {
			return WorkbookResolverResult.reject("Unable to read file, the file " + file.getAbsolutePath() + " is not readable.");
		}
		if (!file.isFile()) {
			return WorkbookResolverResult.reject("Unable to read file, the file " + file.getAbsolutePath() + " is not actually a file.");
		}
		if (!isExcelFile(file)) {
			return WorkbookResolverResult.reject("The file " + file.getName() + " does not seem to be an Excel file - should have a .xls or .xlsx file extension.");
		}

		WorkbookOrError workbook = getWorkbook(file);
		if (!workbook.hasWorkbook()) {
			return WorkbookResolverResult.reject(workbook.getErrorMessage());
		}

		Integer numberOfSheets = workbook.getWorkbook().getNumberOfSheets();
		if (numberOfSheets <= 0) {
			return WorkbookResolverResult.reject(Arrays.asList("Unable to find any worksheets in this spreadsheet."));
		}

		return WorkbookResolverResult.accept(workbook.getWorkbook());
	}

	public static WorkbookResolverResult open(InputStream stream) {
		if (stream == null) {
			return WorkbookResolverResult.reject("Unable to read file, input stream is null.");
		}

		WorkbookOrError workbook = getWorkbook(stream);
		if (!workbook.hasWorkbook()) {
			return WorkbookResolverResult.reject(workbook.getErrorMessage());
		}

		return WorkbookResolverResult.accept(workbook.getWorkbook());
	}

	private static boolean isExcelFile(File file) {
		if (file == null || file.getName() == null) {
			return false;
		}
		return AdapterUtilities.isExcelFile(file.getName());
	}

	private static WorkbookOrError getWorkbook(File file) {
		try {
			Workbook workbook = WorkbookFactory.create(file);
			WorkbookOrError result = new WorkbookOrError();
			result.setWorkbook(workbook);
			return result;

		}
		catch (Exception e) {
			WorkbookOrError result = new WorkbookOrError();
			result.setErrorMessage(e.getMessage());
			return result;
		}
	}

	private static WorkbookOrError getWorkbook(InputStream stream) {
		try {
			Workbook workbook = WorkbookFactory.create(stream);
			WorkbookOrError result = new WorkbookOrError();
			result.setWorkbook(workbook);
			return result;

		}
		catch (Exception e) {
			WorkbookOrError result = new WorkbookOrError();
			result.setErrorMessage(e.getMessage());
			return result;
		}
	}

	private static class WorkbookOrError {

		private Workbook workbook;
		private String errorMessage;

		public Workbook getWorkbook() {
			return workbook;
		}

		public void setWorkbook(Workbook workbook) {
			this.workbook = workbook;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public boolean hasWorkbook() {
			return workbook != null;
		}

	}

}
