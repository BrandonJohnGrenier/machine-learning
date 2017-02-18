package ai.brandon.excel;

import java.util.Arrays;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import ai.brandon.excel.model.WorkbookResolverResult;

public class WorkbookResolverResultAssertions extends AbstractAssert<WorkbookResolverResultAssertions, WorkbookResolverResult> {

    public WorkbookResolverResultAssertions(WorkbookResolverResult result) {
        super(result, WorkbookResolverResultAssertions.class);
    }

    public static WorkbookResolverResultAssertions assertThat(WorkbookResolverResult result) {
        return new WorkbookResolverResultAssertions(result);
    }

    public WorkbookResolverResultAssertions rejected() {
        Assertions.assertThat(actual.rejected()).describedAs("Expected the result to be rejected, but had 0 errors").isTrue();
        return this;
    }

    public WorkbookResolverResultAssertions accepted() {
        Assertions.assertThat(actual.accepted()).describedAs("Expected the result to be accepted, but had " + actual.getErrors().size() + " validation errors: " + Arrays.toString(actual.getErrors().toArray())).isTrue();
        return this;
    }

    public WorkbookResolverResultAssertions withErrors(String... errors) {
        Assertions.assertThat(errors.length).describedAs("Expected " + errors.length + " errors but found " + actual.getErrors().size() + " errors: " + Arrays.toString(actual.getErrors().toArray())).isEqualTo(actual.getErrors().size());
        for (String error : errors) {
            Assertions.assertThat(actual.getErrors()).contains(error);
        }
        return this;
    }

}
