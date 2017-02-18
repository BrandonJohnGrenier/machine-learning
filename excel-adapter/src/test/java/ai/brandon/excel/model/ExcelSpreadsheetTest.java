package ai.brandon.excel.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

import ai.brandon.excel.AdapterTest;

public class ExcelSpreadsheetTest extends AdapterTest {

    private ExcelSpreadsheet spreadsheet;

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
