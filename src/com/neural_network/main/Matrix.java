package com.neural_network.main;

public class Matrix {

  private final int column;
  private final int rows;
  private final double[][] data;
  private final double[] firstRow;

  public Matrix(double[][] inputs) {
    this.column = inputs[0].length;
    this.rows = inputs.length;
    this.data = inputs;
    this.firstRow = inputs[0];
  }

  public int getColumn() {
    return this.column;
  }

  public int getRows() {
    return this.rows;
  }

  public double[][] getData() {
    return this.data;
  }

  public double[] getFirstRow() {
    return this.firstRow;
  }

  public void printMatrix() {
    for (double[] rows : this.data) {
      System.out.print("{ ");
      for (double data : rows) {
        System.out.print(data + " ");
      }
      System.out.println("}");
    }
  }

  public int getHighestValuePosition() {
    double highest = 0.0;
    int position = 0;
    for (int i = 0; i < getRows(); i++) {
      for (int j = 0; j < getColumn(); j++) {
        if (this.data[i][j] > highest) {
          highest = this.data[i][j];
          position = j;
        }
      }
    }
    return position;
  }
}
