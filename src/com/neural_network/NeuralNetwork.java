package com.neural_network;

public class NeuralNetwork {

  private int inputNodes;
  private int hiddenNodes;
  private int outputNodes;
  private float learningRate;
  private static final int DEFAULT_NODES = 3;
  private static final float DEFAULT_LEARNING_RATE = 0.3f;

  public NeuralNetwork() {
    this.hiddenNodes = DEFAULT_NODES;
    this.inputNodes = DEFAULT_NODES;
    this.outputNodes = DEFAULT_NODES;
    this.learningRate = DEFAULT_LEARNING_RATE;
  }

  public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes) {
    this.inputNodes = inputNodes;
    this.hiddenNodes = hiddenNodes;
    this.outputNodes = outputNodes;
  }

  public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes, float learningRate) {
    this.inputNodes = inputNodes;
    this.hiddenNodes = hiddenNodes;
    this.outputNodes = outputNodes;
    this.learningRate = learningRate;
  }

  @Override
  public String toString() {
    return "input nodes = " + inputNodes + "; hidden nodes = " + hiddenNodes + "; output nodes = "
        + outputNodes + "; learning rate = " + learningRate;
  }

  public void setInputNodes(int inputNodes) {
    this.inputNodes = inputNodes;
  }

  public void setHiddenNodes(int hiddenNodes) {
    this.hiddenNodes = hiddenNodes;
  }

  public void setOutputNodes(int outputNodes) {
    this.outputNodes = outputNodes;
  }

  public void setLearningRate(int learningRate) {
    this.learningRate = learningRate;
  }

  
}
