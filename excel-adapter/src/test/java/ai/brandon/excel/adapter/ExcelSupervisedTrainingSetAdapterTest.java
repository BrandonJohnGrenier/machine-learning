package ai.brandon.excel.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import ai.brandon.commons.model.SupervisedTrainingInstance;
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

        SupervisedTrainingSet<Double> set = result.getData();
        assertThat(set.getInstances()).hasSize(2);

        SupervisedTrainingInstance<Double> first = set.getInstances().get(0);
        assertThat(first.getTarget()).isEqualTo(200000.0);
        assertThat(first.getFeatures()).containsExactly(1223.0, 34.0, 4.0, 3.0);

        SupervisedTrainingInstance<Double> second = set.getInstances().get(1);
        assertThat(second.getTarget()).isEqualTo(300000.0);
        assertThat(second.getFeatures()).containsExactly(3343.0, 44.0, 2.0, 2.0);
    }

}
