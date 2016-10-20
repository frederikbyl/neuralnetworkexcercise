package com.frederik.network;

import java.util.ArrayList;
import java.util.Random;

public class SimpleNeuralNetwork {
	
	private double [] weights_neuron1 = new double [4];
	private double [] weights_neuron2 = new double [4];
	private double [] weights_neuron3 = new double [4];
	
	private double lambda = 0; 
	private double alpha = 0.1; // learning reate

	
	public void initialise() {
		Random rand = new Random();
		weights_neuron1 [0] = rand.nextDouble();
		weights_neuron1 [1] = rand.nextDouble();
		weights_neuron1 [2] = rand.nextDouble();
		weights_neuron1 [3] = rand.nextDouble();
		
		weights_neuron2 [0] = rand.nextDouble();
		weights_neuron2 [1] = rand.nextDouble();
		weights_neuron2 [2] = rand.nextDouble();
		weights_neuron2 [3] = rand.nextDouble();
		
		weights_neuron3 [0] = rand.nextDouble();
		weights_neuron3 [1] = rand.nextDouble();
		weights_neuron3 [2] = rand.nextDouble();
		
	}
	
	public double process(HousePriceInput input) {
		
		// through layer 1
		double result1Layer1 = weights_neuron1[0] + input.getNumberOfBathrooms()* weights_neuron1[1] + input.getNumberOfRooms()* weights_neuron1[2] +input.getSize()* weights_neuron1[3];
		double result2Layer1 = weights_neuron2[0] + input.getNumberOfBathrooms()* weights_neuron2[1] + input.getNumberOfRooms()* weights_neuron2[2] +input.getSize()* weights_neuron2[3];
		
		
		// through layer 2
		double result1Layer2 = weights_neuron3[0] + result1Layer1 * weights_neuron3[1]+  result2Layer1 * weights_neuron2[2];
		
		return result1Layer2;
	}

	
	
	public double train(ArrayList<HousePriceTrainingItem> trainingSet,  int maxIterations, double learningRate, double minError) {
		
		int number_of_training_examples = trainingSet.size();
		double [] gradient_neuron1 = new double [4];
		double [] gradient_neuron2 = new double [4];
		double [] gradient_neuron3 = new double [3];
		
		double [] delta_neuron1 = new double [4]; 
		double [] delta_neuron2 = new double [4];
		double [] delta_neuron3 = new double [3];
		
		double activation_neuron1 = 0;
		double activation_neuron2 = 0;
		double activation_neuron3 = 0;
		
		double error_neuron1 = 0;
		double error_neuron2 = 0;
		double error_neuron3 = 0;
		
		double cummulated_error = Double.MAX_VALUE;
		double lowest_cummulated_error = Double.MAX_VALUE;
		
		
		//repeat until max iterations or minError occurred
		for(int iteration =0; iteration < maxIterations; iteration++ ) {
			//initialize delta's
			//delta's neuron 1
			delta_neuron1[0] = 0;
			delta_neuron1[1] = 0;
			delta_neuron1[2] = 0;
			delta_neuron1[3] = 0;
					
			//delta's neuron 2

			delta_neuron2[0] = 0;
			delta_neuron2[1] = 0;
			delta_neuron2[2] = 0;
			delta_neuron2[3] = 0;
			
			//delta's neuron 3

			delta_neuron3[0] = 0;
			delta_neuron3[1] = 0;
			delta_neuron3[2] = 0;
			cummulated_error = 0;
			for(int train_example = 0 ;train_example < number_of_training_examples; train_example++ ) {

				//calculate output
				activation_neuron1 = weights_neuron1[0] + trainingSet.get(train_example).getInput().getNumberOfBathrooms()* weights_neuron1[1] + trainingSet.get(train_example).getInput().getNumberOfRooms()* weights_neuron1[2] +trainingSet.get(train_example).getInput().getSize()* weights_neuron1[3];
				activation_neuron2 = weights_neuron2[0] + trainingSet.get(train_example).getInput().getNumberOfBathrooms()* weights_neuron2[1] + trainingSet.get(train_example).getInput().getNumberOfRooms()* weights_neuron2[2] +trainingSet.get(train_example).getInput().getSize()* weights_neuron2[3];
				activation_neuron3 = weights_neuron3[0] + activation_neuron1 * weights_neuron3[1]+  activation_neuron2 * weights_neuron3[2];
				
				//calculate_delta
				error_neuron3 = activation_neuron3 - trainingSet.get(train_example).getPrice();
				error_neuron2 = error_neuron3 * weights_neuron3[1];
				error_neuron1 = error_neuron3 * weights_neuron3[0];
				
				cummulated_error = cummulated_error + Math.abs(error_neuron3);
				
				//delta's neuron 1
				delta_neuron1[0] = delta_neuron1[0] + error_neuron1;
				delta_neuron1[1] = delta_neuron1[1] + error_neuron1*trainingSet.get(train_example).getInput().getNumberOfBathrooms();
				delta_neuron1[2] = delta_neuron1[2] + error_neuron1*trainingSet.get(train_example).getInput().getNumberOfRooms();
				delta_neuron1[3] = delta_neuron1[3] + error_neuron1*trainingSet.get(train_example).getInput().getSize();
						
				//delta's neuron 2

				delta_neuron2[0] = delta_neuron2[0] + error_neuron2;
				delta_neuron2[1] = delta_neuron2[1] + error_neuron2*trainingSet.get(train_example).getInput().getNumberOfBathrooms();
				delta_neuron2[2] = delta_neuron2[2] + error_neuron2*trainingSet.get(train_example).getInput().getNumberOfRooms();
				delta_neuron2[3] = delta_neuron2[3] + error_neuron2*trainingSet.get(train_example).getInput().getSize();
				
				//delta's neuron 3

				delta_neuron3[0] = delta_neuron3[0] + error_neuron3;
				delta_neuron3[1] = delta_neuron3[1] + error_neuron3*activation_neuron1;
				delta_neuron3[2] = delta_neuron3[2] + error_neuron3*activation_neuron2;
				
					
			}
			//System.out.println("ACTIVATIONS: " + activation_neuron1+" "+ activation_neuron2 + " "+ activation_neuron3);
			//System.out.println("ERRORS: " + error_neuron1+" "+ error_neuron2 + " "+ error_neuron3);
			
			System.out.println(iteration + ": Cummulated error: " +cummulated_error);
			if(cummulated_error < lowest_cummulated_error) 
				lowest_cummulated_error = cummulated_error;
			else
				return lowest_cummulated_error;
		
			
			//calculate gradient1
			gradient_neuron1[0] = 1.0/(double)number_of_training_examples* delta_neuron1[0] ;
			gradient_neuron1[1] = 1.0/(double)number_of_training_examples* delta_neuron1[1] + lambda*weights_neuron1[1];
			gradient_neuron1[2] = 1.0/(double)number_of_training_examples* delta_neuron1[2] + lambda*weights_neuron1[2];
			gradient_neuron1[3] = 1.0/(double)number_of_training_examples* delta_neuron1[3] + lambda*weights_neuron1[3];
			//calculate gradient2
			gradient_neuron2[0] =  1.0/(double)number_of_training_examples* delta_neuron2[0] ;
			gradient_neuron2[1] =  1.0/(double)number_of_training_examples* delta_neuron2[1] + lambda*weights_neuron2[1];
			gradient_neuron2[2] =  1.0/(double)number_of_training_examples* delta_neuron2[2] + lambda*weights_neuron2[2];
			gradient_neuron2[3] =  1.0/(double)number_of_training_examples* delta_neuron2[3] + lambda*weights_neuron2[3];
			
			//calculate gradinet3
			gradient_neuron3[0] = 1.0/(double)number_of_training_examples* delta_neuron3[0] ;
			gradient_neuron3[1] = 1.0/(double)number_of_training_examples* delta_neuron3[1] + lambda*weights_neuron3[1];
			gradient_neuron3[2] = 1.0/(double)number_of_training_examples* delta_neuron3[2] + lambda*weights_neuron3[2];
			

			

			//update according to gradient descent and train again
			weights_neuron1[0] = weights_neuron1[0]  - alpha*gradient_neuron1[0];
			weights_neuron1[1] = weights_neuron1[1]  - alpha*gradient_neuron1[1];
			weights_neuron1[2] = weights_neuron1[2]  - alpha*gradient_neuron1[2];
			weights_neuron1[3] = weights_neuron1[3]  - alpha*gradient_neuron1[3];
			
			
			weights_neuron2[0] = weights_neuron2[0]  - alpha*gradient_neuron2[0];
			weights_neuron2[1] = weights_neuron2[1]  - alpha*gradient_neuron2[1];
			weights_neuron2[2] = weights_neuron2[2]  - alpha*gradient_neuron2[2];
			weights_neuron2[3] = weights_neuron2[3]  - alpha*gradient_neuron2[3];
			
			weights_neuron3[0] = weights_neuron3[0]  - alpha*gradient_neuron3[0];
			weights_neuron3[1] = weights_neuron3[1]  - alpha*gradient_neuron3[1];
			weights_neuron3[2] = weights_neuron3[2]  - alpha*gradient_neuron3[2];
		
			//System.out.println("GRADIENTS: " + gradient_neuron1[0]+ " "+ gradient_neuron1[1]+" "+ gradient_neuron1[2]+ " "+gradient_neuron1[3]);
			//System.out.println("WEIGHTS: " + weights_neuron1[0] + " " +weights_neuron1[1] + " "+weights_neuron1[2] + " " + weights_neuron1[3]  );
			
		}
		return error_neuron3;
	}
	
	public double test(HousePriceInput input, double output) {
	
		
		return 0.0;
	}
//	
//	public void outputWeights() {
//		System.out.println("==NEURAL NETWORK STRUCTURE==");
//		System.out.println("--------LAYER 1-------------");
//		System.out.println("=="+layer1[0][0]+"=="+layer1[0][1]+"=="+layer1[0][2]+"=="+layer1[0][3]);
//		System.out.println("=="+layer1[1][0]+"=="+layer1[1][1]+"=="+layer1[1][2]+"=="+layer1[1][3]);
//		System.out.println("--------LAYER 2-------------");
//		System.out.println("=="+layer2[0]+"=="+layer2[1]+"=="+layer2[2]);
//		
//	}

	public void outputWeights() {
		System.out.println("NEURON 1");
		System.out.println("0 : "+ weights_neuron1[0]);
		System.out.println("1 : "+ weights_neuron1[1]);
		System.out.println("2 : "+ weights_neuron1[2]);
		System.out.println("3 : "+ weights_neuron1[3]);
		

		System.out.println("NEURON 2");
		System.out.println("0 : "+ weights_neuron2[0]);
		System.out.println("1 : "+ weights_neuron2[1]);
		System.out.println("2 : "+ weights_neuron2[2]);
		System.out.println("3 : "+ weights_neuron2[3]);
		

		System.out.println("NEURON 3");
		System.out.println("0 : "+ weights_neuron3[0]);
		System.out.println("1 : "+ weights_neuron3[1]);
		System.out.println("2 : "+ weights_neuron3[2]);		
	}
	
	
	

}
