package ru.rr.nndigits;

import java.util.function.UnaryOperator;

public class NNConfig {
    private UnaryOperator<Double> activation;
    private UnaryOperator<Double> derivative;
    private Double learningRate;
    private Double defaultWeight;
    public NNConfig(UnaryOperator<Double> activation,
                    UnaryOperator<Double> derivative,
                    double learningRate, double defaultWeight)
    {
        this.activation = activation;
        this.derivative = derivative;
        this.learningRate = learningRate;
        this.defaultWeight = defaultWeight;
    }

    public UnaryOperator<Double> getActivation()
    {return activation;}
    public UnaryOperator<Double> getDerivative()
    {return derivative;}
    public Double getLearningRate()
    {return learningRate;}
    public Double getDefaultWeight()
    {return defaultWeight;}
}
