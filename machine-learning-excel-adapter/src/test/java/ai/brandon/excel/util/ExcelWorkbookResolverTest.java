package ai.brandon.excel.util;

import static ai.brandon.excel.ExcelAssertions.assertThat;

import java.io.File;
import java.io.InputStream;

import org.junit.Test;

import ai.brandon.excel.AdapterTest;
import ai.brandon.excel.util.ExcelWorkbookResolver;

public class ExcelWorkbookResolverTest extends AdapterTest {

	@Test
	public void shouldBeAbleToReadAFileFromAFileInstance() {
		assertThat(ExcelWorkbookResolver.open(new File(path("simple.xlsx")))).accepted();
	}

	@Test
	public void shouldBeAbleToReadAFileFromTheFileSystem() {
		assertThat(ExcelWorkbookResolver.open(path("simple.xlsx"))).accepted();
	}

	@Test
	public void shouldBeAbleToReadAFileFromAnInputStream() {
		assertThat(ExcelWorkbookResolver.open(inputStream("simple.xlsx"))).accepted();
	}

	@Test
	public void shouldNotBeAbleToReadAFileWhenTheFileIsNullOrEmpty() {
		assertThat(ExcelWorkbookResolver.open("  ")).rejected().withErrors("Unable to read file, the file path is null or empty.");
		assertThat(ExcelWorkbookResolver.open((String) null)).rejected().withErrors("Unable to read file, the file path is null or empty.");
	}

	@Test
	public void shouldNotBeAbleToReadAFileWhenTheFileIsADirectory() {
		assertThat(ExcelWorkbookResolver.open("/tmp")).rejected().withErrors("Unable to read file, the file /tmp is not actually a file.");
	}

	@Test
	public void shouldNotBeAbleToReadAFileWhenTheFileIsNotReadable() {
		assertThat(ExcelWorkbookResolver.open("/tmp/asldifjlsadf")).rejected().withErrors("Unable to read file, the file /tmp/asldifjlsadf is not readable.");
	}

	@Test
	public void shouldNotBeAbleToReadAFileWhenTheFileIsNotAnExcelFile() {
		assertThat(ExcelWorkbookResolver.open(path("not-excel.txt"))).rejected().withErrors("The file not-excel.txt does not seem to be an Excel file - should have a .xls or .xlsx file extension.");
	}

	@Test
	public void shouldNotBeAbleToReadAFileFromAnInputStreamWhenTheInputStreamIsNull() {
		assertThat(ExcelWorkbookResolver.open((InputStream) null)).rejected().withErrors("Unable to read file, input stream is null.");
	}

	private InputStream inputStream(String filename) {
		return this.getClass().getClassLoader().getResourceAsStream("excel/" + filename);
	}

}
