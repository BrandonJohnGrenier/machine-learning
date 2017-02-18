package ai.brandon.excel.model;

import org.apache.commons.lang3.StringUtils;

public class ExcelCell {

	private final Integer rowIndex;
	private final Integer columnIndex;
	private final String value;

	public ExcelCell(Integer rowIndex, Integer columnIndex, String value) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.value = StringUtils.isBlank(value) ? "" : value.trim();
	}

	public Integer getRowIndex() {
		return rowIndex;
	}

	public Integer getColumnIndex() {
		return columnIndex;
	}

	public String getValue() {
		return value;
	}

	public boolean isEmpty() {
		return StringUtils.isBlank(value);
	}

	public String toString() {
		return "{row:" + getRowIndex() + ", column:" + getColumnIndex() + ", content:" + getValue() + "}";
	}

}
