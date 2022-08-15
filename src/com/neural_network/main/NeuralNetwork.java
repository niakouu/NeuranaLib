package com.neural_network.main;

import java.util.*;

public class NeuralNetwork {

  private static final int DEFAULT_NODES = 3;
  private static final float DEFAULT_LEARNING_RATE = 0.3f;
  private final static double INITIAL_VALUE_FOR_TARGETS = 0.01;

  private final int inputNodes;
  private final int hiddenNodes;
  private final int outputNodes;
  private final float learningRate;
  private Matrix weightsInputToHidden;
  private Matrix weightsHiddenToOutput;


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

  public Matrix getWeightsInputToHidden() {
    return this.weightsInputToHidden;
  }

  public Matrix getWeightsHiddenToOutput() {
    return this.weightsHiddenToOutput;
  }

  @Override
  public String toString() {
    return "input nodes = " + inputNodes + "; hidden nodes = " + hiddenNodes + "; output nodes = "
        + outputNodes + "; learning rate = " + learningRate;
  }

  public List<Matrix> query(Dataset inputs) {
    List<Matrix> outputs = new ArrayList<>();
    for (double[] reshapedInputs : inputs.getReshapedInputsMatrix().getData()) {
      Matrix input = MatrixManipulations.structure1dimensionalTo2dimensional(1,
          reshapedInputs.length, reshapedInputs);
      Matrix output = getFinalOutput(input);
      outputs.add(output);
    }
    return outputs;
  }

  public void trainData(Dataset data, int epochs) {
    for (int i = 0; i < epochs; i++) {
      int counter = 0;
      for (double[] reshapedInputs : data.getReshapedInputsMatrix().getData()) {
        Matrix inputs = MatrixManipulations.structure1dimensionalTo2dimensional(1,
            reshapedInputs.length, reshapedInputs);
        double targetValue = data.getHeaders().get(counter++);
        int target = (int) targetValue;
        Matrix targets = initialTargetsValues(this.outputNodes, target);
        train(inputs, targets);
      }
    }
  }

  private Matrix getFinalOutput(Matrix inputs) {
    Matrix hiddenInputs = MatrixManipulations.multiplyMatrix(this.weightsInputToHidden, inputs);
    Matrix hiddenOutputs = MatrixManipulations.sigmoid(hiddenInputs);
    Matrix finalInputs = MatrixManipulations.multiplyMatrix(this.weightsHiddenToOutput,
        hiddenOutputs);
    return MatrixManipulations.sigmoid(finalInputs);
  }

  private Matrix initialTargetsValues(int length, int target) {
    double[] result = new double[length];
    Arrays.fill(result, INITIAL_VALUE_FOR_TARGETS);
    result[target] = 0.99;
    return MatrixManipulations.structure1dimensionalTo2dimensional(this.outputNodes, 1, result);
  }

  private void train(Matrix inputs, Matrix targets) {
    Matrix hiddenInputs = MatrixManipulations.multiplyMatrix(inputs, this.weightsInputToHidden);
    Matrix hiddenOutputs = MatrixManipulations.sigmoid(hiddenInputs);
    Matrix finalInputs = MatrixManipulations.multiplyMatrix(hiddenOutputs,
        this.weightsHiddenToOutput);
    Matrix finalOutputs = MatrixManipulations.sigmoid(finalInputs);
    finalOutputs = MatrixManipulations.structure1dimensionalTo2dimensional(finalOutputs.getColumn(),
        1, finalOutputs.getFirstRow());
    Matrix outputErrors = MatrixManipulations.subtractMatrix(targets, finalOutputs);
    Matrix hiddenErrors = MatrixManipulations.multiplyMatrix(this.weightsHiddenToOutput,
        outputErrors);
    this.weightsInputToHidden = MatrixManipulations.addMatrix(updateWeights(hiddenErrors, hiddenOutputs, inputs), this.weightsInputToHidden);
    this.weightsHiddenToOutput = MatrixManipulations.addMatrix(updateWeights(outputErrors, finalOutputs, hiddenOutputs), this.weightsHiddenToOutput);
  }

  private Matrix updateWeights(Matrix errors, Matrix outputs, Matrix transposedValues) {
    return MatrixManipulations.multiplyElementByElement(this.learningRate,
        MatrixManipulations.multiplyMatrix(transposedValues,
            MatrixManipulations.multiplyElementByElement(errors, outputs,
                MatrixManipulations.subtractMatrix(1.0, outputs))));
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