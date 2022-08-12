package com.neural_network.main;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    NeuralNetwork n = new NeuralNetwork();
    n.getWeightsInputToHidden();
    double[][] d = n.query(Arrays.asList(2.0, 9.0, 5.0));
    Arrays.stream(d)
        .forEach(x -> Arrays.stream(x)
            .forEach(y -> System.out.println(y + " ")));
  }
}
