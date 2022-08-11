package com.neural_network.main;

import java.util.Random;
import java.util.stream.DoubleStream;

public class Main {

  public static void main(String[] args) {
    for (double[] weight : new NeuralNetwork().getWeightsHiddenToOutput()) {
      for (double w : weight){
        System.out.print(w + ", ");
      }
      System.out.print("\n");
    }
  }
}
