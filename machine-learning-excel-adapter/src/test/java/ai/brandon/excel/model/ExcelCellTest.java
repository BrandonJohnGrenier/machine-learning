package ai.brandon.excel.model;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

import ai.brandon.excel.model.ExcelCell;

public class ExcelCellTest {

    @Test
    public void shouldReturnTrueWhenTheCellContainsALinkTag() {
        assertThat(new ExcelCell(1, 1, "#link").containsLinkTag()).isTrue();
        assertThat(new ExcelCell(1, 1, "#Link").containsLinkTag()).isTrue();
        assertThat(new ExcelCell(1, 1, "#LINK").containsLinkTag()).isTrue();
        assertThat(new ExcelCell(1, 1, "").containsLinkTag()).isFalse();
        assertThat(new ExcelCell(1, 1, "links").containsLinkTag()).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenTheCellContainsAnIdTag() {
        assertThat(new ExcelCell(1, 1, "#id").containsIdTag()).isTrue();
        assertThat(new ExcelCell(1, 1, "#Id").containsIdTag()).isTrue();
        assertThat(new ExcelCell(1, 1, "#ID").containsIdTag()).isTrue();
        assertThat(new ExcelCell(1, 1, "").containsIdTag()).isFalse();
        assertThat(new ExcelCell(1, 1, "ids").containsIdTag()).isFalse();
    }

    @Test
    public void cellShouldBeEmptyWhenTheCellHasNoContent() {
        assertThat(new ExcelCell(1, 1, "").isEmpty()).isTrue();
        assertThat(new ExcelCell(1, 1, "   ").isEmpty()).isTrue();
        assertThat(new ExcelCell(1, 1, null).isEmpty()).isTrue();
    }

    @Test
    public void cellShouldNotBeEmptyWhenTheCellHasContent() {
        assertThat(new ExcelCell(1, 1, "d").isEmpty()).isFalse();
    }

    @Test
    public void shouldReturnTheCorrectRowAndColumnIndex() {

        ExcelCell cell = new ExcelCell(5, 10, "value");
        assertThat(cell.getRowIndex()).isEqualTo(5);
        assertThat(cell.getColumnIndex()).isEqualTo(10);
        assertThat(cell.getValue()).isEqualTo("value");
    }

}
