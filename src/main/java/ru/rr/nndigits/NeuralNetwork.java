package ru.rr.nndigits;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class NeuralNetwork {
    final double defaultWeight = 0.5;
    private NeuronLayer InputLayer;
    private ArrayList<NeuronLayer> HiddenLayers;
    private NeuronLayer OutputLayer;
    private UnaryOperator<Double> activation;
    private UnaryOperator<Double> derivative;
    private Double learningRate;

    public
    NeuralNetwork(
            double learningRate,
            UnaryOperator<Double> activation,
            UnaryOperator<Double> derivative,
            int... sizes)
    {
        this.learningRate = learningRate;
        this.activation = activation;
        this.derivative = derivative;

        if(sizes.length>2)
            HiddenLayers = new ArrayList<>();

        for(int i = 0; i<sizes.length; i++)
        {
            var name = i==0              ?   LayerNames.Input:
                                  i==sizes.length-1 ?   LayerNames.Output:
                                                        LayerNames.Hidden;
            buildLayer(sizes[i], name);
        }
    }

    private
    void buildLayer(int size, LayerNames name)
    {
        switch (name){
            case Input:
                InputLayer = new NeuronLayer(size,name,defaultWeight,null);
                break;
            case Hidden:
                var previous = HiddenLayers.size()==0?InputLayer:HiddenLayers.get(HiddenLayers.size()-1);
                var newLayer = new NeuronLayer(size,name,defaultWeight,previous);
                HiddenLayers.add(newLayer);
                previous.setOutputs(newLayer.Neurons);
                break;
            case Output:
                previous = HiddenLayers.size()==0?InputLayer:HiddenLayers.get(HiddenLayers.size()-1);
                OutputLayer = new NeuronLayer(size,name,defaultWeight, previous);
                previous.setOutputs(OutputLayer.Neurons);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
    }
}
