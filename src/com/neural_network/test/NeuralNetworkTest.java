package com.neural_network.test;

import com.neural_network.main.*;
import org.junit.jupiter.api.*;
import java.util.List;
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
    for (double[] weight : new NeuralNetwork().getWeightsHiddenToOutput().getData()) {
      for (double w : weight) {
        assertTrue(w > -0.5 || w < 0.5);
      }
    }

    for (double[] weight : new NeuralNetwork().getWeightsInputToHidden().getData()) {
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
        MatrixManipulations.multiplyMatrix(new Matrix(arr1), new Matrix(arr2)).getData());
    double[][] ar1 = {{1, 2}, {3, 4}};
    double[][] ar2 = {{5, 6}, {7, 8}};
    Assertions.assertArrayEquals(new double[][]{{19, 22}, {43, 50}},
        MatrixManipulations.multiplyMatrix(new Matrix(ar1), new Matrix(ar2)).getData());
  }

  @Test
  public void testQuery() {
    NeuralNetwork n = new NeuralNetwork(784, 100, 10);
    n.trainData(new Dataset("mnist_dataset/mnist_train_100.csv", 784), 10);
    Dataset d = new Dataset("mnist_dataset/mnist_test_10.csv", 784);
    List<Matrix> test = n.query(d);
    float performance = d.appendCorrectOrIncorrect(test);
    System.out.println("performance = " + performance);

    NeuralNetwork n2 = new NeuralNetwork(784, 100, 10, 0.3f);
    n2.trainData(new Dataset("mnist_dataset/mnist_train.csv", 784), 1);
    Dataset d2 = new Dataset("mnist_dataset/mnist_test.csv", 784);
    test = n2.query(d2);
    performance = d2.appendCorrectOrIncorrect(test);
    System.out.println("performance = " + performance);
  }

  @Test
  public void testSubMatrix() {
    Assertions.assertArrayEquals(new double[][]{{1, -1, 3}, {-1, 0, 2}},
        MatrixManipulations.subtractMatrix(new Matrix(new double[][]{{3, 4, 4}, {5, 4, 6}}),
            new Matrix(new double[][]{{2, 5, 1}, {6, 4, 4}})).getData());
  }

  @Test
  public void testTranspose() {
    Assertions.assertArrayEquals(new double[][]{{1, -1, 3}, {-1, 0, 2}},
        MatrixManipulations.transpose(new Matrix(new double[][]{{1, -1}, {-1, 0}, {3, 2}}))
            .getData());
  }

  @Test
  public void testTrain() {
    NeuralNetwork n = new NeuralNetwork(784, 100, 10);
    n.trainData(new Dataset("mnist_dataset/mnist_train_100.csv", 784), 1);
  }

  @Test
  public void testDatasetInputs() {
    double[][] data = new Dataset("mnist_dataset/mnist_train_100.csv", 785).getRawData();
    for (double[] i : data) {
      for (double j : i) {
        System.out.print(j);
      }
      System.out.println();
    }
  }

  @Test
  public void testDatasetHeaders() {
    List<Integer> d = new Dataset("mnist_dataset/mnist_train_100.csv", 785).getHeaders();
    System.out.println(d);
    assertEquals(100, d.size());
  }

  @Test
  public void testDatasetReshapedInputs() {
    double[][] inputs = new Dataset("mnist_dataset/mnist_train_100.csv",
        785).getInputs().getData();
    for (double[] row : inputs) {
      System.out.print("{ ");
      for (double num : row) {
        System.out.print(num + " ");
        assertTrue(num < 1.0 && num >= (double) 0);
      }
      System.out.println("}");
    }
    assertEquals(100, inputs.length);
  }
}