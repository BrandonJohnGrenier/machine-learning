package ai.brandon.mlr.functions;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ai.brandon.mlr.model.TrainingSet;

@SuppressWarnings("unchecked")
public class SquaredErrorCostFunction<T> {

    private final TrainingSet<T> set;

    public SquaredErrorCostFunction(TrainingSet<T> set) {
        this.set = set;
    }

    public BigDecimal at(T... inputs) {
        List<BigDecimal> list = (List<BigDecimal>) IntStream.range(0, inputs.length).mapToObj(i -> new BigDecimal(inputs[i].toString())).collect(Collectors.toList());
        return at(list.toArray(new BigDecimal[list.size()]));
    }

    public BigDecimal at(BigDecimal... inputs) {
        BigDecimal total = sumOfSquaredErrors(new LinearFunction<T>(inputs));
        return total.divide(new BigDecimal(set.size() * 2), 10, HALF_UP);
    }

    private BigDecimal sumOfSquaredErrors(LinearFunction<T> function) {
        return set.stream().map(ith -> function.at(ith.getFeatures().list()).subtract(new BigDecimal(ith.getTarget().toString())).pow(2)).reduce(ZERO, BigDecimal::add);
    }

}
