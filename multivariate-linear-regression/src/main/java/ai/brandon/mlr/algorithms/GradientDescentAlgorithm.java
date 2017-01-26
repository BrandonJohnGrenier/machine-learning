package ai.brandon.mlr.algorithms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ai.brandon.mlr.common.ErrorFunction;
import ai.brandon.mlr.functions.LinearFunction;
import ai.brandon.mlr.functions.SquaredErrorCostFunction;
import ai.brandon.mlr.model.TrainingSet;

public class GradientDescentAlgorithm<T> {

    private final TrainingSet<T> set;
    private final SquaredErrorCostFunction<T> costFunction;

    private Double tolerance = 1E-9;
    private Double alpha = 0.100;

    public GradientDescentAlgorithm(TrainingSet<T> set) {
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
        List<BigDecimal> thetas = new ArrayList<BigDecimal>();
        List<BigDecimal> tempThetas = new ArrayList<BigDecimal>();
        BigDecimal cost = new BigDecimal(100.0), tempCost = new BigDecimal(100.0);

        Double convergence = new Double(100.0);

        for (int i = 0; i < set.featureCount() + 1; i++) {
            thetas.add(new BigDecimal(0.0));
            tempThetas.add(new BigDecimal(0.0));
        }

        while (convergence > tolerance) {
            for (int i = 0; i < thetas.size(); i++) {
                tempThetas.set(i, calculateTheta(thetas.get(i), i, thetas.toArray(new BigDecimal[thetas.size()])));
            }

            cost = costFunction.at(thetas.toArray(new BigDecimal[thetas.size()]));
            tempCost = costFunction.at(tempThetas.toArray(new BigDecimal[thetas.size()]));

            alpha = (tempCost.doubleValue() > cost.doubleValue()) ? alpha / 2 : alpha + 0.02;
            convergence = Math.abs(tempCost.doubleValue() - cost.doubleValue());

            System.out.println("alpha = " + alpha + ", cost = " + cost);

            for (int i = 0; i < tempThetas.size(); i++) {
                thetas.set(i, tempThetas.get(i));
                System.out.println("theta(" + i + ") = " + tempThetas.get(i));
            }
        }

        return new LinearFunction<T>(thetas.toArray(new BigDecimal[thetas.size()]));
    }

    public BigDecimal calculateTheta(BigDecimal targetTheta, Integer index, BigDecimal... thetas) {
        LinearFunction<T> function = new LinearFunction<T>(thetas);
        BigDecimal vector = ErrorFunction.averageOfErrors2(function, set, index).multiply(new BigDecimal(alpha.toString()));
        return new BigDecimal(targetTheta.toString()).subtract(vector);
    }

}
