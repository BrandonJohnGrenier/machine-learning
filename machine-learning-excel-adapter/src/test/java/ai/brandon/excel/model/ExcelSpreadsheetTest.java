package ai.brandon.excel.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

import ai.brandon.excel.AdapterTest;
import ai.brandon.excel.model.ExcelSpreadsheet;

public class ExcelSpreadsheetTest extends AdapterTest {

	private ExcelSpreadsheet spreadsheet;

	@Test
	public void shouldBeAValidSpreadsheetWhenAtLeastOneWorksheetContainsValidNodeData() {
		this.spreadsheet = new ExcelSpreadsheet(path("valid-annotated.xlsx"));
		assertThat(spreadsheet.isValid()).isTrue();
		assertThat(spreadsheet.getWorksheets()).hasSize(1);
		assertThat(spreadsheet.getWorksheets().get(0).containsNodeData()).isTrue();
	}

	@Test
	public void shouldBeAValidSpreadsheetWhenAtLeastOneWorksheetContainsValidRelationshipData() {
		this.spreadsheet = new ExcelSpreadsheet(path("links.xls"));
		assertThat(spreadsheet.isValid()).isTrue();
		assertThat(spreadsheet.getWorksheets()).hasSize(1);
		assertThat(spreadsheet.getWorksheets().get(0).containsRelationshipData()).isTrue();
	}

	@Test
	public void shouldBeAbleToResolveAllValidWorksheetsInTheSpreadSheet() {
		this.spreadsheet = new ExcelSpreadsheet(path("valid-multisheet-annotated.xlsx"));
		assertThat(spreadsheet.isValid()).isTrue();
		assertThat(spreadsheet.getWorksheets()).hasSize(3);
	}

	@Test
	public void shouldBeAnInvalidSpreadsheetWhenAnErrorOccursWhileAttemptingToOpenAnExcelSpreadsheet() {
		this.spreadsheet = new ExcelSpreadsheet(path("not-a-real-file.xlsx"));
		assertThat(spreadsheet.isValid()).isFalse();
		assertThat(spreadsheet.getErrors()).hasSize(1);
	}

}
