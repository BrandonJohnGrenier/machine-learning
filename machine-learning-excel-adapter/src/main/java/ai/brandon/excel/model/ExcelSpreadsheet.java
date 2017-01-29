package ai.brandon.excel.model;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import ai.brandon.excel.util.ExcelWorkbookResolver;

public class ExcelSpreadsheet {

	private final WorkbookResolverResult result;
	private final Workbook workbook;
	private final List<ExcelWorksheet> sheets;

	public ExcelSpreadsheet(File file) {
		this(ExcelWorkbookResolver.open(file));
	}

	public ExcelSpreadsheet(InputStream stream) {
		this(ExcelWorkbookResolver.open(stream));
	}

	public ExcelSpreadsheet(String filepath) {
		this(ExcelWorkbookResolver.open(filepath));
	}

	private ExcelSpreadsheet(WorkbookResolverResult result) {
		this.result = result;
		this.workbook = result.accepted() ? result.getWorkbook() : null;
		this.sheets = result.accepted() ? loadWorksheets(workbook) : new ArrayList<ExcelWorksheet>();
	}

	public boolean isValid() {
		return result.accepted();
	}

	public List<String> getErrors() {
		return result.getErrors();
	}

	public List<ExcelWorksheet> getWorksheets() {
		return sheets;
	}

	private List<ExcelWorksheet> loadWorksheets(Workbook workbook) {
		List<ExcelWorksheet> sheets = new ArrayList<ExcelWorksheet>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheets.add(new ExcelWorksheet(workbook.getSheetAt(i)));
		}
		return sheets;
	}

}
