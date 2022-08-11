package com.neural_network.main;

public abstract class Calculator {
  public static double sigmoid(double x) {
    return 1 / (1 + Math.pow(Math.E, -1*x));
  }

  public double[][] multiplyMatrix(double[][] arr1, double[][] arr2) {
    return new double[2][5];
  }
}
