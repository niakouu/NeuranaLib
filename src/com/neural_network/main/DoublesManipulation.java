package com.neural_network.main;

public abstract class DoublesManipulation {

  public DoublesManipulation() {
  }

  public static double[][] sigmoid(double[][] arr) {
    for (int row = 0; row < arr.length; ++row) {
      for (int j = 0; j < arr[row].length; ++j) {
        arr[row][j] = sigmoidForEach(arr[row][j]);
      }
    }
    return arr;
  }

  public static double[][] multiplyMatrix(double[][] arr1, double[][] arr2) {
    int rows = arr1.length;
    int columns = arr2[0].length;
    double[][] result = new double[rows][columns];

    for (int i = 0; i < result.length; ++i) {
      int counterColumns = 0;

      for (int j = 0; j < result[i].length; ++j) {
        result[i][j] = multiplyRowByColumn(arr1[i], arr2, counterColumns++);
      }
    }

    return result;
  }

  public static double[][] multiplyElementByElement(double[][] arr1, double[][] arr2,
      double[][] arr3) {
    double[][] result = new double[arr1.length][arr1[0].length];
    for (int i = 0; i < result.length; ++i) {
      for (int j = 0; j < result[i].length; ++j) {
        result[i][j] = arr1[i][j] * arr2[i][j] * arr3[i][j];
      }
    }
    return result;
  }

  public static double[][] multiplyElementByElement(double num, double[][] arr) {
    double[][] result = new double[arr.length][arr[0].length];
    for (int i = 0; i < result.length; ++i) {
      for (int j = 0; j < result[i].length; ++j) {
        result[i][j] = num * arr[i][j];
      }
    }
    return result;
  }

  public static double[][] transpose(double[][] arr) {
    double[][] result = new double[arr[0].length][arr.length];
    for (int i = 0; i < arr.length; ++i) {
      for (int j = 0; j < arr[i].length; ++j) {
        result[j][i] = arr[i][j];
      }
    }
    return result;
  }

  public static double[][] subtractMatrix(double[][] minuend, double[][] subtrahend) {
    double[][] result = new double[minuend.length][minuend[0].length];
    for (int i = 0; i < result.length; ++i) {
      for (int j = 0; j < result[i].length; ++j) {
        result[i][j] = minuend[i][j] - subtrahend[i][j];
      }
    }
    return result;
  }

  public static double[][] subtractMatrix(double minuend, double[][] subtrahend) {
    double[][] result = new double[subtrahend.length][subtrahend[0].length];
    for (int i = 0; i < result.length; ++i) {
      for (int j = 0; j < result[i].length; ++j) {
        result[i][j] = 1.0 - subtrahend[i][j];
      }
    }
    return result;
  }

  private static double sigmoidForEach(double x) {
    return 1.0 / (1.0 + Math.pow(Math.E, -1.0 * x));
  }

  private static double multiplyRowByColumn(double[] row, double[][] input, int column) {
    double total = 0.0;
    int counterColumnForRow = 0;
    double[][] result = input;
    int size = input.length;
    for (int i = 0; i < size; ++i) {
      double[] inputRow = result[i];
      total += inputRow[column] * row[counterColumnForRow++];
    }
    return total;
  }
}
