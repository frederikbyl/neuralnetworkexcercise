package com.frederik.network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class SimpleNeuralNetworkActivationFunction {
	
	private double [] weights_neuron1 = new double [4];
	private double [] weights_neuron2 = new double [4];
	private double [] weights_neuron3 = new double [3];
	
		
	private double lambda = 0; 
	
	
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
		
		//calculate output
		double activation_neuron1 = weights_neuron1[0] + input.getNumberOfBathrooms()* weights_neuron1[1] + input.getNumberOfRooms()* weights_neuron1[2] +input.getSize()* weights_neuron1[3];
		double activation_neuron2 = weights_neuron2[0] + input.getNumberOfBathrooms()* weights_neuron2[1] + input.getNumberOfRooms()* weights_neuron2[2] +input.getSize()* weights_neuron2[3];

		activation_neuron1 = activate(activation_neuron1);
		activation_neuron2 = activate(activation_neuron2);
		
		double activation_neuron3 = weights_neuron3[0] + activation_neuron1 * weights_neuron3[1]+  activation_neuron2 * weights_neuron3[2];
		
		activation_neuron3 = activate(activation_neuron3);
		return activation_neuron3;
		
	}

	
	private double activate(double input) {
		double result = 1.0/(1.0+Math.exp(-input));
		return result;
	}

	public double train(ArrayList<HousePriceTrainingItem> trainingSet,  int maxIterations, double learningRate) throws IOException {
		double [] best_weights_neuron1 = new double [4];
		double [] best_weights_neuron2 = new double [4];
		double [] best_weights_neuron3 = new double [3];
		
	
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
		Path path = Paths.get("trainingErrorOutput.csv");
		BufferedWriter writer = Files.newBufferedWriter(path);
		
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

				activation_neuron1 = activate(activation_neuron1);
				activation_neuron2 = activate(activation_neuron2);
				
				activation_neuron3 = weights_neuron3[0] + activation_neuron1 * weights_neuron3[1]+  activation_neuron2 * weights_neuron3[2];
				
				activation_neuron3 = activate(activation_neuron3);
				
				//calculate_delta
				error_neuron3 = activation_neuron3 - trainingSet.get(train_example).getPrice();
			
				error_neuron2 = error_neuron3 * weights_neuron3[2] * activation_neuron2 * (1.0-activation_neuron2);
				error_neuron1 = error_neuron3 * weights_neuron3[1] * activation_neuron1 * (1.0-activation_neuron1) ;
			
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
			writer.write(iteration + "; " +cummulated_error+";\n");
			System.out.println(iteration + ": Cummulated error: " +cummulated_error);
		
			if(cummulated_error < lowest_cummulated_error) {
				best_weights_neuron1 = weights_neuron1;
				best_weights_neuron2 = weights_neuron2;
				best_weights_neuron3 = weights_neuron3;
			}
			
			//calculate gradient1
			gradient_neuron1[0] = delta_neuron1[0] / ((double)number_of_training_examples) ;
			gradient_neuron1[1] = delta_neuron1[1]/ ((double)number_of_training_examples) + lambda*weights_neuron1[1];
			gradient_neuron1[2] = delta_neuron1[2]/ ((double)number_of_training_examples) + lambda*weights_neuron1[2];
			gradient_neuron1[3] = delta_neuron1[3]/ ((double)number_of_training_examples) + lambda*weights_neuron1[3];
			//calculate gradient2
			gradient_neuron2[0] =  delta_neuron2[0]/ ((double)number_of_training_examples) ;
			gradient_neuron2[1] =  delta_neuron2[1]/ ((double)number_of_training_examples) + lambda*weights_neuron2[1];
			gradient_neuron2[2] =  delta_neuron2[2]/ ((double)number_of_training_examples) + lambda*weights_neuron2[2];
			gradient_neuron2[3] =  delta_neuron2[3]/ ((double)number_of_training_examples) + lambda*weights_neuron2[3];
			
			//calculate gradinet3
			gradient_neuron3[0] = delta_neuron3[0]/ ((double)number_of_training_examples) ;
			gradient_neuron3[1] = delta_neuron3[1]/ ((double)number_of_training_examples) + lambda*weights_neuron3[1];
			gradient_neuron3[2] = delta_neuron3[2]/ ((double)number_of_training_examples) + lambda*weights_neuron3[2];
			

			

			//update according to gradient descent and train again
			weights_neuron1[0] = weights_neuron1[0]  - learningRate*gradient_neuron1[0];
			weights_neuron1[1] = weights_neuron1[1]  - learningRate*gradient_neuron1[1];
			weights_neuron1[2] = weights_neuron1[2]  - learningRate*gradient_neuron1[2];
			weights_neuron1[3] = weights_neuron1[3]  - learningRate*gradient_neuron1[3];
			
			
			weights_neuron2[0] = weights_neuron2[0]  - learningRate*gradient_neuron2[0];
			weights_neuron2[1] = weights_neuron2[1]  - learningRate*gradient_neuron2[1];
			weights_neuron2[2] = weights_neuron2[2]  - learningRate*gradient_neuron2[2];
			weights_neuron2[3] = weights_neuron2[3]  - learningRate*gradient_neuron2[3];
			
			weights_neuron3[0] = weights_neuron3[0]  - learningRate*gradient_neuron3[0];
			weights_neuron3[1] = weights_neuron3[1]  - learningRate*gradient_neuron3[1];
			weights_neuron3[2] = weights_neuron3[2]  - learningRate*gradient_neuron3[2];
		
				
		}
		
		weights_neuron1 = best_weights_neuron1;
		weights_neuron2 = best_weights_neuron2;
		weights_neuron3 = best_weights_neuron3;
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
