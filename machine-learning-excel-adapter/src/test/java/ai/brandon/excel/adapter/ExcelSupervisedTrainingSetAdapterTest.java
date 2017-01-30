package ai.brandon.excel.adapter;

import static ai.brandon.excel.ExcelAssertions.assertThat;

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
	public void shouldRejectASpreadsheetWithMoreThanOneIdColumn() {
		AdapterResult<SupervisedTrainingSet<Double>> result = adapter.adapt(new ExcelSpreadsheet(path("nodes-with-multiple-id-tags.xlsx")).getWorksheets().get(0));
		assertThat(result).rejected().withErrors("The worksheet named Sheet1 has more than one #id tag. Only one column can have an #id tag.");
	}

	@Test
	public void shouldNotGenerateAKeyForEachNodeWhenTheMetaModelRowDoesNotContainAnIdAnnotation() {
		AdapterResult<SupervisedTrainingSet<Double>> result = adapter.adapt(new ExcelSpreadsheet(path("missing-id-tag.xlsx")).getWorksheets().get(0));
		assertThat(result).accepted();
		assertThat(result).hasThisManyResults(2);


	}

//	@Test
//	public void shouldBeAbleToConvertAnExcelWorksheetWhenNoMetaModelIsPresent() {
//		AdapterResult<NodeRepresentation> result = adapter.adapt(new ExcelSpreadsheet(path("valid-no-metamodel.xlsx")).getWorksheets().get(0));
//		assertThat(result).accepted();
//		assertThat(result).hasThisManyResults(2);
//
//		assertThat(result).containsPropertyForNode(0, "First Name", "Jeff", NODE_LABEL);
//		assertThat(result).containsPropertyForNode(0, "Last Name", "Smith", NODE_LABEL);
//		assertThat(result).containsPropertyForNode(0, "Age", "22", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(0, "Department", "sales", NODE_LABEL);
//
//		assertThat(result).containsPropertyForNode(1, "First Name", "Mandy", NODE_LABEL);
//		assertThat(result).containsPropertyForNode(1, "Last Name", "Mathers", NODE_LABEL);
//		assertThat(result).containsPropertyForNode(1, "Age", "34", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(1, "Department", "marketing", NODE_LABEL);
//	}
//
//	@Test
//	public void shouldBeAbleToInferAColumnAsACategoryWhenNoMetaModelIsDefined() {
//		AdapterResult<NodeRepresentation> result = adapter.adapt(new ExcelSpreadsheet(path("infer-category.xlsx")).getWorksheets().get(0));
//		assertThat(result).accepted();
//		assertThat(result).hasThisManyResults(10);
//
//		assertThat(result).containsPropertyForNode(0, "First Name", "John", NODE_LABEL);
//		assertThat(result).containsPropertyForNode(0, "Last Name", "Smith", NODE_LABEL);
//		assertThat(result).containsPropertyForNode(0, "Role", "Manager", NODE_CATEGORY);
//		assertThat(result).containsPropertyForNode(0, "Department", "Sales", NODE_CATEGORY);
//	}
//
//	@Test
//	public void shouldBeAbleToInferAColumnAsASizeWhenNoMetaModelIsDefined() {
//		AdapterResult<NodeRepresentation> result = adapter.adapt(new ExcelSpreadsheet(path("infer-size.xlsx")).getWorksheets().get(0));
//		assertThat(result).accepted();
//		assertThat(result).hasThisManyResults(10);
//
//		assertThat(result).containsPropertyForNode(0, "First Name", "John", NODE_LABEL);
//		assertThat(result).containsPropertyForNode(0, "Last Name", "Smith", NODE_LABEL);
//		assertThat(result).containsPropertyForNode(0, "Influence", "1", NODE_SIZE);
//		assertThat(result).containsPropertyForNode(0, "Role", "Manager", NODE_CATEGORY);
//		assertThat(result).containsPropertyForNode(0, "Department", "Sales", NODE_CATEGORY);
//	}
//
//	@Test
//	public void shouldBeAbleToInferAColumnAsAnAttributeWhenNoMetaModelIsDefined() {
//		AdapterResult<NodeRepresentation> result = adapter.adapt(new ExcelSpreadsheet(path("infer-attribute.xlsx")).getWorksheets().get(0));
//		assertThat(result).accepted();
//		assertThat(result).hasThisManyResults(10);
//
//		assertThat(result).containsPropertyForNode(0, "First Name", "03-445-004", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(0, "Last Name", "Smith", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(0, "Influence", "1", NODE_SIZE);
//		assertThat(result).containsPropertyForNode(0, "Role", "Manager", NODE_CATEGORY);
//		assertThat(result).containsPropertyForNode(0, "Department", "Sales", NODE_CATEGORY);
//	}
//
//	@Test
//	public void shouldBeAbleToConvertAnExcelWorksheetWithIdsIntoAListOfNodeRepresentations() {
//		AdapterResult<NodeRepresentation> result = adapter.adapt(new ExcelSpreadsheet(path("just-ids.xlsx")).getWorksheets().get(0));
//		assertThat(result).accepted();
//		assertThat(result).hasThisManyResults(5);
//	}
//
//	@Test
//	public void shouldBeAbleToConvertAnExcelWorksheetWithMissingIdsIntoAListOfNodeRepresentations() {
//		AdapterResult<NodeRepresentation> result = adapter.adapt(new ExcelSpreadsheet(path("just-ids-some-missing.xlsx")).getWorksheets().get(0));
//		assertThat(result).accepted();
//		assertThat(result).hasThisManyResults(5);
//	}
//
//	@Test
//	public void shouldBeAbleToConvertAnExcelWorksheetIntoAListOfNodeRepresentations() {
//		AdapterResult<NodeRepresentation> result = adapter.adapt(new ExcelSpreadsheet(path("valid-annotated.xlsx")).getWorksheets().get(0));
//		assertThat(result).accepted();
//		assertThat(result).hasThisManyResults(2);
//
//		assertThat(result).containsPropertyForNode(0, "First Name", "Jeff", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(0, "Last Name", "Smith", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(0, "Age", "22", NODE_SIZE);
//		assertThat(result).containsPropertyForNode(0, "Department", "sales", NODE_CATEGORY);
//
//		assertThat(result).containsPropertyForNode(1, "First Name", "Mandy", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(1, "Last Name", "Mathers", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(1, "Age", "34", NODE_SIZE);
//		assertThat(result).containsPropertyForNode(1, "Department", "marketing", NODE_CATEGORY);
//	}
//
//	@Test
//	public void shouldBeAbleToConvertAnExcelWorksheetIntoAListOfNodeRepresentationsWhenTheFirstTwoColumnsAreMissing() {
//		AdapterResult<NodeRepresentation> result = adapter.adapt(new ExcelSpreadsheet(path("valid-missing-first-two-columns.xlsx")).getWorksheets().get(0));
//		assertThat(result).accepted();
//		assertThat(result).hasThisManyResults(2);
//
//		assertThat(result.getData().get(0).getKey()).isEqualTo("1");
//		assertThat(result).containsPropertyForNode(0, "Second Column", "Smith", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(0, "Third Column", "jeff@place.com", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(0, "Fourth Column", "22", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(0, "Fifth Column", "today", NODE_ATTRIBUTE);
//
//		assertThat(result.getData().get(1).getKey()).isEqualTo("2");
//		assertThat(result).containsPropertyForNode(1, "Second Column", "Mathers", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(1, "Third Column", "mandy@place.com", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(1, "Fourth Column", "34", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(1, "Fifth Column", "yesterday", NODE_ATTRIBUTE);
//	}
//
//	@Test
//	public void shouldBeAbleToConvertAnExcelWorksheetIntoAListOfNodeRepresentationsAndUsePropertyAsADefaultTypeWhenATypeIsNotProvided() {
//		AdapterResult<NodeRepresentation> result = adapter.adapt(new ExcelSpreadsheet(path("valid-missing-annotations.xlsx")).getWorksheets().get(0));
//		assertThat(result).accepted();
//		assertThat(result).hasThisManyResults(2);
//
//		assertThat(result).containsPropertyForNode(0, "First Name", "Jeff", NODE_LABEL);
//		assertThat(result).containsPropertyForNode(0, "Last Name", "Smith", NODE_LABEL);
//		assertThat(result).containsPropertyForNode(0, "Age", "22", NODE_SIZE);
//		assertThat(result).containsPropertyForNode(0, "Department", "sales", NODE_CATEGORY);
//
//		assertThat(result).containsPropertyForNode(1, "First Name", "Mandy", NODE_LABEL);
//		assertThat(result).containsPropertyForNode(1, "Last Name", "Mathers", NODE_LABEL);
//		assertThat(result).containsPropertyForNode(1, "Age", "34", NODE_SIZE);
//		assertThat(result).containsPropertyForNode(1, "Department", "marketing", NODE_CATEGORY);
//	}
//
//	@Test
//	public void shouldBeAbleToConvertAnExcelWorksheetIntoAListOfNodeRepresentationsAndProvideEmptyValuesForCellsThatHaveNoData() {
//		AdapterResult<NodeRepresentation> result = adapter.adapt(new ExcelSpreadsheet(path("valid-empty-cells.xlsx")).getWorksheets().get(0));
//		assertThat(result).accepted();
//		assertThat(result).hasThisManyResults(2);
//
//		assertThat(result).containsPropertyForNode(0, "First Name", "Jeff", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(0, "Last Name", "Smith", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(0, "Age", "1", NODE_SIZE);
//		assertThat(result).containsPropertyForNode(0, "Department", "", NODE_CATEGORY);
//
//		assertThat(result).containsPropertyForNode(1, "First Name", "Mandy", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(1, "Last Name", "Mathers", NODE_ATTRIBUTE);
//		assertThat(result).containsPropertyForNode(1, "Age", "1", NODE_SIZE);
//		assertThat(result).containsPropertyForNode(1, "Department", "", NODE_CATEGORY);
//	}

}
