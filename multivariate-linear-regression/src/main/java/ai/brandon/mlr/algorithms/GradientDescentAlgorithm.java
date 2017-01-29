package ai.brandon.mlr.algorithms;

import static java.util.stream.IntStream.range;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.brandon.commons.BigDecimals;
import ai.brandon.commons.MinimisableFunction;
import ai.brandon.commons.model.SupervisedTrainingSet;
import ai.brandon.mlr.functions.SquaredErrorCostFunction;
import ai.brandon.mlr.functions.SquaredErrorPartialDerivative;

public class GradientDescentAlgorithm<T> {

    private static final Logger logger = LoggerFactory.getLogger(GradientDescentAlgorithm.class);

    private final SupervisedTrainingSet<T> trainingSet;
    private final SquaredErrorCostFunction<T> costFunction;

    private Double tolerance = 1E-9;
    private Double convergence = 100.0;
    private Double alpha = 0.100;
    private BigDecimal cost;
    private Integer iterations;

    public GradientDescentAlgorithm(SupervisedTrainingSet<T> set) {
        this.trainingSet = set;
        this.costFunction = new SquaredErrorCostFunction<T>(this.trainingSet);
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

    public Integer getIterations() {
        return iterations;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public MinimisableFunction<T> minimise(MinimisableFunction<T> function) {
        List<BigDecimal> thetas = initialise();
        List<BigDecimal> cache = initialise();

        this.convergence = new Double(100.0);
        this.cost = new BigDecimal(100.0);
        this.iterations = 0;

        while (convergence > tolerance) {
            range(0, thetas.size()).forEach(i -> cache.set(i, calculateTheta(function, i, thetas)));

            this.cost = costFunction.at(BigDecimals.listToArray(thetas));
            BigDecimal tempCost = costFunction.at(BigDecimals.listToArray(cache));

            this.alpha = (tempCost.doubleValue() > cost.doubleValue()) ? alpha / 2 : alpha + 0.02;
            this.convergence = Math.abs(tempCost.doubleValue() - cost.doubleValue());
            this.iterations += 1;

            range(0, cache.size()).forEach(i -> thetas.set(i, cache.get(i)));
            log(thetas);
        }

        return function.newInstance(BigDecimals.listToArray(thetas));
    }

    private List<BigDecimal> initialise() {
        return IntStream.range(0, trainingSet.getFeatureCount() + 1).mapToObj(i -> new BigDecimal(0.0)).collect(Collectors.toList());
    }

    private BigDecimal calculateTheta(MinimisableFunction<T> function, Integer index, List<BigDecimal> thetas) {
        MinimisableFunction<T> f = function.newInstance(BigDecimals.listToArray(thetas));
        BigDecimal vector = SquaredErrorPartialDerivative.calculate(f, trainingSet, index).multiply(new BigDecimal(alpha.toString()));
        return new BigDecimal(thetas.get(index).toString()).subtract(vector);
    }

    private void log(List<BigDecimal> thetas) {
        DecimalFormat df = new DecimalFormat("0.00000");
        String output = IntStream.range(0, thetas.size()).mapToObj(i -> "theta(" + i + ") = " + df.format(thetas.get(i).doubleValue())).collect(Collectors.joining(", "));
        logger.debug("iteration {}: alpha = {}, cost = {}, " + output, iterations, df.format(alpha), df.format(cost));
    }

}
