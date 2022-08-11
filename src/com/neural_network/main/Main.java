package com.neural_network.main;

import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    NeuralNetwork n = new NeuralNetwork();
    n.getWeightsInputToHidden();
    n.query(Arrays.asList(2.0, 9.0, 5.0));
  }
}
