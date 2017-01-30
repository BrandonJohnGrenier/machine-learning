package ai.brandon.excel.adapter;

import org.junit.Before;
import org.junit.Test;

import ai.brandon.commons.model.SupervisedTrainingSet;
import ai.brandon.excel.AdapterTest;
import ai.brandon.excel.model.ExcelSpreadsheet;

public class ExcelSupervisedTrainingSetAdapterTest extends AdapterTest {

    private ExcelSupervisedTrainingSetAdapter adapter;

    @Before
    public void before() {
        this.adapter = new ExcelSupervisedTrainingSetAdapter();
    }

    @Test
    public void shouldBeAbleToConvertASpreadsheetIntoASupervisedTrainingSet() {
        AdapterResult<SupervisedTrainingSet<Double>> result = adapter.adapt(new ExcelSpreadsheet(path("simple-training-set.xlsx")).getWorksheets().get(0));
        System.out.println(result.getData());
    }

}
