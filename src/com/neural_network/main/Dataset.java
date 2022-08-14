package com.neural_network.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dataset {

  private final double[][] inputs;
  private final List<Double> headers;
  private Matrix reshapedInputsMatrix;
  private final int sizeOfaData;

  public Dataset(String directory, int sizeOfData) {
    this.sizeOfaData = sizeOfData;
    this.inputs = getData(directory);
    this.headers = new ArrayList<>();
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
      inputs[i] = (inputs[i]/255.5 * 0.99) + 0.01;
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
