package com.neural_network.main;

import java.util.List;
import java.util.Random;

public class NeuralNetwork {

  private static final int DEFAULT_NODES = 3;
  private static final float DEFAULT_LEARNING_RATE = 0.3f;

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

  public double[][] query(List<Double> inputs_list) {
    double[][] inputs = transportToArray(inputs_list);
    double[][] hiddenInputs = DoublesManipulation.multiplyMatrix(this.weightsInputToHidden, inputs);
    double[][] hiddenOutputs = DoublesManipulation.sigmoid(hiddenInputs);
    double[][] finalInputs = DoublesManipulation.multiplyMatrix(this.weightsHiddenToOutput,
        hiddenOutputs);
    return DoublesManipulation.sigmoid(finalInputs);
  }

  public void train(List<Double> inputsList, List<Double> targetList) {
    double[][] inputs = transportToArray(inputsList);
    double[][] hiddenInputs = DoublesManipulation.multiplyMatrix(this.weightsInputToHidden, inputs);
    double[][] hiddenOutputs = DoublesManipulation.sigmoid(hiddenInputs);
    double[][] finalInputs = DoublesManipulation.multiplyMatrix(this.weightsHiddenToOutput,
        hiddenOutputs);
    double[][] finalOutputs = DoublesManipulation.sigmoid(finalInputs);
    double[][] targets = transportToArray(targetList);
    double[][] outputErrors = DoublesManipulation.subtractMatrix(targets, finalOutputs);
    double[][] hiddenErrors = DoublesManipulation.sigmoid(outputErrors);
    this.weightsHiddenToOutput = updateWeights(outputErrors, finalOutputs, hiddenOutputs);
    this.weightsInputToHidden = updateWeights(hiddenErrors, hiddenOutputs, inputs);
  }

  private double[][] updateWeights(double[][] errors, double[][] outputs,
      double[][] transposedValues) {
    return DoublesManipulation.multiplyElementByElement(this.learningRate,
        DoublesManipulation.multiplyMatrix(
            DoublesManipulation.multiplyElementByElement(errors,
                outputs,
                DoublesManipulation.subtractMatrix(1.0, outputs))
            , DoublesManipulation.transpose(transposedValues)));
  }

  private double[][] transportToArray(List<Double> inputs_list) {
    double[] inputsOneDimensionalArray = changeFromListToArrayForDouble(inputs_list);
    return DoublesManipulation.structure1dimensionalTo2dimensional(inputsOneDimensionalArray.length, 1,
        inputsOneDimensionalArray);
  }

  private double[] changeFromListToArrayForDouble(List<Double> inputs_list) {
    return inputs_list.stream().mapToDouble(Double::doubleValue).toArray();
  }

  private void generateLinkWeights() {
    this.weightsInputToHidden = DoublesManipulation.structure1dimensionalTo2dimensional(this.inputNodes, this.hiddenNodes,
        addNegativeNumbersForWeights(generateRandomizeWeights(this.inputNodes, this.hiddenNodes)));
    this.weightsHiddenToOutput = DoublesManipulation.structure1dimensionalTo2dimensional(this.hiddenNodes, this.outputNodes,
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