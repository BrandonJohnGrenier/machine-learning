package ai.brandon.excel.model;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import ai.brandon.excel.adapter.ValidationResult;

import com.google.common.collect.Lists;

public class ExcelWorksheet {

	private final Sheet sheet;

	private final List<Row> rows;
	private final List<ExcelRow> excelRows;
	private final Map<Integer, ExcelColumn> excelColumns;

	private Integer firstColumnIndex = null;
	private Integer lastColumnIndex = null;

	private final ValidationResult result;

	public ExcelWorksheet(Sheet sheet) {
		this.sheet = sheet;
		this.excelColumns = new HashMap<Integer, ExcelColumn>();
		this.rows = sheet == null ? new ArrayList<Row>() : Lists.newArrayList(sheet.rowIterator());
		this.excelRows = generateRows();
		this.result = validate();
	}

	public boolean isValid() {
		return result.accepted();
	}

	public List<String> getErrors() {
		return result.getErrors();
	}

	public String getName() {
		return sheet == null ? "" : this.sheet.getSheetName();
	}

	public boolean isEmpty() {
		return getColumnCount() <= 0 || getRowCount() <= 0;
	}

	public boolean containsNodeData() {
		ExcelRow model = getMetaModel();
		return model == null ? true : model.hasIdTag();
	}

	public boolean containsRelationshipData() {
		ExcelRow model = getMetaModel();
		if (model == null) {
			return false;
		}
		return model.hasExactlyTwoLinkTags();
	}

	public Integer getColumnCount() {
		int count = getLastColumnIndex() - getFirstColumnIndex();
		return count + 1;
	}

	public ExcelColumn getColumn(Integer index) {
		if (index == null) {
			return new ExcelColumn();
		}
		if (excelColumns.containsKey(index)) {
			return excelColumns.get(index);
		}

		ExcelColumn column = new ExcelColumn();
		for (ExcelRow row : excelRows) {
			Optional<ExcelCell> result = row.cellAt(index);
			if (result.isPresent()) {
				column.add(result.get());
			}
		}

		excelColumns.put(index, column);

		return column;
	}

	public Integer getFirstColumnIndex() {
		if (firstColumnIndex != null) {
			return firstColumnIndex;
		}
		try {
			firstColumnIndex = rows.stream().mapToInt(Row::getFirstCellNum).min().getAsInt();
			return firstColumnIndex;
		}
		catch (Exception e) {
			return -1;
		}
	}

	public Integer getLastColumnIndex() {
		if (lastColumnIndex != null) {
			return lastColumnIndex;
		}
		try {
			lastColumnIndex = rows.stream().mapToInt(Row::getLastCellNum).max().getAsInt() - 1;
			return lastColumnIndex;
		}
		catch (Exception e) {
			return -1;
		}
	}

	public Integer getRowCount() {
		return getAllRows().size();
	}

	public Integer getFirstRowIndex() {
		return sheet.getFirstRowNum();
	}

	public Integer getLastRowIndex() {
		return sheet.getLastRowNum();
	}

	public ExcelRow getHeader() {
		return getRowCount() >= 1 ? getAllRows().get(0) : null;
	}

	public ExcelRow getMetaModel() {
		return getRowCount() >= 2 ? getAllRows().get(1) : null;
	}

	public boolean hasMetaModel() {
		ExcelRow metaModel = getMetaModel();
		if (metaModel == null) {
			return false;
		}
		return metaModel.isMetaModelRow();
	}

	public List<ExcelRow> getAllDataRows() {
		List<ExcelRow> rows = getAllRows();
		List<ExcelRow> data = new ArrayList<>();

		Integer testIndex = hasMetaModel() ? 2 : 1;
		if (rows.size() <= testIndex) {
			return new ArrayList<>();
		}

		Integer startIndex = hasMetaModel() ? 2 : 1;
		for (int i = startIndex; i < rows.size(); i++) {
			data.add(rows.get(i));
		}

		return data;
	}

	public List<ExcelRow> getAllRows() {
		return excelRows;
	}

	public List<ExcelRow> generateRows() {
		List<ExcelRow> excelRows = new ArrayList<ExcelRow>();

		for (int rowIndex = getFirstRowIndex(); rowIndex <= getLastRowIndex() + 1; rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			ExcelRow excelRow = transform(row, rowIndex);
			if (excelRow != null && !excelRow.isEmpty()) {
				excelRows.add(excelRow);
			}
		}

		return excelRows;
	}

	private ValidationResult validate() {
		if (this.isEmpty()) {
			return ValidationResult.reject("Unable to find any data in the worksheet.");
		}

		ExcelRow header = this.getHeader();
		if (header == null) {
			return ValidationResult.reject("The worksheet named " + this.getName() + " does not have a header row.");
		}

		List<Integer> emptyHeaderColumnIndexes = getEmptyHeaderColumnIndexes(header, this);
		if (!emptyHeaderColumnIndexes.isEmpty()) {
			return ValidationResult.reject("The worksheet named " + this.getName() + " does not have all header columns populated" + getMissingColumns(emptyHeaderColumnIndexes) + " The header row (i.e. the first row of your worksheet) must have a value in each column.");
		}

		List<String> errors = detectDuplicateColumnHeaders(header);
		if (!errors.isEmpty()) {
			return ValidationResult.reject(errors);
		}

		List<ExcelRow> data = this.getAllDataRows();
		if (data.isEmpty()) {
			return ValidationResult.reject("The worksheet named " + this.getName() + " has no data to process.");
		}

		return ValidationResult.accept();
	}

	private List<String> detectDuplicateColumnHeaders(ExcelRow header) {
		Map<String, Integer> duplicates = new HashMap<>();
		for (ExcelCell cell : header.getCells()) {
			if (cell.isEmpty()) {
				continue;
			}
			if (duplicates.containsKey(cell.getValue())) {
				duplicates.put(cell.getValue(), duplicates.get(cell.getValue()) + 1);
			}
			else {
				duplicates.put(cell.getValue(), 1);
			}
		}

		List<String> errors = new ArrayList<String>();
		for (Map.Entry<String, Integer> key : duplicates.entrySet()) {
			if (key.getValue() > 1) {
				errors.add("There are " + key.getValue() + " columns named " + key.getKey() + " in this worksheet. All column names must be unique.");
			}
		}

		return errors;
	}

	private ExcelRow transform(Row row, Integer rowIndex) {
		if (row == null) {
			return null;
		}

		ExcelRow excelRow = new ExcelRow();

		for (int columnIndex = getFirstColumnIndex(); columnIndex < getLastColumnIndex() + 1; columnIndex++) {
			Cell cell = row.getCell(columnIndex, Row.CREATE_NULL_AS_BLANK);
			excelRow.add(new ExcelCell(rowIndex, columnIndex, getCellContent(cell)));
		}

		return excelRow;
	}

	private String getCellContent(Cell cell) {
		if (cell == null) {
			return "";
		}
		if (cell.getCellType() == CELL_TYPE_BLANK) {
			return "";
		}
		if (cell.getCellType() == CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		}
		if (cell.getCellType() == CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		}
		if (cell.getCellType() == CELL_TYPE_NUMERIC) {
			String value = String.valueOf(cell.getNumericCellValue());
			return value.endsWith(".0") ? StringUtils.removeEnd(value, ".0") : value;
		}
		return "";
	}

	private List<Integer> getEmptyHeaderColumnIndexes(ExcelRow header, ExcelWorksheet worksheet) {
		List<Integer> emptyHeaderColumns = new ArrayList<Integer>();

		if (!header.atLeastOneCellIsEmpty()) {
			return emptyHeaderColumns;
		}

		List<Integer> emptyColumnIndexes = header.getEmptyColumnIndexes();
		for (Integer columnIndex : emptyColumnIndexes) {
			ExcelColumn column = worksheet.getColumn(columnIndex);
			if (!column.isEmpty()) {
				emptyHeaderColumns.add(columnIndex);
			}
		}

		return emptyHeaderColumns;
	}

	private String getMissingColumns(List<Integer> emptyCells) {
		if (emptyCells == null || emptyCells.isEmpty()) {
			return ".";
		}
		if (emptyCells.size() == 1) {
			return " - column " + emptyCells.get(0) + " is empty.";
		}
		return " - columns " + emptyCells.toString() + " are empty.";
	}

}
