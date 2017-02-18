package ai.brandon.excel.model;

import static ai.brandon.excel.ExcelAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

import ai.brandon.excel.AdapterTest;
import ai.brandon.excel.model.ExcelSpreadsheet;
import ai.brandon.excel.model.ExcelWorksheet;

public class ExcelWorksheetTest extends AdapterTest {

	private ExcelSpreadsheet spreadsheet;

	@Test
	public void shouldRejectTheSpreadsheetWhenThereAreNoRowsOrColumns() {
		this.spreadsheet = new ExcelSpreadsheet(path("empty.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet.isValid()).isFalse();
		assertThat(worksheet.getErrors()).contains("Unable to find any data in the worksheet.");
	}

	@Test
	public void shouldRejectTheSpreadsheetWhenTheHeaderIsMissingColumnData() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-first-header.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet.isValid()).isFalse();
		assertThat(worksheet.getErrors()).contains("The worksheet named Sheet1 does not have all header columns populated - column 0 is empty. The header row (i.e. the first row of your worksheet) must have a value in each column.");
	}

	@Test
	public void shouldRejectTheSpreadsheetWhenTheHeaderContainsDuplicateColumnHeaders() {
		this.spreadsheet = new ExcelSpreadsheet(path("duplicate-column-headers.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet.isValid()).isFalse();
		assertThat(worksheet.getErrors()).contains("There are 2 columns named Second Column in this worksheet. All column names must be unique.");
		assertThat(worksheet.getErrors()).contains("There are 2 columns named Third Column in this worksheet. All column names must be unique.");
	}
	
	@Test
	public void shouldRejectTheSpreadsheetWhenAMetaModelRowExistsAndTheSpreadsheetHasNoDataToProcess() {
		this.spreadsheet = new ExcelSpreadsheet(path("no-data.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);
		
		assertThat(worksheet.hasMetaModel()).isTrue();
		assertThat(worksheet.isValid()).isFalse();
		assertThat(worksheet.getErrors()).contains("The worksheet named Sheet1 has no data to process.");
	}

	@Test
	public void shouldRejectTheSpreadsheetWhenTheMetaModelRowDoesNotExistAndThereIsNoDataToProcess() {
		this.spreadsheet = new ExcelSpreadsheet(path("only-header.xlsx"));
		
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);
		assertThat(worksheet.hasMetaModel()).isFalse();
		assertThat(worksheet.isValid()).isFalse();
		assertThat(worksheet.getErrors()).contains("The worksheet named Sheet1 has no data to process.");
	}

	@Test
	public void shouldReturnTheCorrectRowAndColumnIndexCounts() {
		this.spreadsheet = new ExcelSpreadsheet(path("simple.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet.getFirstColumnIndex()).isEqualTo(0);
		assertThat(worksheet.getLastColumnIndex()).isEqualTo(4);
		assertThat(worksheet.getColumnCount()).isEqualTo(5);

		assertThat(worksheet.getFirstRowIndex()).isEqualTo(0);
		assertThat(worksheet.getLastRowIndex()).isEqualTo(2);
		assertThat(worksheet.getRowCount()).isEqualTo(3);
	}

	@Test
	public void shouldReturnTheCorrectCellContentForEachRowAndColumn() {
		this.spreadsheet = new ExcelSpreadsheet(path("simple.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet).hasRows(3);
		assertThat(worksheet).eachRowHasThisManyColumns(5);

		assertThat(worksheet).rowHasThisContent(0, "First Column", "Second Column", "Third Column", "Fourth Column", "Fifth Column");
		assertThat(worksheet).rowHasThisContent(1, "Jeff", "Smith", "jeff@place.com", "22", "today");
		assertThat(worksheet).rowHasThisContent(2, "Mandy", "Mathers", "mandy@place.com", "34", "yesterday");

		assertThat(worksheet).columnHasThisContent(0, "First Column", "Jeff", "Mandy");
		assertThat(worksheet).columnHasThisContent(1, "Second Column", "Smith", "Mathers");
		assertThat(worksheet).columnHasThisContent(2, "Third Column", "jeff@place.com", "mandy@place.com");
		assertThat(worksheet).columnHasThisContent(3, "Fourth Column", "22", "34");
		assertThat(worksheet).columnHasThisContent(4, "Fifth Column", "today", "yesterday");
	}

	@Test
	public void shouldReturnTheCorrectNumberOfRowsWhenTheFirstTwoRowsAreBlank() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-first-two-rows.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet.getFirstRowIndex()).isEqualTo(2);
		assertThat(worksheet.getLastRowIndex()).isEqualTo(4);
		assertThat(worksheet.getRowCount()).isEqualTo(3);
	}

	@Test
	public void shouldReturnTheCorrectCellContentForEachRowAndColumnWhenTheFirstTwoRowsAreBlank() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-first-two-rows.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet).hasRows(3);
		assertThat(worksheet).eachRowHasThisManyColumns(5);

		assertThat(worksheet).rowHasThisContent(0, "First Column", "Second Column", "Third Column", "Fourth Column", "Fifth Column");
		assertThat(worksheet).rowHasThisContent(1, "Jeff", "Smith", "jeff@place.com", "22", "today");
		assertThat(worksheet).rowHasThisContent(2, "Mandy", "Mathers", "mandy@place.com", "34", "yesterday");

		assertThat(worksheet).columnHasThisContent(0, "First Column", "Jeff", "Mandy");
		assertThat(worksheet).columnHasThisContent(1, "Second Column", "Smith", "Mathers");
		assertThat(worksheet).columnHasThisContent(2, "Third Column", "jeff@place.com", "mandy@place.com");
		assertThat(worksheet).columnHasThisContent(3, "Fourth Column", "22", "34");
		assertThat(worksheet).columnHasThisContent(4, "Fifth Column", "today", "yesterday");
	}

	@Test
	public void shouldReturnTheCorrectNumberOfRowsWhenTheMiddleTwoRowsAreBlank() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-middle-two-rows.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet.getFirstRowIndex()).isEqualTo(0);
		assertThat(worksheet.getLastRowIndex()).isEqualTo(4);
		assertThat(worksheet.getRowCount()).isEqualTo(3);
	}

	@Test
	public void shouldReturnTheCorrectCellContentForEachRowAndColumnWhenTheMiddleTwoRowsAreBlank() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-middle-two-rows.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet).hasRows(3);
		assertThat(worksheet).eachRowHasThisManyColumns(5);

		assertThat(worksheet).rowHasThisContent(0, "First Column", "Second Column", "Third Column", "Fourth Column", "Fifth Column");
		assertThat(worksheet).rowHasThisContent(1, "Jeff", "Smith", "jeff@place.com", "22", "today");
		assertThat(worksheet).rowHasThisContent(2, "Mandy", "Mathers", "mandy@place.com", "34", "yesterday");

		assertThat(worksheet).columnHasThisContent(0, "First Column", "Jeff", "Mandy");
		assertThat(worksheet).columnHasThisContent(1, "Second Column", "Smith", "Mathers");
		assertThat(worksheet).columnHasThisContent(2, "Third Column", "jeff@place.com", "mandy@place.com");
		assertThat(worksheet).columnHasThisContent(3, "Fourth Column", "22", "34");
		assertThat(worksheet).columnHasThisContent(4, "Fifth Column", "today", "yesterday");
	}

	@Test
	public void shouldReturnTheCorrectNumberOfColumnsWhenTheLastColumnIsMissingAHeader() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-last-header.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet.getFirstColumnIndex()).isEqualTo(0);
		assertThat(worksheet.getLastColumnIndex()).isEqualTo(4);
		assertThat(worksheet.getColumnCount()).isEqualTo(5);
	}

	@Test
	public void shouldReturnTheCorrectCellContentForEachRowAndColumnWhenTheLastColumnIsMissingAHeader() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-last-header.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet).hasRows(3);
		assertThat(worksheet).eachRowHasThisManyColumns(5);

		assertThat(worksheet).rowHasThisContent(0, "First Column", "Second Column", "Third Column", "Fourth Column", "");
		assertThat(worksheet).rowHasThisContent(1, "Jeff", "Smith", "jeff@place.com", "22", "today");
		assertThat(worksheet).rowHasThisContent(2, "Mandy", "Mathers", "mandy@place.com", "34", "yesterday");

		assertThat(worksheet).columnHasThisContent(0, "First Column", "Jeff", "Mandy");
		assertThat(worksheet).columnHasThisContent(1, "Second Column", "Smith", "Mathers");
		assertThat(worksheet).columnHasThisContent(2, "Third Column", "jeff@place.com", "mandy@place.com");
		assertThat(worksheet).columnHasThisContent(3, "Fourth Column", "22", "34");
		assertThat(worksheet).columnHasThisContent(4, "", "today", "yesterday");
	}

	@Test
	public void shouldReturnTheCorrectNumberOfColumnsWhenTheMiddleColumnIsMissingAHeader() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-middle-header.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet.getFirstColumnIndex()).isEqualTo(0);
		assertThat(worksheet.getLastColumnIndex()).isEqualTo(4);
		assertThat(worksheet.getColumnCount()).isEqualTo(5);
	}

	@Test
	public void shouldReturnTheCorrectCellContentForEachRowAndColumnWhenTheMiddleColumnIsMissingAHeader() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-middle-header.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet).hasRows(3);
		assertThat(worksheet).eachRowHasThisManyColumns(5);

		assertThat(worksheet).rowHasThisContent(0, "First Column", "Second Column", "", "Fourth Column", "Fifth Column");
		assertThat(worksheet).rowHasThisContent(1, "Jeff", "Smith", "jeff@place.com", "22", "today");
		assertThat(worksheet).rowHasThisContent(2, "Mandy", "Mathers", "mandy@place.com", "34", "yesterday");

		assertThat(worksheet).columnHasThisContent(0, "First Column", "Jeff", "Mandy");
		assertThat(worksheet).columnHasThisContent(1, "Second Column", "Smith", "Mathers");
		assertThat(worksheet).columnHasThisContent(2, "", "jeff@place.com", "mandy@place.com");
		assertThat(worksheet).columnHasThisContent(3, "Fourth Column", "22", "34");
		assertThat(worksheet).columnHasThisContent(4, "Fifth Column", "today", "yesterday");
	}

	@Test
	public void shouldReturnTheCorrectNumberOfColumnsWhenTheFirstColumnIsMissingAHeader() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-first-header.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet.getFirstColumnIndex()).isEqualTo(0);
		assertThat(worksheet.getLastColumnIndex()).isEqualTo(4);
		assertThat(worksheet.getColumnCount()).isEqualTo(5);
	}

	@Test
	public void shouldReturnTheCorrectCellContentForEachRowAndColumnWhenTheFirstColumnIsMissingAHeader() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-first-header.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet).hasRows(3);
		assertThat(worksheet).eachRowHasThisManyColumns(5);

		assertThat(worksheet).rowHasThisContent(0, "", "Second Column", "Third Column", "Fourth Column", "Fifth Column");
		assertThat(worksheet).rowHasThisContent(1, "Jeff", "Smith", "jeff@place.com", "22", "today");
		assertThat(worksheet).rowHasThisContent(2, "Mandy", "Mathers", "mandy@place.com", "34", "yesterday");

		assertThat(worksheet).columnHasThisContent(0, "", "Jeff", "Mandy");
		assertThat(worksheet).columnHasThisContent(1, "Second Column", "Smith", "Mathers");
		assertThat(worksheet).columnHasThisContent(2, "Third Column", "jeff@place.com", "mandy@place.com");
		assertThat(worksheet).columnHasThisContent(3, "Fourth Column", "22", "34");
		assertThat(worksheet).columnHasThisContent(4, "Fifth Column", "today", "yesterday");
	}

	@Test
	public void shouldReturnTheCorrectNumberOfColumnsWhenTheFirstTwoColumnsAreMissing() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-first-two-columns.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet.getFirstColumnIndex()).isEqualTo(2);
		assertThat(worksheet.getLastColumnIndex()).isEqualTo(6);
		assertThat(worksheet.getColumnCount()).isEqualTo(5);
	}

	@Test
	public void shouldReturnTheCellContentForEachRowWhenTheFirstTwoColumnsAreMissing() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-first-two-columns.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet).hasRows(3);
		assertThat(worksheet).eachRowHasThisManyColumns(5);

		assertThat(worksheet).rowHasThisContent(0, "First Column", "Second Column", "Third Column", "Fourth Column", "Fifth Column");
		assertThat(worksheet).rowHasThisContent(1, "Jeff", "Smith", "jeff@place.com", "22", "today");
		assertThat(worksheet).rowHasThisContent(2, "Mandy", "Mathers", "mandy@place.com", "34", "yesterday");

		assertThat(worksheet).columnHasThisContent(0, "First Column", "Jeff", "Mandy");
		assertThat(worksheet).columnHasThisContent(1, "Second Column", "Smith", "Mathers");
		assertThat(worksheet).columnHasThisContent(2, "Third Column", "jeff@place.com", "mandy@place.com");
		assertThat(worksheet).columnHasThisContent(3, "Fourth Column", "22", "34");
		assertThat(worksheet).columnHasThisContent(4, "Fifth Column", "today", "yesterday");
	}

	@Test
	public void shouldReturnTheCorrectNumberOfColumnsWhenTheMiddleTwoColumnsAreMissing() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-middle-two-columns.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet.getFirstColumnIndex()).isEqualTo(0);
		assertThat(worksheet.getLastColumnIndex()).isEqualTo(6);
		assertThat(worksheet.getColumnCount()).isEqualTo(7);
	}

	@Test
	public void shouldReturnTheCorrectCellContentForEachRowAndColumnWhenTheMiddleTwoColumnsAreMissing() {
		this.spreadsheet = new ExcelSpreadsheet(path("missing-middle-two-columns.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet).hasRows(3);
		assertThat(worksheet).eachRowHasThisManyColumns(7);

		assertThat(worksheet).rowHasThisContent(0, "First Column", "Second Column", "", "", "Third Column", "Fourth Column", "Fifth Column");
		assertThat(worksheet).rowHasThisContent(1, "Jeff", "Smith", "", "", "jeff@place.com", "22", "today");
		assertThat(worksheet).rowHasThisContent(2, "Mandy", "Mathers", "", "", "mandy@place.com", "34", "yesterday");

		assertThat(worksheet).columnHasThisContent(0, "First Column", "Jeff", "Mandy");
		assertThat(worksheet).columnHasThisContent(1, "Second Column", "Smith", "Mathers");
		assertThat(worksheet).columnHasThisContent(2, "", "", "");
		assertThat(worksheet).columnHasThisContent(3, "", "", "");
		assertThat(worksheet).columnHasThisContent(4, "Third Column", "jeff@place.com", "mandy@place.com");
		assertThat(worksheet).columnHasThisContent(5, "Fourth Column", "22", "34");
		assertThat(worksheet).columnHasThisContent(6, "Fifth Column", "today", "yesterday");
	}

	@Test
	public void shouldReturnTheCellContentForEachRowWhenManyFieldsAreEmtpy() {
		this.spreadsheet = new ExcelSpreadsheet(path("disparate.xlsx"));
		ExcelWorksheet worksheet = spreadsheet.getWorksheets().get(0);

		assertThat(worksheet).hasRows(4);
		assertThat(worksheet).eachRowHasThisManyColumns(5);

		assertThat(worksheet).rowHasThisContent(0, "First Column", "Second Column", "Third Column", "", "Fifth Column");
		assertThat(worksheet).rowHasThisContent(1, "", "", "jeff@place.com", "22", "today");
		assertThat(worksheet).rowHasThisContent(2, "", "Mathers", "mandy@place.com", "34", "");
		assertThat(worksheet).rowHasThisContent(3, "Craig", "", "", "", "");

		assertThat(worksheet).columnHasThisContent(0, "First Column", "", "", "Craig");
		assertThat(worksheet).columnHasThisContent(1, "Second Column", "", "Mathers", "");
		assertThat(worksheet).columnHasThisContent(2, "Third Column", "jeff@place.com", "mandy@place.com", "");
		assertThat(worksheet).columnHasThisContent(3, "", "22", "34", "");
		assertThat(worksheet).columnHasThisContent(4, "Fifth Column", "today", "", "");
	}

}
