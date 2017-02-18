package ai.brandon.excel.adapter;

import java.util.ArrayList;
import java.util.List;

import ai.brandon.commons.model.SupervisedTrainingInstance;
import ai.brandon.commons.model.SupervisedTrainingSet;
import ai.brandon.excel.model.ExcelRow;
import ai.brandon.excel.model.ExcelWorksheet;

public class ExcelSupervisedTrainingSetAdapter implements ExcelAdapter<SupervisedTrainingSet<Double>> {

    public AdapterResult<SupervisedTrainingSet<Double>> adapt(ExcelWorksheet worksheet) {
        if (!worksheet.isValid()) {
            return AdapterResult.reject(worksheet.getErrors());
        }

        ExcelRow headerRow = worksheet.getHeader();

        SupervisedTrainingSet<Double> set = new SupervisedTrainingSet<>(worksheet.getColumnCount() - 1);

        for (ExcelRow dataRow : worksheet.getAllDataRows()) {
            set.add(adapt(headerRow, dataRow, worksheet));
        }

        return AdapterResult.accept(set);
    }

    private SupervisedTrainingInstance<Double> adapt(ExcelRow header, ExcelRow data, ExcelWorksheet worksheet) {
        Double target = Double.parseDouble(data.cellAt(worksheet.getLastColumnIndex()).get().getValue());

        List<Double> features = new ArrayList<Double>();
        for (int i = 0; i < worksheet.getLastColumnIndex(); i++) {
            features.add(Double.parseDouble(data.cellAt(i).get().getValue()));
        }

        return new SupervisedTrainingInstance<Double>(target, features);
    }

}
