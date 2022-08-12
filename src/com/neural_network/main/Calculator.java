package com.neural_network.main;

public abstract class Calculator {

  public static double[][] sigmoid(double[][] arr) {
    for (int row = 0; row < arr.length; row++) {
      for (int j = 0; j < arr[row].length; j++) {
        arr[row][j] = sigmoidForEach(arr[row][j]);
      }
    }
    return arr;
  }

  public static double[][] multiplyMatrix(double[][] arr1, double[][] arr2) {
    int rows = arr1.length;
    int columns = arr2[0].length;

    double[][] result = new double[rows][columns];
    for (int i = 0; i < result.length; i++) {
      int counterColumns = 0;
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = multiplyRowByColumn(arr1[i], arr2, counterColumns++);
      }
    }
    return result;
  }

  public static double[][] subtractMatrix(double[][] minuend, double[][] subtrahend) {
    double[][] result = new double[minuend.length][minuend[0].length];
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = minuend[i][j] - subtrahend[i][j];
      }
    }
    return result;
  }

  private static double sigmoidForEach(double x) {
    return 1 / (1 + Math.pow(Math.E, -1 * x));
  }

  private static double multiplyRowByColumn(double[] row, double[][] input, int column) {
    double total = 0.0;
    int counterColumnForRow = 0;
    for (double[] inputRow : input) {
      total += inputRow[column] * row[counterColumnForRow++];
    }
    return total;
  }
}
