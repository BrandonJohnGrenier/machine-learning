package ai.brandon.commons.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ai.brandon.commons.JSON;

public class NormalisationFunction {

    private final BigDecimal mean;
    private final BigDecimal range;

    public NormalisationFunction(BigDecimal mean, BigDecimal range) {
        if (mean == null || range == null) {
            throw new IllegalArgumentException("Cannot create normalisation function: mean and range cannot be null.");
        }
        this.mean = mean;
        this.range = range.intValue() == 0 ? new BigDecimal("1") : range;
    }

    public <T> BigDecimal normalise(T x) {
        return new BigDecimal(x.toString()).subtract(mean).divide(range, 10, RoundingMode.HALF_UP);
    }

    public String toString() {
        return JSON.stringify(this);
    }

}
