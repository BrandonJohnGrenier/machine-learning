package ai.brandon.excel;

import org.assertj.core.api.Assertions;

import ai.brandon.excel.adapter.AdapterResult;
import ai.brandon.excel.model.ExcelWorksheet;
import ai.brandon.excel.model.WorkbookResolverResult;

public class ExcelAssertions extends Assertions {

    public static WorkbookResolverResultAssertions assertThat(WorkbookResolverResult result) {
        return new WorkbookResolverResultAssertions(result);
    }

    public static AdapterResultAssertions assertThat(AdapterResult<?> result) {
        return new AdapterResultAssertions(result);
    }

    public static ExcelWorksheetAssertions assertThat(ExcelWorksheet result) {
        return new ExcelWorksheetAssertions(result);
    }

}
