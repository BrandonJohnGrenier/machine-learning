package ai.brandon.excel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelColumn {

    private List<ExcelCell> cells = new ArrayList<ExcelCell>();

    public List<ExcelCell> getCells() {
        return cells;
    }

    public void add(ExcelCell cell) {
        if (cell != null) {
            this.cells.add(cell);
        }
    }

    public String cellValueAt(Integer index) {
        Optional<ExcelCell> cell = cellAt(index);
        return cell.isPresent() ? cell.get().getValue() : null;
    }

    public Optional<ExcelCell> cellAt(Integer index) {
        try {
            return (index == null || index < 0) ? Optional.empty() : Optional.of(cells.get(index));
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public boolean isEmpty() {
        for (ExcelCell cell : cells) {
            if (!cell.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public Integer firstIndexOf(String value) {
        for (ExcelCell cell : this.getCells()) {
            if (cell.getValue().equals(value)) {
                return cell.getRowIndex();
            }
        }
        return -1;
    }

}
