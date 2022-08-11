package com.neural_network.test;

import com.neural_network.main.NeuralNetwork;
import java.util.ArrayList;
import java.util.List;
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
  public void testQuery(){

  }

}