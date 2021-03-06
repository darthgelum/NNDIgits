package ru.rr.nndigits;

import java.util.ArrayList;
import java.util.List;

public class NeuronLayer {
    public LayerNames Name;
    public int NCounts;
    public ArrayList<Neuron> Neurons;
    public NeuronLayer(int size, LayerNames name, NNConfig conf, NeuronLayer previous)
    {
        Neurons = new ArrayList<>();
        Name = name;
        NCounts = size;
        for(int i = 0;i<size; i++)
        {
            var neuron = new Neuron(conf, previous!=null ? previous.Neurons : null);
            Neurons.add(neuron);
        }
    }
    public void setOutputs(ArrayList<Neuron> oNeurons)
    {
        for (var neuron : Neurons)
        {
            neuron.Outputs = oNeurons;
        }
    }

    public void proceedData()
    {
        for(var neuron: Neurons)
        {
            neuron.proceedData();
        }
    }
}
