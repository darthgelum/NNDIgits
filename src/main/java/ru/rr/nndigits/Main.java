package ru.rr.nndigits;

import java.util.function.UnaryOperator;

public class Main {

    public
    static void main(String[] args) {
	// write your code here
        UnaryOperator<Double> sigmoid = x -> 1 / (1 + Math.exp(-x));
        UnaryOperator<Double> d_sigmoid = y -> y * (1 - y);
        NeuralNetwork nn = new NeuralNetwork(0.001, sigmoid, d_sigmoid, 1,1);

        System.out.println("Hell!");
    }
}
