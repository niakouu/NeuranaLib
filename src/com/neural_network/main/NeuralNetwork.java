package com.neural_network.main;

import java.util.Random;

public class NeuralNetwork {

  private static final int DEFAULT_NODES = 3;
  private static final float DEFAULT_LEARNING_RATE = 0.3f;

  private int inputNodes;
  private int hiddenNodes;
  private int outputNodes;
  private float learningRate;
  private double[][] weightsInputToHidden;
  private double[][] weightsHiddenToOutput;


  public NeuralNetwork() {
    this.hiddenNodes = DEFAULT_NODES;
    this.inputNodes = DEFAULT_NODES;
    this.outputNodes = DEFAULT_NODES;
    this.learningRate = DEFAULT_LEARNING_RATE;
    generateLinkWeights();
  }

  public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes) {
    this.inputNodes = inputNodes;
    this.hiddenNodes = hiddenNodes;
    this.outputNodes = outputNodes;
    this.learningRate = DEFAULT_LEARNING_RATE;
    generateLinkWeights();
  }

  public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes, float learningRate) {
    this.inputNodes = inputNodes;
    this.hiddenNodes = hiddenNodes;
    this.outputNodes = outputNodes;
    this.learningRate = learningRate;
    generateLinkWeights();
  }

  public double[][] getWeightsInputToHidden() {
    return this.weightsInputToHidden;
  }

  public double[][] getWeightsHiddenToOutput() {
    return this.weightsHiddenToOutput;
  }

  @Override
  public String toString() {
    return "input nodes = " + inputNodes + "; hidden nodes = " + hiddenNodes + "; output nodes = "
        + outputNodes + "; learning rate = " + learningRate;
  }

  private void generateLinkWeights() {
    this.weightsInputToHidden = structureWeightsTo2dimensional(this.inputNodes, this.hiddenNodes,
        addNegativeNumbersForWeights(generateRandomizeWeights(this.inputNodes,
            this.hiddenNodes)));
    this.weightsHiddenToOutput = structureWeightsTo2dimensional(this.hiddenNodes, this.outputNodes,
        addNegativeNumbersForWeights(generateRandomizeWeights(this.hiddenNodes,
            this.outputNodes)));
  }

  private double[][] structureWeightsTo2dimensional(int columns, int rows,
      double[] oneDimensional) {
    double[][] weights = new double[columns][rows];
    int counter = 0;
    for (double weight : oneDimensional) {
      if (counter != rows) {
        weights[columns - 1][counter++] = weight;
      } else {
        counter = 0;
        weights[columns - 2][counter++] = weight;
        columns--;
      }
    }
    return weights;
  }

  private double[] addNegativeNumbersForWeights(double[] weights) {
    for (int i = 0; i < weights.length; i++) {
      weights[i] -= 0.5;
    }
    return weights;
  }

  private double[] generateRandomizeWeights(int node1, int node2) {
    return new Random().doubles(node1 * node2).toArray();
  }

}