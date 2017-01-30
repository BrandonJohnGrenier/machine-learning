package ai.brandon.excel.adapter;

import ai.brandon.commons.model.SupervisedTrainingSet;
import ai.brandon.excel.model.ExcelRow;
import ai.brandon.excel.model.ExcelWorksheet;

public class ExcelSupervisedTrainingSetAdapter implements ExcelAdapter<SupervisedTrainingSet<Double>> {

    public AdapterResult<SupervisedTrainingSet<Double>> adapt(ExcelWorksheet worksheet) {
        if (!worksheet.isValid()) {
            return AdapterResult.reject(worksheet.getErrors());
        }

        ExcelRow headerRow = worksheet.getHeader();
        ExcelRow metaModelRow = worksheet.getMetaModel();

        for (ExcelRow dataRow : worksheet.getAllDataRows()) {
            adapt(headerRow, metaModelRow, dataRow, worksheet);
        }

        return AdapterResult.accept(null);
    }

    private void adapt(ExcelRow header, ExcelRow model, ExcelRow data, ExcelWorksheet worksheet) {

    }

}
