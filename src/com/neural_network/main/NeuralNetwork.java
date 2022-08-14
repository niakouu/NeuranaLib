package com.neural_network.main;

import java.util.Arrays;
import java.util.Random;

public class NeuralNetwork {

  private static final int DEFAULT_NODES = 3;
  private static final float DEFAULT_LEARNING_RATE = 0.3f;
  private final static double INITIAL_VALUE_FOR_TARGETS = 0.1;

  private final int inputNodes;
  private final int hiddenNodes;
  private final int outputNodes;
  private final float learningRate;
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

  public double[][] query(double[][] inputs) {
    double[][] hiddenInputs = MatrixManipulations.multiplyMatrix(this.weightsInputToHidden, inputs);
    double[][] hiddenOutputs = MatrixManipulations.sigmoid(hiddenInputs);
    double[][] finalInputs = MatrixManipulations.multiplyMatrix(this.weightsHiddenToOutput,
        hiddenOutputs);
    return MatrixManipulations.sigmoid(finalInputs);
  }

  public void trainData(Dataset data) {
    double[][] targets = initialTargetsValues(this.outputNodes);
    for (double[] reshapedInputs : data.getReshapedInputs()) {
      double[][] inputs = MatrixManipulations.structure1dimensionalTo2dimensional(
          1, reshapedInputs.length, reshapedInputs);
      train(inputs, targets);
    }
  }

  private double[][] initialTargetsValues(int length) {
    double[] result = new double[length];
    Arrays.fill(result, INITIAL_VALUE_FOR_TARGETS);
    return MatrixManipulations.structure1dimensionalTo2dimensional(this.outputNodes, 1, result);
  }

  private void train(double[][] inputs, double[][] targets) {
    double[][] hiddenInputs = MatrixManipulations.multiplyMatrix(inputs, this.weightsInputToHidden);
    double[][] hiddenOutputs = MatrixManipulations.sigmoid(hiddenInputs);
    double[][] finalInputs = MatrixManipulations.multiplyMatrix(hiddenOutputs,
        this.weightsHiddenToOutput);
    double[][] finalOutputs = MatrixManipulations.sigmoid(finalInputs);
    finalOutputs = MatrixManipulations.structure1dimensionalTo2dimensional(finalOutputs[0].length,
        1, finalOutputs[0]);
    double[][] outputErrors = MatrixManipulations.subtractMatrix(targets, finalOutputs);
    double[][] hiddenErrors = MatrixManipulations.multiplyMatrix(new Matrix(this.weightsHiddenToOutput), new Matrix(outputErrors));
    this.weightsInputToHidden = updateWeights(hiddenErrors, hiddenOutputs, inputs);
    this.weightsHiddenToOutput = updateWeights(outputErrors, finalOutputs, hiddenOutputs);
  }

  private double[][] updateWeights(double[][] errors, double[][] outputs,
      double[][] transposedValues) {
    Matrix transposedValue = new Matrix(transposedValues);
    return MatrixManipulations.multiplyElementByElement(this.learningRate,
        MatrixManipulations.multiplyMatrix(
            transposedValue,
            new Matrix(MatrixManipulations.multiplyElementByElement(errors,
                outputs,
                MatrixManipulations.subtractMatrix(1.0, outputs)))));
  }

  private void generateLinkWeights() {
    this.weightsInputToHidden = MatrixManipulations.structure1dimensionalTo2dimensional(
        this.inputNodes, this.hiddenNodes,
        addNegativeNumbersForWeights(generateRandomizeWeights(this.inputNodes, this.hiddenNodes)));
    this.weightsHiddenToOutput = MatrixManipulations.structure1dimensionalTo2dimensional(
        this.hiddenNodes, this.outputNodes,
        addNegativeNumbersForWeights(generateRandomizeWeights(this.hiddenNodes, this.outputNodes)));
  }

  private double[] addNegativeNumbersForWeights(double[] weights) {
    for (int i = 0; i < weights.length; i++) {
      weights[i] -= 0.5;
    }
    return weights;
  }

  private double[] generateRandomizeWeights(int node1, int node2) {
    return new Random().doubles((long) node1 * node2).toArray();
  }
}