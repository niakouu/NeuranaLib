package com.neural_network.test;

import com.neural_network.main.Dataset;
import com.neural_network.main.MatrixManipulations;
import com.neural_network.main.Matrix;
import com.neural_network.main.NeuralNetwork;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeuralNetworkTest {

  @Test
  public void testToString() {
    assertEquals("input nodes = 3; hidden nodes = 3; output nodes = 3; learning rate = 0.3",
        new NeuralNetwork().toString());
    assertEquals("input nodes = 10; hidden nodes = 10; output nodes = 10; learning rate = 0.2",
        new NeuralNetwork(10, 10, 10, 0.2f).toString());
    assertEquals("input nodes = 5; hidden nodes = 5; output nodes = 5; learning rate = 0.3",
        new NeuralNetwork(5, 5, 5).toString());
  }

  @Test
  public void testWeightSpecification() {
    for (double[] weight : new NeuralNetwork().getWeightsHiddenToOutput().getInputs()) {
      for (double w : weight) {
        assertTrue(w > -0.5 || w < 0.5);
      }
    }

    for (double[] weight : new NeuralNetwork().getWeightsInputToHidden().getInputs()) {
      for (double w : weight) {
        assertTrue(w > -0.5 || w < 0.5);
      }
    }
  }

  @Test
  public void testMultiplyMatrix() {
    double[][] arr1 = {{1, 2, 3}, {4, 5, 6}};
    double[][] arr2 = {{7, 8}, {9, 10}, {11, 12}};
    Assertions.assertArrayEquals(new double[][]{{58, 64}, {139, 154}},
        MatrixManipulations.multiplyMatrix(new Matrix(arr1), new Matrix(arr2)).getInputs());
    double[][] ar1 = {{1, 2}, {3, 4}};
    double[][] ar2 = {{5, 6}, {7, 8}};
    Assertions.assertArrayEquals(new double[][]{{19, 22}, {43, 50}},
        MatrixManipulations.multiplyMatrix(new Matrix(ar1), new Matrix(ar2)).getInputs());
  }

  @Test
  public void testQuery() {
    Matrix d = new NeuralNetwork().query(new Matrix(new double[][]{{2.0}, {9.0}, {5.0}}));
    Arrays.stream(d.getInputs())
        .forEach(x -> Arrays.stream(x)
            .forEach(y -> System.out.println(y + " ")));
  }

  @Test
  public void testSubMatrix() {
    Assertions.assertArrayEquals(new double[][]{{1, -1, 3}, {-1, 0, 2}},
        MatrixManipulations.subtractMatrix(
            new Matrix(new double[][]{{3, 4, 4}, {5, 4, 6}}),
            new Matrix(new double[][]{{2, 5, 1}, {6, 4, 4}}
        )).getInputs());
  }

  @Test
  public void testTranspose() {
    Assertions.assertArrayEquals(new double[][]{{1, -1, 3}, {-1, 0, 2}},
        MatrixManipulations.transpose(
            new Matrix(new double[][]{{1, -1}, {-1, 0}, {3, 2}})
        ).getInputs());
  }

  @Test
  public void testTrain() {
    NeuralNetwork n = new NeuralNetwork(784, 100, 10);
    n.trainData(new Dataset("mnist_dataset/mnist_train_100.csv", 785));
  }

  @Test
  public void testDatasetInputs() {
    double[][] data = new Dataset("mnist_dataset/mnist_train_100.csv", 785).getInputs();
    for(double[] i : data) {
      for (double j : i) {
        System.out.print(j);
      }
      System.out.println();
    }
  }

  @Test
  public void testDatasetHeaders() {
    List<Double> d = new Dataset("mnist_dataset/mnist_train_100.csv", 785).getHeaders();
    System.out.println(d);
    assertEquals(100, d.size());
  }

  @Test
  public void testDatasetReshapedInputs() {
    double[][] inputs = new Dataset("mnist_dataset/mnist_train_100.csv", 785).getReshapedInputsMatrix().getInputs();
      for (double[] row : inputs) {
        System.out.print("{ ");
        for (double num : row ){
          System.out.print(num + " ");
          assertTrue(num < 1.0 && num >= (double) 0);
        }
        System.out.println("}");
      }
    assertEquals(100, inputs.length);
  }
}