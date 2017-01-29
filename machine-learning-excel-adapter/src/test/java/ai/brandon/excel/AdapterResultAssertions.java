package ai.brandon.excel;

import java.util.Arrays;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import ai.brandon.excel.adapter.AdapterResult;

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

    public AdapterResultAssertions withErrors(String... errors) {
        Assertions.assertThat(errors.length).describedAs("Expected " + errors.length + " errors but found " + actual.getErrors().size() + " errors: " + Arrays.toString(actual.getErrors().toArray())).isEqualTo(actual.getErrors().size());
        for (String error : errors) {
            Assertions.assertThat(actual.getErrors()).contains(error);
        }
        return this;
    }

}
