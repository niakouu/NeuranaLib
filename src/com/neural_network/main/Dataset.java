package com.neural_network.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dataset {

  private double[][] inputs;
  private int sizeOfaData;

  public Dataset(String directory, int sizeOfData) {
    this.sizeOfaData = sizeOfData;
    this.inputs = this.getData(directory);
  }

  public double[][] getInputs() {
    return this.inputs;
  }

  private double[][] getData(String directory) {
    List<double[]> dataList = new ArrayList();
    try {
      BufferedReader reader = this.openFile(directory);
      String line = reader.readLine();
      while(line != null){
        dataList.add(this.parseLine(line));
        line = reader.readLine();
      }
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this.getArrayDataList(dataList);
  }

  private BufferedReader openFile(String directory) throws FileNotFoundException {
    return new BufferedReader(new FileReader(directory));
  }

  private double[] parseLine(String line) {
    String[] characters = line.split(",");
    double[] inputs = new double[characters.length];
    int counter = 0;
    int size = characters.length;
    for (int i = 0; i < size; ++i) {
      inputs[counter++] = Double.parseDouble(characters[i]);
    }
    return inputs;
  }

  private double[][] getArrayDataList(List<double[]> datalist) {
    double[][] data = new double[datalist.size()][this.sizeOfaData];
    for (int i = 0; i < data.length; ++i) {
      data[i] = datalist.get(i);
    }
    return data;
  }
}
