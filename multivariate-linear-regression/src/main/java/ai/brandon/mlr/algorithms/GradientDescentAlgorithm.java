package ai.brandon.mlr.algorithms;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ai.brandon.commons.BigDecimals;
import ai.brandon.mlr.functions.SquaredErrorPartialDerivative;
import ai.brandon.mlr.functions.LinearFunction;
import ai.brandon.mlr.functions.SquaredErrorCostFunction;
import ai.brandon.mlr.model.SupervisedTrainingSet;

public class GradientDescentAlgorithm<T> {

    private final SupervisedTrainingSet<T> set;
    private final SquaredErrorCostFunction<T> costFunction;

    private Double tolerance = 1E-9;
    private Double alpha = 0.100;

    public GradientDescentAlgorithm(SupervisedTrainingSet<T> set) {
        this.set = set;
        this.costFunction = new SquaredErrorCostFunction<T>(this.set);
    }

    public Double getTolerance() {
        return tolerance;
    }

    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public LinearFunction<T> run() {
        List<BigDecimal> thetas = initialise();
        List<BigDecimal> tempThetas = initialise();
        BigDecimal cost = new BigDecimal(100.0), tempCost = new BigDecimal(100.0);

        Double convergence = new Double(100.0);

        while (convergence > tolerance) {
            IntStream.range(0, thetas.size()).forEach(i -> tempThetas.set(i, calculateTheta(i, thetas)));

            cost = costFunction.at(BigDecimals.listToArray(thetas));
            tempCost = costFunction.at(BigDecimals.listToArray(tempThetas));

            alpha = (tempCost.doubleValue() > cost.doubleValue()) ? alpha / 2 : alpha + 0.02;
            convergence = Math.abs(tempCost.doubleValue() - cost.doubleValue());

            IntStream.range(0, tempThetas.size()).forEach(i -> thetas.set(i, tempThetas.get(i)));
        }

        return new LinearFunction<T>(thetas.toArray(new BigDecimal[thetas.size()]));
    }

    private List<BigDecimal> initialise() {
        return IntStream.range(0, set.getFeatureCount() + 1).mapToObj(i -> new BigDecimal(0.0)).collect(Collectors.toList());
    }

    private BigDecimal calculateTheta(Integer index, List<BigDecimal> thetas) {
        LinearFunction<T> function = new LinearFunction<T>(BigDecimals.listToArray(thetas));
        BigDecimal vector = SquaredErrorPartialDerivative.calculate(function, set, index).multiply(new BigDecimal(alpha.toString()));
        return new BigDecimal(thetas.get(index).toString()).subtract(vector);
    }

}
