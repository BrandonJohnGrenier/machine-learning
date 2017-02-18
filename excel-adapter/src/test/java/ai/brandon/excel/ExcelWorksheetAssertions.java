package ai.brandon.excel;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import ai.brandon.excel.model.ExcelColumn;
import ai.brandon.excel.model.ExcelRow;
import ai.brandon.excel.model.ExcelWorksheet;

public class ExcelWorksheetAssertions extends AbstractAssert<ExcelWorksheetAssertions, ExcelWorksheet> {

	public ExcelWorksheetAssertions(ExcelWorksheet result) {
		super(result, ExcelWorksheetAssertions.class);
	}

	public static ExcelWorksheetAssertions assertThat(ExcelWorksheet result) {
		return new ExcelWorksheetAssertions(result);
	}

	public ExcelWorksheetAssertions hasRows(Integer count) {
		Assertions.assertThat(actual.getAllRows()).hasSize(count);
		return this;
	}

	public ExcelWorksheetAssertions rowHasThisContent(Integer rowIndex, String... content) {
		ExcelRow row = actual.getAllRows().get(rowIndex);
		for (int i = 0; i < content.length; i++) {
			Assertions.assertThat(content[i]).isEqualTo(row.cellAt(i).get().getValue());
		}
		return this;
	}

	public ExcelWorksheetAssertions columnHasThisContent(Integer columnIndex, String... content) {
		ExcelColumn column = actual.getColumn(columnIndex);
		for (int i = 0; i < content.length; i++) {
			Assertions.assertThat(content[i]).isEqualTo(column.cellAt(i).get().getValue());
		}
		return this;
	}

	public ExcelWorksheetAssertions eachRowHasThisManyColumns(Integer count) {
		List<ExcelRow> rows = actual.getAllRows();
		for (ExcelRow row : rows) {
			Assertions.assertThat(row.getCells()).hasSize(count);
		}
		return this;
	}

	public ExcelWorksheetAssertions isInvalid() {
		Assertions.assertThat(actual.isValid()).describedAs("Expected the worksheet to be invalid, but had 0 errors.").isFalse();
		return this;
	}

	public ExcelWorksheetAssertions isValid() {
		Assertions.assertThat(actual.isValid()).describedAs("Expected the worksheet to be valid, but had " + actual.getErrors().size() + " errors: " + Arrays.toString(actual.getErrors().toArray())).isTrue();
		return this;
	}

	public ExcelWorksheetAssertions withErrors(String... errors) {
		Assertions.assertThat(errors.length).describedAs("Expected " + errors.length + " errors but found " + actual.getErrors().size() + " errors: " + Arrays.toString(actual.getErrors().toArray())).isEqualTo(actual.getErrors().size());
		for (String error : errors) {
			Assertions.assertThat(actual.getErrors()).contains(error);
		}
		return this;
	}

}
