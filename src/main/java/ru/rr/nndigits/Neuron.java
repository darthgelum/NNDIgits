package ru.rr.nndigits;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Neuron {
    public Hashtable<Neuron,Double> Inputs;
    public ArrayList<Neuron> Outputs;

    public
    Neuron(double defaultWeight,@Nullable ArrayList<Neuron> inputs)
    {
        Inputs = new Hashtable<>();
        if (inputs != null)
        for (var inputNeuron :
                inputs) {
            Inputs.put(inputNeuron,defaultWeight);
        }
    }

    public
    void setOutputs(ArrayList<Neuron> outputs)
    {
        this.Outputs = outputs;
    }
}
