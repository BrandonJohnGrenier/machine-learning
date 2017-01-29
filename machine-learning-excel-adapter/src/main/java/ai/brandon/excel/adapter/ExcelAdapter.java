package ai.brandon.excel.adapter;

import ai.brandon.excel.model.ExcelWorksheet;

public interface ExcelAdapter<T> {

	AdapterResult<T> adapt(ExcelWorksheet worksheet);

}
