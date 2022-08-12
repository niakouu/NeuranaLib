package com.neural_network.test;

import com.neural_network.main.Calculator;
import com.neural_network.main.NeuralNetwork;
import java.util.Arrays;
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
    for (double[] weight : new NeuralNetwork().getWeightsHiddenToOutput()) {
      for (double w : weight) {
        assertTrue(w > -0.5 || w < 0.5);
      }
    }

    for (double[] weight : new NeuralNetwork().getWeightsInputToHidden()) {
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
        Calculator.multiplyMatrix(arr1, arr2));
    double[][] ar1 = {{1, 2}, {3, 4}};
    double[][] ar2 = {{5, 6}, {7, 8}};
    Assertions.assertArrayEquals(new double[][]{{19, 22}, {43, 50}},
        Calculator.multiplyMatrix(ar1, ar2));
  }

  @Test
  public void testQuery() {
    double[][] d = new NeuralNetwork().query(Arrays.asList(2.0, 9.0, 5.0));
    Arrays.stream(d)
        .forEach(x -> Arrays.stream(x)
            .forEach(y -> System.out.println(y + " ")));
  }
}