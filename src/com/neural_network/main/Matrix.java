package com.neural_network.main;

import java.util.ArrayList;

public class Matrix {

  private int column = 0;
  private int rows = 0;
  private double[][] inputs;

  public Matrix(double[][] inputs) {
    this.column = inputs[0].length;
    this.rows = inputs.length;
    this.inputs = inputs;
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

  public boolean oneSideEquals(Matrix anotherMatrix) {
    if (
        anotherMatrix.getColumn() == getRows() ||
            anotherMatrix.getColumn() == getColumn() ||
            anotherMatrix.getRows() == getColumn() ||
            anotherMatrix.getRows() == getRows()
    ) {
      return true;
    }
    return false;
  }

  public Matrix[] makeRowOfFirstMatrixBeEqualToColumnOfSecondMatrix(Matrix anotherMatrix) {
    if (anotherMatrix.getColumn() == getRows() && isInsideNumberSmallerThenTheOutside(
        anotherMatrix.getRows(), anotherMatrix.getColumn())) {
      return new Matrix[] {getColumn() > anotherMatrix.getRows() ? anotherMatrix : this,
                            getColumn() > anotherMatrix.getRows() ? this : anotherMatrix};

    } else if (anotherMatrix.getColumn() == getRows() && !isInsideNumberSmallerThenTheOutside(
        anotherMatrix.getRows(), getColumn())) {
      return new Matrix[] {getColumn() > anotherMatrix.getRows() ? this : anotherMatrix,
          getColumn() > anotherMatrix.getRows() ? anotherMatrix : this};

    } else if (anotherMatrix.getColumn() == getColumn() && isInsideNumberSmallerThenTheOutside(
        anotherMatrix.getRows(), getRows())) {
      return new Matrix[] {getRows() > anotherMatrix.getRows() ? anotherMatrix : transpose(this),
          getRows() > anotherMatrix.getRows() ? transpose(this) : anotherMatrix};

    } else if (anotherMatrix.getColumn() == getColumn() && !isInsideNumberSmallerThenTheOutside(
        anotherMatrix.getRows(), getRows())) {
      return new Matrix[] {getRows() > anotherMatrix.getRows() ? transpose(this) : anotherMatrix,
          getRows() > anotherMatrix.getRows() ? anotherMatrix : transpose(this)};

    } else if (anotherMatrix.getRows() == getColumn() && isInsideNumberSmallerThenTheOutside(
        anotherMatrix.getColumn(), getRows())) {
      return new Matrix[] {getRows() > anotherMatrix.getColumn() ? transpose(anotherMatrix) : transpose(this),
          getRows() > anotherMatrix.getColumn() ? transpose(this) : transpose(anotherMatrix)};

    } else if (anotherMatrix.getRows() == getColumn() && !isInsideNumberSmallerThenTheOutside(
        anotherMatrix.getColumn(), getRows())) {
      return new Matrix[] {getRows() > anotherMatrix.getColumn() ? transpose(this) : transpose(anotherMatrix),
          getRows() > anotherMatrix.getColumn() ? transpose(anotherMatrix) : transpose(this)};

    } else if (anotherMatrix.getRows() == getRows() && isInsideNumberSmallerThenTheOutside(
        anotherMatrix.getColumn(), getColumn())) {
      return new Matrix[] {getColumn() > anotherMatrix.getColumn() ? transpose(anotherMatrix) : this,
          getColumn() > anotherMatrix.getColumn() ? this : transpose(anotherMatrix)};

    } else if (anotherMatrix.getRows() == getRows() && !isInsideNumberSmallerThenTheOutside(
        anotherMatrix.getColumn(), getColumn())) {
      return new Matrix[] {getColumn() > anotherMatrix.getColumn() ? this : transpose(anotherMatrix),
          getColumn() > anotherMatrix.getColumn() ? transpose(anotherMatrix) : this};
    }
    return null;
  }

  public boolean isInsideNumberSmallerThenTheOutside(int side1, int side2) {
    if (side1 > 1 || side2 > 1) {
      return true;
    }
    return false;
  }

  public Matrix transpose(Matrix matrix) {
    double[][] arr = matrix.getInputs();
    double[][] result = new double[arr[0].length][arr.length];
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        result[j][i] = arr[i][j];
      }
    }
    return new Matrix(result);
  }
}
