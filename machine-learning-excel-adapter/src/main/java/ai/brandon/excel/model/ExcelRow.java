package ai.brandon.excel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelRow {

	private final List<ExcelCell> cells = new ArrayList<ExcelCell>();

	public ExcelRow add(ExcelCell cell) {
		if (cell != null) {
			this.cells.add(cell);
		}
		return this;
	}

	public boolean isMetaModelRow() {
		for (int i = 0; i < cells.size(); i++) {
			if (cellAt(i).isPresent() && cellAt(i).get().getValue().startsWith("#")) {
				return true;
			}
		}
		return false;
	}

	public boolean hasExactlyTwoLinkTags() {
		Integer count = 0;
		for (int i = 0; i < cells.size(); i++) {
			if (cellAt(i).isPresent() && cellAt(i).get().containsLinkTag()) {
				count += 1;
			}
		}
		return count == 2;
	}

	public boolean hasMoreThanOneIdTag() {
		Integer count = 0;
		for (int i = 0; i < cells.size(); i++) {
			if (cellAt(i).isPresent() && cellAt(i).get().containsIdTag()) {
				count += 1;
			}
		}
		return count > 1;
	}

	public boolean hasIdTag() {
		for (int i = 0; i < cells.size(); i++) {
			if (cellAt(i).isPresent() && cellAt(i).get().containsIdTag()) {
				return true;
			}
		}
		return false;
	}

	public boolean atLeastOneCellIsEmpty() {
		for (ExcelCell cell : cells) {
			if (cell != null && cell.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public List<Integer> getEmptyColumnIndexes() {
		List<Integer> indexes = new ArrayList<>();
		for (int i = 0; i < cells.size(); i++) {
			if (cellAt(i).isPresent() && cellAt(i).get().isEmpty()) {
				indexes.add(i);
			}
		}
		return indexes;
	}

	public boolean isEmpty() {
		for (ExcelCell cell : cells) {
			if (cell != null && !cell.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public String cellValueAt(Integer index) {
		for (ExcelCell c : cells) {
			if (c.getColumnIndex() == index) {
				return c.getValue();
			}
		}
		return "";
	}

	public Optional<ExcelCell> cellAt(Integer index) {
		try {
			return (index == null || index < 0) ? Optional.empty() : Optional.of(cells.get(index));
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return Optional.empty();
		}
		catch (IndexOutOfBoundsException e) {
			return Optional.empty();
		}
	}

	public List<ExcelCell> getCells() {
		return cells;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (ExcelCell cell : cells) {
			builder.append(cell.toString());
		}
		return "cells: [" + builder.toString() + "]";
	}

	public Integer getFirstColumnIndex() {
		return cells.isEmpty() ? -1 : cells.stream().mapToInt(ExcelCell::getColumnIndex).min().getAsInt();
	}

	public Integer getLastColumnIndex() {
		return cells.isEmpty() ? -1 : cells.stream().mapToInt(ExcelCell::getColumnIndex).max().getAsInt();
	}

}
