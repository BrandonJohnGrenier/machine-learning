package ai.brandon.excel.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import ai.brandon.excel.model.ExcelCell;
import ai.brandon.excel.model.ExcelRow;

public class ExcelRowTest {

	private ExcelRow row;

	@Before
	public void before() {
		this.row = new ExcelRow();
	}

	@Test
	public void shouldBeAMetaModelRowIfAtLeastOneColumnStartsWithAHashTag() {
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 1, "#label"));
		row.add(new ExcelCell(1, 1, ""));
		assertThat(row.isMetaModelRow()).isTrue();
	}

	@Test
	public void shouldNotBeAMetaModelRowIfThereAreNoCellsThatStartWithAHashTag() {
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 1, ""));
		assertThat(row.isMetaModelRow()).isFalse();
	}

	@Test
	public void shouldNotBeAbleToAddNullCellsToTheRow() {
		assertThat(row.isEmpty()).isTrue();
		row.add(null);
		assertThat(row.isEmpty()).isTrue();
	}

	@Test
	public void rowShouldBeEmptyWhenThereAreNoCells() {
		assertThat(row.isEmpty()).isTrue();
	}

	@Test
	public void shouldBeAbleToFindTheFirstColumnIndex() {
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 2, ""));
		row.add(new ExcelCell(1, 6, ""));
		row.add(new ExcelCell(1, 7, ""));
		row.add(new ExcelCell(1, 4, ""));
		assertThat(row.getFirstColumnIndex()).isEqualTo(1);
	}

	@Test
	public void shouldBeAbleToFindTheLastColumnIndex() {
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 2, ""));
		row.add(new ExcelCell(1, 6, ""));
		row.add(new ExcelCell(1, 7, ""));
		row.add(new ExcelCell(1, 4, ""));
		assertThat(row.getLastColumnIndex()).isEqualTo(7);
	}

	@Test
	public void rowShouldBeEmptyWhenAllCellsAreEmpty() {
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 1, ""));

		assertThat(row.isEmpty()).isTrue();
	}

	@Test
	public void rowShouldBeEmptyWhenAllCellContentIsNull() {
		row.add(new ExcelCell(1, 1, null));
		row.add(new ExcelCell(1, 1, null));
		row.add(new ExcelCell(1, 1, null));
		row.add(new ExcelCell(1, 1, null));
		row.add(new ExcelCell(1, 1, null));
		assertThat(row.isEmpty()).isTrue();
	}

	@Test
	public void rowShouldNotBeEmptyWhenAtLeastOneCellIsNotEmtpy() {
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 1, "foo"));
		row.add(new ExcelCell(1, 1, ""));
		row.add(new ExcelCell(1, 1, ""));

		assertThat(row.isEmpty()).isFalse();
		assertThat(row.atLeastOneCellIsEmpty()).isTrue();
	}

}
