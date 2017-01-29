package ai.brandon.excel.adapter;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;

import ai.brandon.excel.model.ExcelCell;
import ai.brandon.excel.model.ExcelColumn;
import ai.brandon.excel.model.ExcelRow;
import ai.brandon.excel.model.ExcelWorksheet;
import au.com.enterprisegenetics.rest.api.NodeRepresentation;
import au.com.enterprisegenetics.rest.api.PropertyRepresentation;
import au.com.enterprisegenetics.rest.api.PropertyType;

public class ExcelNodeAdapter implements ExcelAdapter<NodeRepresentation> {

	public AdapterResult<NodeRepresentation> adapt(ExcelWorksheet worksheet) {
		if (!worksheet.isValid()) {
			return AdapterResult.reject(worksheet.getErrors());
		}

		ExcelRow metaModel = worksheet.getMetaModel();
		if (metaModel.hasMoreThanOneIdTag()) {
			return AdapterResult.reject("The worksheet named " + worksheet.getName() + " has more than one #id tag. Only one column can have an #id tag.");
		}

		ExcelRow headerRow = worksheet.getHeader();
		ExcelRow metaModelRow = worksheet.getMetaModel();

		List<NodeRepresentation> nodes = new ArrayList<NodeRepresentation>();
		for (ExcelRow dataRow : worksheet.getAllDataRows()) {
			nodes.add(adapt(headerRow, metaModelRow, dataRow, worksheet));
		}

		return AdapterResult.accept(nodes);
	}

	private NodeRepresentation adapt(ExcelRow header, ExcelRow model, ExcelRow data, ExcelWorksheet worksheet) {
		NodeRepresentation representation = new NodeRepresentation();

		for (ExcelCell headerCell : header.getCells()) {
			String name = headerCell.getValue();
			String type = resolveType(model.cellValueAt(headerCell.getColumnIndex()), worksheet.getColumn(headerCell.getColumnIndex()));
			String value = data.cellValueAt(headerCell.getColumnIndex());

			if (type.equals(PropertyType.NODE_SIZE) && isEmpty(value)) {
				value = "1";
			}

			if (type.equals(PropertyType.ID)) {
				representation.setKey(value);
			}
			else {
				representation.addProperty(new PropertyRepresentation(name, value, type));
			}
		}

		return representation;
	}

	private String resolveType(String type, ExcelColumn column) {
		String propertyType = findExplicitType(type, column);
		return propertyType != null ? propertyType : column.inferType();
	}

	private String findExplicitType(String type, ExcelColumn column) {
		if (type.equalsIgnoreCase("#id")) {
			return PropertyType.ID;
		}
		if (type.equalsIgnoreCase("#label")) {
			return PropertyType.NODE_LABEL;
		}
		if (type.equalsIgnoreCase("#category")) {
			return PropertyType.NODE_CATEGORY;
		}
		if (type.equalsIgnoreCase("#attribute")) {
			return PropertyType.NODE_ATTRIBUTE;
		}
		if (type.equalsIgnoreCase("#size")) {
			return PropertyType.NODE_SIZE;
		}
		return null;
	}

}
