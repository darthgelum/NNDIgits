package ru.rr.nndigits;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class NeuralNetwork {
    private NNConfig conf;
    private NeuronLayer InputLayer;
    private ArrayList<NeuronLayer> HiddenLayers;
    private NeuronLayer OutputLayer;

    public NeuralNetwork(
            double learningRate,
            UnaryOperator<Double> activation,
            UnaryOperator<Double> derivative,
            int... sizes)
    {
        conf = new NNConfig(activation,derivative,learningRate,0.5);
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

    private void buildLayer(int size, LayerNames name)
    {
        switch (name){
            case Input:
                InputLayer = new NeuronLayer(size,name,conf,null);
                break;
            case Hidden:
                var previous = HiddenLayers.size()==0?InputLayer:HiddenLayers.get(HiddenLayers.size()-1);
                var newLayer = new NeuronLayer(size,name,conf,previous);
                HiddenLayers.add(newLayer);
                previous.setOutputs(newLayer.Neurons);
                break;
            case Output:
                previous = HiddenLayers==null?InputLayer:HiddenLayers.get(HiddenLayers.size()-1);
                OutputLayer = new NeuronLayer(size,name,conf, previous);
                previous.setOutputs(OutputLayer.Neurons);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
    }
    /*
    NN input data
     */
    public void input(double... values) {
        if(values.length > InputLayer.NCounts)
        {
            throw new IllegalStateException("Values counts greater than input layer");
        }
        for (int i=0; i< values.length; i++)
        {
            InputLayer.Neurons.get(i).pushData(values[i],null);
        }
    }

    public NeuronLayer calc()
    {
        InputLayer.proceedData();
        for(var layer:HiddenLayers)
        {
            layer.proceedData();
        }
        return OutputLayer;
    }
}
