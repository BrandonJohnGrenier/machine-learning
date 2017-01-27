package ai.brandon.commons;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public final class BigDecimals {

    private BigDecimals() {

    }

    public static List<BigDecimal> arrayToList(BigDecimal... inputs) {
        return Arrays.asList(inputs);
    }

    public static BigDecimal[] listToArray(List<BigDecimal> inputs) {
        return inputs.toArray(new BigDecimal[inputs.size()]);
    }

    public static <T> List<BigDecimal> toBigDecimalList(List<T> inputs) {
        return inputs.stream().map(input -> new BigDecimal(input.toString())).collect(Collectors.toList());
    }

    public static <T> List<BigDecimal> toBigDecimalList(T... inputs) {
        List<BigDecimal> list = new ArrayList<BigDecimal>();
        for (T input : inputs) {
            list.add(new BigDecimal(input.toString()));
        }
        return list;
    }

    public static <T> BigDecimal[] toBigDecimalArray(T... inputs) {
        BigDecimal[] array = new BigDecimal[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            array[i] = new BigDecimal(inputs[i].toString());
        }
        return array;
    }

}
