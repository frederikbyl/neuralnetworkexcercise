package com.frederik.network;

import java.util.ArrayList;
import java.util.Random;

public class HousePriceNeuralNetwork {
	
	private double [][] layer1 = new double [2][4]; //4th one is the bias
	private double [] layer2 = new double [3]; //3th one is the bias

	
	private double housePrice;
	
	public void initialise() {
		
		initializeLayer1();
		initializeLayer2();
		
	}
	
	
	public double calculateError(HousePriceInput input, double price) {
		
		double predictedPrice = calculatePrice(input);
		
		return 1.0/2.0 * Math.pow(price - predictedPrice, 2);
		
	}
	
	public double calculatePrice(HousePriceInput input) {
		
		// through layer 1
		double result1Layer1 = input.getNumberOfBathrooms()* layer1[0][0] + input.getNumberOfRooms()* layer1[0][1] +input.getSize()* layer1[0][2] + layer1[0][3];
		double result2Layer1 = input.getNumberOfBathrooms()* layer1[1][0] + input.getNumberOfRooms()* layer1[1][1] +input.getSize()* layer1[1][2] + layer1[1][3];
		
		
		// through layer 2
		double result1Layer2 = result1Layer1 * layer2[0] +  result2Layer1 * layer2[1]  + layer2[2];
		
		return result1Layer2;
	}

	private void initializeLayer2() {
		Random rand = new Random();
		
		layer2[0] = rand.nextDouble();
		layer2[1] = rand.nextDouble();
		layer2[2] = rand.nextDouble();
		
	}

	private void initializeLayer1() {

		Random rand = new Random();
		
		layer1[0][0] = rand.nextDouble();
		layer1[0][1] = rand.nextDouble();
		layer1[0][2] = rand.nextDouble();
		layer1[0][3] = rand.nextDouble();
		
		
		
		layer1[1][0] = rand.nextDouble();
		layer1[1][1] = rand.nextDouble();
		layer1[1][2] = rand.nextDouble();
		layer1[1][3] = rand.nextDouble();
		
	}

	public double train(ArrayList<HousePriceInput> input, ArrayList<Double> output, int maxIterations, double learningRate, double maxError) {
		
		
		return 0.0;
	}
	
	public double test(HousePriceInput input, double output) {
	
		return 0.0;
	}
	
	public void outputWeights() {
		System.out.println("==NEURAL NETWORK STRUCTURE==");
		System.out.println("--------LAYER 1-------------");
		System.out.println("=="+layer1[0][0]+"=="+layer1[0][1]+"=="+layer1[0][2]+"=="+layer1[0][3]);
		System.out.println("=="+layer1[1][0]+"=="+layer1[1][1]+"=="+layer1[1][2]+"=="+layer1[1][3]);
		System.out.println("--------LAYER 2-------------");
		System.out.println("=="+layer2[0]+"=="+layer2[1]+"=="+layer2[2]);
		
	}
	
	
	

}
