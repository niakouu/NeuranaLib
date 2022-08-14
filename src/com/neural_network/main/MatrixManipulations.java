package com.neural_network.main;

public abstract class MatrixManipulations {

  public MatrixManipulations() {
  }

  public static double[][] sigmoid(double[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        arr[i][j] = sigmoidForEach(arr[i][j]);
      }
    }
    return arr;
  }

  public static double[][] multiplyMatrix(double[][] arr1, double[][] arr2) {
    int column = 0;
    int rows = 0;
    double[][] moreRows;
    double[][] lessRows;
    if (arr1.length > arr2.length && arr2.length == arr1[0].length) {
      moreRows = arr1;
      lessRows = arr2;
      column = moreRows[0].length;
      rows = lessRows.length;
    }
    else if (arr1.length < arr2.length && arr2.length == arr1[0].length) {
      moreRows = arr2;
      lessRows = arr1;
      column = moreRows[0].length;
      rows = lessRows.length;
    }
    else {
      if (arr1.length == arr2[0].length) {
        moreRows = arr2;
        lessRows = arr1;
        column = moreRows[0].length;
        rows = lessRows.length;
      }
      else if (arr2.length == arr1[0].length) {
        moreRows = arr1;
        lessRows = arr2;
        column = moreRows[0].length;
        rows = lessRows.length;
      }
      else if (arr2[0].length == arr1[0].length) {
        if (arr2.length < arr1.length) {
          moreRows = arr2;
          lessRows = transpose(arr1);
          column = moreRows[0].length;
          rows = lessRows.length;
        }
        else {
          moreRows = arr1;
          lessRows = transpose(arr2);
          column = moreRows[0].length;
          rows = lessRows.length;
        }
      }
      else {
        return null;
      }
    }
    double[][] result = new double[rows][column];
    for (int i = 0; i < result.length; i++) {
      int counterColumns = 0;
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = multiplyRowByColumn(lessRows[i], moreRows, counterColumns++);
      }
    }
    return result;
  }

  public static double[][] multiplyMatrix(Matrix m1, Matrix m2) {
    Matrix[] arr = m1.makeRowOfFirstMatrixBeEqualToColumnOfSecondMatrix(m2);
    int rows = arr[0].getRows();
    int column = arr[1].getColumn();
    double[][] lessRows = arr[0].getInputs();
    double[][] moreRows = arr[1].getInputs();
    double[][] result = new double[rows][column];
    for (int i = 0; i < result.length; i++) {
      int counterColumns = 0;
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = multiplyRowByColumn(lessRows[i], moreRows, counterColumns++);
      }
    }
    return result;
  }

  public static double[][] multiplyElementByElement(double[][] arr1, double[][] arr2,
      double[][] arr3) {
    double[][] result = new double[arr1.length][arr1[0].length];
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = arr1[i][j] * arr2[i][j] * arr3[i][j];
      }
    }
    return result;
  }

  public static double[][] multiplyElementByElement(double num, double[][] arr) {
    double[][] result = new double[arr.length][arr[0].length];
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = num * arr[i][j];
      }
    }
    return result;
  }

  public static double[][] transpose(double[][] arr) {
    double[][] result = new double[arr[0].length][arr.length];
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        result[j][i] = arr[i][j];
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

  public static double[][] subtractMatrix(double minuend, double[][] subtrahend) {
    double[][] result = new double[subtrahend.length][subtrahend[0].length];
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = minuend - subtrahend[i][j];
      }
    }
    return result;
  }

  public static double[][] structure1dimensionalTo2dimensional(int rows, int columns,
      double[] oneDimensional) {
    double[][] weights = new double[rows][columns];
    try{
      int rowsCounter = 0;
      int columnsCounter = 0;
      for (double weight : oneDimensional) {
        if (rowsCounter == rows && columnsCounter == columns) {
          return weights;
        } else if (columnsCounter == columns) {
          columnsCounter = 0;
          weights[++rowsCounter][columnsCounter++] = weight;
        } else {
          weights[rowsCounter][columnsCounter++] = weight;
        }
      }
    }catch (IndexOutOfBoundsException e) {
      return weights;
    }

    return weights;
  }

  private static double sigmoidForEach(double x) {
    return 1.0 / (1.0 + Math.pow(Math.E, -1.0 * x));
  }

  private static double multiplyRowByColumn(double[] row, double[][] input, int column) {
    double total = 0.0;
    int counterColumnForRow = 0;
    if (row.length == 1) {
      for (double[] inputRow : input) {
        total += inputRow[column] * row[0];
        return total;
      }
    }
    for (double[] inputRow : input) {
      total += inputRow[column] * row[counterColumnForRow++];
    }
    return total;
  }

}
