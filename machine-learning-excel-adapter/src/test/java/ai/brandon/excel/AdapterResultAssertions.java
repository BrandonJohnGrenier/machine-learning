package ai.brandon.excel;

import static org.fest.assertions.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;

import ai.brandon.excel.adapter.AdapterResult;
import au.com.enterprisegenetics.rest.api.NodeRepresentation;
import au.com.enterprisegenetics.rest.api.PropertyRepresentation;
import au.com.enterprisegenetics.rest.api.RelationshipRepresentation;

@SuppressWarnings("unchecked")
public class AdapterResultAssertions extends AbstractAssert<AdapterResultAssertions, AdapterResult<?>> {

	public AdapterResultAssertions(AdapterResult<?> result) {
		super(result, AdapterResultAssertions.class);
	}

	public static AdapterResultAssertions assertThat(AdapterResult<?> result) {
		return new AdapterResultAssertions(result);
	}

	public AdapterResultAssertions rejected() {
		Assertions.assertThat(actual.rejected()).describedAs("Expected the result to be rejected, but had 0 errors").isTrue();
		return this;
	}

	public AdapterResultAssertions hasThisManyResults(Integer size) {
		Assertions.assertThat(actual.getData()).hasSize(size);
		return this;
	}

	public AdapterResultAssertions accepted() {
		Assertions.assertThat(actual.accepted()).describedAs("Expected the result to be accepted, but had " + actual.getErrors().size() + " validation errors: " + Arrays.toString(actual.getErrors().toArray())).isTrue();
		return this;
	}

	public AdapterResultAssertions containsRelationshipBetween(String from, String to) {
		List<RelationshipRepresentation> relationships = (List<RelationshipRepresentation>) actual.getData();
		for (RelationshipRepresentation representation : relationships) {
			if (representation.getSource().getKey().equals(from) && representation.getDestination().getKey().equals(to)) {
				return this;
			}
		}
		fail("Expected to find relationship with source " + from + " and destination " + to + ", but did not.");
		return this;
	}

	public AdapterResultAssertions containsPropertyForNode(Integer index, String name, String value, String type) {
		NodeRepresentation representation = (NodeRepresentation) actual.getData().get(index);
		if (representation.getProperties().isEmpty()) {
			Assertions.fail("This node has no properties!");
		}
		for (PropertyRepresentation property : representation.getProperties()) {
			if (property.getName().equals(name) && property.getValue().equals(value) && property.getType().equals(type)) {
				return this;
			}
		}
		fail("Expected node at index " + index + " to contain the following property {name:" + name + ", value:" + value + ", type:" + type + "}");
		return this;
	}

	public AdapterResultAssertions containsPropertyForRelationship(Integer index, String name, String value, String type) {
		RelationshipRepresentation representation = (RelationshipRepresentation) actual.getData().get(index);
		if (representation.getProperties().isEmpty()) {
			fail("This relationship has no properties!");
		}
		for (PropertyRepresentation property : representation.getProperties()) {
			if (property.getName().equals(name) && property.getValue().equals(value) && property.getType().equals(type)) {
				return this;
			}
		}
		fail("Expected relationship at index " + index + " to contain the following property {name:" + name + ", value:" + value + ", type:" + type + "}");
		return this;
	}

	public AdapterResultAssertions withErrors(String... errors) {
		Assertions.assertThat(errors.length).describedAs("Expected " + errors.length + " errors but found " + actual.getErrors().size() + " errors: " + Arrays.toString(actual.getErrors().toArray())).isEqualTo(actual.getErrors().size());
		for (String error : errors) {
			Assertions.assertThat(actual.getErrors()).contains(error);
		}
		return this;
	}

}
