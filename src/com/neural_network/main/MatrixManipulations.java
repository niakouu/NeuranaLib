package com.neural_network.main;

public abstract class MatrixManipulations {

  public static Matrix sigmoid(Matrix matrix) {
    double[][] arr = matrix.getData();
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        arr[i][j] = sigmoidForEach(arr[i][j]);
      }
    }
    return new Matrix(arr);
  }

  public static Matrix addMatrix(Matrix m1, Matrix m2) {
    double[][] result = new double[m1.getRows()][m1.getColumn()];
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = m1.getData()[i][j] + m2.getData()[i][j];
      }
    }
    return new Matrix(result);
  }

  public static Matrix multiplyMatrix(Matrix m1, Matrix m2) {
    Matrix[] arr = makeRowOfFirstMatrixBeEqualToColumnOfSecondMatrix(m1, m2);
    int rows = arr[0].getRows();
    int column = arr[1].getColumn();
    double[][] lessRows = arr[0].getData();
    double[][] moreRows = arr[1].getData();
    double[][] result = new double[rows][column];
    for (int i = 0; i < result.length; i++) {
      int counterColumns = 0;
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = multiplyRowByColumn(lessRows[i], moreRows, counterColumns++);
      }
    }
    return new Matrix(result);
  }

  public static Matrix multiplyElementByElement(Matrix arr1, Matrix arr2, Matrix arr3) {
    double[][] result = new double[arr1.getRows()][arr1.getColumn()];
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = arr1.getData()[i][j] * arr2.getData()[i][j] * arr3.getData()[i][j];
      }
    }
    return new Matrix(result);
  }

  public static Matrix multiplyElementByElement(double num, Matrix arr) {
    double[][] result = new double[arr.getRows()][arr.getColumn()];
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = num * arr.getData()[i][j];
      }
    }
    return new Matrix(result);
  }

  public static Matrix transpose(Matrix matrix) {
    double[][] result = new double[matrix.getColumn()][matrix.getRows()];
    for (int i = 0; i < matrix.getRows(); i++) {
      for (int j = 0; j < matrix.getColumn(); j++) {
        result[j][i] = matrix.getData()[i][j];
      }
    }
    return new Matrix(result);
  }

  public static Matrix subtractMatrix(Matrix minuend, Matrix subtrahend) {
    double[][] result = new double[minuend.getRows()][minuend.getColumn()];
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = minuend.getData()[i][j] - subtrahend.getData()[i][j];
      }
    }
    return new Matrix(result);
  }

  public static Matrix subtractMatrix(double minuend, Matrix subtrahend) {
    double[][] result = new double[subtrahend.getRows()][subtrahend.getColumn()];
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        result[i][j] = minuend - subtrahend.getData()[i][j];
      }
    }
    return new Matrix(result);
  }

  public static Matrix structure1dimensionalTo2dimensional(int rows, int columns,
      double[] oneDimensional) {
    double[][] weights = new double[rows][columns];
    try {
      int rowsCounter = 0;
      int columnsCounter = 0;
      for (double weight : oneDimensional) {
        if (rowsCounter == rows && columnsCounter == columns) {
          return new Matrix(weights);
        } else if (columnsCounter == columns) {
          columnsCounter = 0;
          weights[++rowsCounter][columnsCounter++] = weight;
        } else {
          weights[rowsCounter][columnsCounter++] = weight;
        }
      }
    } catch (IndexOutOfBoundsException e) {
      return new Matrix(weights);
    }
    return new Matrix(weights);
  }

  private static Matrix[] makeRowOfFirstMatrixBeEqualToColumnOfSecondMatrix(Matrix m1, Matrix m2) {
    if (twoSidesAreSimilar(m1, m2) && m2.getColumn() == m1.getRows()) {
      return new Matrix[]{m1, m2};
    } else if (twoSidesAreSimilar(m1, m2) && m1.getColumn() == m2.getColumn()) {
      return new Matrix[]{m1, transpose(m2)};
    } else if (m1.getColumn() == m2.getRows() && isInsideNumberSmallerThenTheOutside(m1.getRows(),
        m2.getColumn(), m2.getRows())) {
      return new Matrix[]{m2.getColumn() > m1.getRows() ? transpose(m1) : transpose(m2),
          m2.getColumn() > m1.getRows() ? transpose(m2) : transpose(m1)};
    } else if (m1.getColumn() == m2.getRows()) {
      return new Matrix[]{m2.getColumn() > m1.getRows() ? m1 : m2,
          m2.getColumn() > m1.getRows() ? m2 : m1};

    } else if (m1.getColumn() == m2.getColumn() && isInsideNumberSmallerThenTheOutside(m1.getRows(),
        m2.getRows(), m2.getColumn())) {
      return new Matrix[]{m2.getRows() > m1.getRows() ? m1 : transpose(m2),
          m2.getRows() > m1.getRows() ? transpose(m2) : m1};

    } else if (m1.getColumn() == m2.getColumn()) {
      return new Matrix[]{m2.getRows() > m1.getRows() ? transpose(m2) : m1,
          m2.getRows() > m1.getRows() ? m1 : transpose(m2)};

    } else if (m1.getRows() == m2.getColumn() && isInsideNumberSmallerThenTheOutside(m1.getColumn(),
        m2.getRows(), m2.getColumn())) {
      return new Matrix[]{m2.getRows() > m1.getColumn() ? transpose(m2) : transpose(m1),
          m2.getRows() > m1.getColumn() ? transpose(m1) : transpose(m2)};

    } else if (m1.getRows() == m2.getColumn()) {
      return new Matrix[]{m2.getRows() > m1.getColumn() ? m1 : m2,
          m2.getRows() > m1.getColumn() ? m2 : m1};

    } else if (m1.getRows() == m2.getRows() && isInsideNumberSmallerThenTheOutside(m1.getColumn(),
        m2.getColumn(), m2.getRows())) {
      return new Matrix[]{m2.getColumn() > m1.getColumn() ? transpose(m2) : transpose(m1),
          m2.getColumn() > m1.getColumn() ? m1 : m2};

    } else if (m1.getRows() == m2.getRows()) {
      return new Matrix[]{m2.getColumn() > m1.getColumn() ? m2 : transpose(m1),
          m2.getColumn() > m1.getColumn() ? transpose(m1) : m2};
    }
    return null;
  }

  private static boolean isInsideNumberSmallerThenTheOutside(int outside1, int outside2,
      int inside) {
    return outside1 > inside || outside2 > inside;
  }

  private static boolean twoSidesAreSimilar(Matrix m1, Matrix m2) {
    return m1.getColumn() == m2.getRows() && m1.getRows() == m2.getColumn()
        || m1.getRows() == m2.getRows() && m1.getColumn() == m2.getColumn();
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
