package ai.brandon.mlr.functions;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import ai.brandon.mlr.functions.LinearFunction;

public class LinearFunctionTest {

    @Test
    public void shouldBeAbleToCalculateTheValueOfALinearFunctionWithIntegerParameters() {
        assertThat(new LinearFunction<Integer>(1, 1).at(8)).isEqualTo(new BigDecimal("9"));
        assertThat(new LinearFunction<Integer>(1, 1).at(0)).isEqualTo(new BigDecimal("1"));
        assertThat(new LinearFunction<Integer>(0, 5).at(8)).isEqualTo(new BigDecimal("40"));
        assertThat(new LinearFunction<Integer>(0, 5).at(0)).isEqualTo(new BigDecimal("0"));
        assertThat(new LinearFunction<Integer>(5, 0).at(8)).isEqualTo(new BigDecimal("5"));
        assertThat(new LinearFunction<Integer>(5, 0).at(0)).isEqualTo(new BigDecimal("5"));

        assertThat(new LinearFunction<Integer>(-1, -1).at(8)).isEqualTo(new BigDecimal("-9"));
        assertThat(new LinearFunction<Integer>(-1, -1).at(0)).isEqualTo(new BigDecimal("-1"));
        assertThat(new LinearFunction<Integer>(0, -5).at(8)).isEqualTo(new BigDecimal("-40"));
        assertThat(new LinearFunction<Integer>(0, -5).at(0)).isEqualTo(new BigDecimal("0"));
        assertThat(new LinearFunction<Integer>(-5, 0).at(8)).isEqualTo(new BigDecimal("-5"));
        assertThat(new LinearFunction<Integer>(-5, 0).at(0)).isEqualTo(new BigDecimal("-5"));
    }

    @Test
    public void shouldBeAbleToCalculateTheValueOfALinearFunctionWithDoubleParameters() {
        assertThat(new LinearFunction<Double>(1.0, 1.0).at(8.0)).isEqualTo(new BigDecimal("9.00"));
        assertThat(new LinearFunction<Double>(1.0, 1.0).at(0.0)).isEqualTo(new BigDecimal("1.00"));
        assertThat(new LinearFunction<Double>(0.0, 5.0).at(8.0)).isEqualTo(new BigDecimal("40.00"));
        assertThat(new LinearFunction<Double>(0.0, 5.0).at(0.0)).isEqualTo(new BigDecimal("0.00"));
        assertThat(new LinearFunction<Double>(5.0, 0.0).at(8.0)).isEqualTo(new BigDecimal("5.00"));
        assertThat(new LinearFunction<Double>(5.0, 0.0).at(0.0)).isEqualTo(new BigDecimal("5.00"));

        assertThat(new LinearFunction<Double>(-1.0, -1.0).at(8.0)).isEqualTo(new BigDecimal("-9.00"));
        assertThat(new LinearFunction<Double>(-1.0, -1.0).at(0.0)).isEqualTo(new BigDecimal("-1.00"));
        assertThat(new LinearFunction<Double>(0.0, -5.0).at(8.0)).isEqualTo(new BigDecimal("-40.00"));
        assertThat(new LinearFunction<Double>(0.0, -5.0).at(0.0)).isEqualTo(new BigDecimal("0.00"));
        assertThat(new LinearFunction<Double>(-5.0, 0.0).at(8.0)).isEqualTo(new BigDecimal("-5.00"));
        assertThat(new LinearFunction<Double>(-5.0, 0.0).at(0.0)).isEqualTo(new BigDecimal("-5.00"));
    }

    @Test
    public void shouldBeAbleToCalculateTheValueOfALinearFunctionWithMultipleIntegerInputs() {
        System.out.println(new LinearFunction<Integer>(1, 2, 3));
        assertThat(new LinearFunction<Integer>(1, 2, 3).at(3, 3)).isEqualTo(new BigDecimal("16"));
    }

}
