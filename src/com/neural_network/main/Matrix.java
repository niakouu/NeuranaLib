package com.neural_network.main;

import java.util.Arrays;
import java.util.stream.Stream;

public class Matrix {

  private final int column;
  private final int rows;
  private final double[][] inputs;
  private final double[] firstRow;

  public Matrix(double[][] inputs) {
    this.column = inputs[0].length;
    this.rows = inputs.length;
    this.inputs = inputs;
    this.firstRow = inputs[0];
  }

  public int getColumn() {
    return this.column;
  }

  public int getRows() {
    return this.rows;
  }

  public double[][] getInputs() {
    return this.inputs;
  }

  public double[] getFirstRow() {
    return this.firstRow;
  }

  public void printMatrix() {
    for (double[] rows : this.inputs) {
      System.out.print("{ ");
      for (double data : rows) {
        System.out.print(data + " ");
      }
      System.out.println("}");
    }
  }
}
