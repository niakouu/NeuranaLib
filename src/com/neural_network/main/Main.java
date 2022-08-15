package com.neural_network.main;

import java.awt.Frame;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    NeuralNetwork n = new NeuralNetwork(784, 100, 10);
    n.trainData(new Dataset("C:/Users/edeli/OneDrive/Desktop/mnist_train.csv", 784));
    List<Matrix> test = n.query(new Dataset("C:/Users/edeli/OneDrive/Desktop/mnist_test.csv", 784));
    test.forEach(x -> x.printMatrix());
  }
}
