package com.neural_network.main;

import java.io.*;
import java.util.*;

public class Dataset {

  private final double[][] inputs;
  private final List<Double> headers;
  private Matrix reshapedInputsMatrix;
  private final int sizeOfaData;

  public Dataset(String directory, int sizeOfData) {
    this.sizeOfaData = sizeOfData;
    this.headers = new ArrayList<>();
    this.inputs = getData(directory);
    reshape();
  }

  public double[][] getInputs() {
    return this.inputs;
  }

  public List<Double> getHeaders() {
    return this.headers;
  }

  public Matrix getReshapedInputsMatrix() {
    return this.reshapedInputsMatrix;
  }

  public float appendCorrectOrIncorrect(List<Matrix> matrixList) {
    List<Float> scorecard = new ArrayList<>();
    for (int i = 0; i < matrixList.size(); i++) {
      int label = matrixList.get(i).getHighestValuePosition();
      if (label == this.headers.get(i)) {
        scorecard.add(1.0f);
      } else {
        scorecard.add(0.0f);
      }
    }
    return calculatePerformance(scorecard);
  }

  private float calculatePerformance(List<Float> scorecard) {
    float total = 0;
    for (Float score : scorecard) {
      total += score;
    }
    return total / (float) scorecard.size() * 100;
  }

  private void reshape() {
    double[][] reshapedInputsArray = new double[this.inputs.length][this.inputs[0].length];
    int counter = 0;
    for (double[] input : this.inputs) {
      this.headers.add(input[0]);
      scaleInput(input);
      reshapedInputsArray[counter++] = Arrays.copyOfRange(input, 1, input.length);
    }
    this.reshapedInputsMatrix = new Matrix(reshapedInputsArray);
  }

  private void scaleInput(double[] inputs) {
    for (int i = 0; i < inputs.length; i++) {
      inputs[i] = (inputs[i] / 255.5 * 0.99) + 0.01;
    }
  }

  private double[][] getData(String directory) {
    BufferedReader reader;
    List<double[]> dataList = new ArrayList<>();
    try {
      reader = openFile(directory);
      String line = reader.readLine();
      while (line != null) {
        dataList.add(parseLine(line));
        line = reader.readLine();
      }
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return getArrayFromDataList(dataList);
  }

  private BufferedReader openFile(String directory) throws FileNotFoundException {
    return new BufferedReader(new FileReader(directory));
  }

  private double[] parseLine(String line) {
    String[] characters = line.split(",");
    double[] inputs = new double[characters.length];
    int counter = 0;
    for (String character : characters) {
      inputs[counter++] = Double.parseDouble(character);
    }
    return inputs;
  }

  private double[][] getArrayFromDataList(List<double[]> datalist) {
    double[][] data = new double[datalist.size()][this.sizeOfaData];
    for (int i = 0; i < data.length; i++) {
      data[i] = datalist.get(i);
    }
    return data;
  }
}
