package ru.rr.nndigits;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.UnaryOperator;

public class Neuron {
    public Hashtable<Neuron,Double> Inputs;
    public ArrayList<Neuron> Outputs;

    private final UnaryOperator<Double> activation;
    private final UnaryOperator<Double> derivative;
    private final Double learningRate;
    double value = 0;
    public Neuron(NNConfig conf,
                  @Nullable ArrayList<Neuron> inputs)
    {
        Inputs = new Hashtable<>();
        this.activation = conf.getActivation();
        this.derivative = conf.getDerivative();
        this.learningRate = conf.getLearningRate();
        if (inputs != null)
        for (var inputNeuron :
                inputs) {
            Inputs.put(inputNeuron,conf.getDefaultWeight());
        }
    }

    public void setOutputs(ArrayList<Neuron> outputs)
    {
        this.Outputs = outputs;
    }

    public
    void pushData(Double value, @Nullable  Neuron sender)
    {
        this.value+=sender==null?value:Inputs.get(sender)*value;
    }

    public void proceedData()
    {
        for(var neuron:Outputs)
        {
          neuron.pushData(activation.apply(value),this);
        }
        value = 0;
    }
}
