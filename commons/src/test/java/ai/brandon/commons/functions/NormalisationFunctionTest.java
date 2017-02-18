package ai.brandon.commons.functions;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import ai.brandon.commons.functions.NormalisationFunction;

public class NormalisationFunctionTest {

    @Test
    public void shouldBeAbleToNormaliseAnInput() {
        NormalisationFunction function = new NormalisationFunction(new BigDecimal("5"), new BigDecimal("2"));
        assertThat(function.normalise(5)).isEqualTo(new BigDecimal("0.0000000000"));
        assertThat(function.normalise(6)).isEqualTo(new BigDecimal("0.5000000000"));
        assertThat(function.normalise(4)).isEqualTo(new BigDecimal("-0.5000000000"));
        assertThat(function.normalise(0)).isEqualTo(new BigDecimal("-2.5000000000"));
    }

    @Test
    public void shouldDefaultToARangeOfOneIfTheRangeIsZero() {
        NormalisationFunction function = new NormalisationFunction(new BigDecimal("5"), new BigDecimal("0"));
        assertThat(function.normalise(5)).isEqualTo(new BigDecimal("0.0000000000"));
        assertThat(function.normalise(6)).isEqualTo(new BigDecimal("1.0000000000"));
        assertThat(function.normalise(4)).isEqualTo(new BigDecimal("-1.0000000000"));
        assertThat(function.normalise(0)).isEqualTo(new BigDecimal("-5.0000000000"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateANormalisationFunctionIfTheMeanIsNotProvided() {
        new NormalisationFunction(null, new BigDecimal("2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateANormalisationFunctionIfTheRangeIsNotProvided() {
        new NormalisationFunction(new BigDecimal("2"), null);
    }

}
