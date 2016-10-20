package com.frederik.network;

import java.util.ArrayList;
import java.util.Random;

public class SimpleNeuralNetworkImproved {
	
	private double [] weights_neuron1 = new double [4];
	private double [] weights_neuron2 = new double [4];
	private double [] weights_neuron3 = new double [3];
	private double [] weights_neuron4 = new double [3];
	private double [] weights_neuron5 = new double [3];
	private double [] weights_neuron6 = new double [3];
	private double [] weights_neuron7 = new double [3];
	
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
		
		weights_neuron4 [0] = rand.nextDouble();
		weights_neuron4 [1] = rand.nextDouble();
		weights_neuron4 [2] = rand.nextDouble();
		
		weights_neuron5 [0] = rand.nextDouble();
		weights_neuron5 [1] = rand.nextDouble();
		weights_neuron5 [2] = rand.nextDouble();
		
		weights_neuron6 [0] = rand.nextDouble();
		weights_neuron6 [1] = rand.nextDouble();
		weights_neuron6 [2] = rand.nextDouble();
	
		weights_neuron7 [0] = rand.nextDouble();
		weights_neuron7 [1] = rand.nextDouble();
		weights_neuron7 [2] = rand.nextDouble();
		
	}
	
	public double process(HousePriceInput input) {
		
		// through layer 1
		double result1Layer1 = weights_neuron1[0] + input.getNumberOfBathrooms()* weights_neuron1[1] + input.getNumberOfRooms()* weights_neuron1[2] +input.getSize()* weights_neuron1[3];
		result1Layer1 = activate(result1Layer1);
		double result2Layer1 = weights_neuron2[0] + input.getNumberOfBathrooms()* weights_neuron2[1] + input.getNumberOfRooms()* weights_neuron2[2] +input.getSize()* weights_neuron2[3];
		result2Layer1 = activate(result2Layer1);
		
		// through layer 2
		double result1Layer2 = weights_neuron3[0] + result1Layer1 * weights_neuron3[1]+  result2Layer1 * weights_neuron3[2];
		result1Layer2 = activate(result1Layer2);
		double result2Layer2 = weights_neuron4[0] + result1Layer1 * weights_neuron4[1]+  result2Layer1 * weights_neuron4[2];
		result2Layer2 = activate(result2Layer2);
		
		//through layer 3
		double result1Layer3 = weights_neuron5[0] + result1Layer2 * weights_neuron5[1]+  result2Layer2 * weights_neuron5[2];
		result1Layer3 = activate(result1Layer3);
		double result2Layer3 = weights_neuron6[0] + result1Layer2 * weights_neuron6[1]+  result2Layer2 * weights_neuron6[2];
		result2Layer3 = activate(result2Layer3);
		
		//through layer 4
		double result1Layer4 = weights_neuron7[0] + result1Layer3 * weights_neuron7[1]+  result2Layer3 * weights_neuron7[2];
		result1Layer4 = activate(result1Layer4);
		
		return result1Layer4;
	}

	
	
	private double activate(double input) {
		double result = 1.0/(1.0+Math.exp(-input));
		return result;
	}

	public double train(ArrayList<HousePriceTrainingItem> trainingSet,  int maxIterations, double alpha) {
		
		int number_of_training_examples = trainingSet.size();
		double [] gradient_neuron1 = new double [4];
		double [] gradient_neuron2 = new double [4];
		double [] gradient_neuron3 = new double [3];
		double [] gradient_neuron4 = new double [3];
		double [] gradient_neuron5 = new double [3];
		double [] gradient_neuron6 = new double [3];
		double [] gradient_neuron7 = new double [3];
		
		double [] delta_neuron1 = new double [4]; 
		double [] delta_neuron2 = new double [4];
		double [] delta_neuron3 = new double [3];
		double [] delta_neuron4 = new double [3];
		double [] delta_neuron5 = new double [3];
		double [] delta_neuron6 = new double [3];
		double [] delta_neuron7 = new double [3];
		
		double activation_neuron1 = 0;
		double activation_neuron2 = 0;
		double activation_neuron3 = 0;
		double activation_neuron4 = 0;
		double activation_neuron5 = 0;
		double activation_neuron6 = 0;
		double activation_neuron7 = 0;
		
		double error_neuron1 = 0;
		double error_neuron2 = 0;
		double error_neuron3 = 0;
		double error_neuron4 = 0;
		double error_neuron5 = 0;
		double error_neuron6 = 0;
		double error_neuron7 = 0;
		
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
			
			//delta's neuron 4

			delta_neuron4[0] = 0;
			delta_neuron4[1] = 0;
			delta_neuron4[2] = 0;
			
			//delta's neuron 5

			delta_neuron5[0] = 0;
			delta_neuron5[1] = 0;
			delta_neuron5[2] = 0;
			
			//delta's neuron 6

			delta_neuron6[0] = 0;
			delta_neuron6[1] = 0;
			delta_neuron6[2] = 0;
			
			//delta's neuron 7

			delta_neuron7[0] = 0;
			delta_neuron7[1] = 0;
			delta_neuron7[2] = 0;
			
			cummulated_error = 0;
			
			
			for(int train_example = 0 ;train_example < number_of_training_examples; train_example++ ) {

				//calculate output
				activation_neuron1 = weights_neuron1[0] + trainingSet.get(train_example).getInput().getNumberOfBathrooms()* weights_neuron1[1] + trainingSet.get(train_example).getInput().getNumberOfRooms()* weights_neuron1[2] +trainingSet.get(train_example).getInput().getSize()* weights_neuron1[3];
				activation_neuron1 = activate(activation_neuron1);
				activation_neuron2 = weights_neuron2[0] + trainingSet.get(train_example).getInput().getNumberOfBathrooms()* weights_neuron2[1] + trainingSet.get(train_example).getInput().getNumberOfRooms()* weights_neuron2[2] +trainingSet.get(train_example).getInput().getSize()* weights_neuron2[3];
				activation_neuron2 = activate(activation_neuron2);
				activation_neuron3 = weights_neuron3[0] + activation_neuron1 * weights_neuron3[1]+  activation_neuron2 * weights_neuron3[2];
				activation_neuron3 = activate(activation_neuron3);
				activation_neuron4 = weights_neuron4[0] + activation_neuron1 * weights_neuron4[1]+  activation_neuron2 * weights_neuron4[2];
				activation_neuron4 = activate(activation_neuron4);
				activation_neuron5 = weights_neuron5[0] + activation_neuron3 * weights_neuron5[1]+  activation_neuron4 * weights_neuron5[2];
				activation_neuron5 = activate(activation_neuron5);
				activation_neuron6 = weights_neuron6[0] + activation_neuron3 * weights_neuron6[1]+  activation_neuron4 * weights_neuron6[2];
				activation_neuron6 = activate(activation_neuron6);
				activation_neuron7 = weights_neuron7[0] + activation_neuron5 * weights_neuron7[1]+  activation_neuron6 * weights_neuron7[2];
				activation_neuron7 = activate(activation_neuron7);
				
				//calculate_delta
				error_neuron7 = activation_neuron7 - trainingSet.get(train_example).getPrice();
				error_neuron6 = error_neuron7 * weights_neuron7[2] * activation_neuron6 * (1.0-activation_neuron6);
				error_neuron5 = error_neuron7 * weights_neuron7[1] * activation_neuron5 * (1.0-activation_neuron5);
				error_neuron4 = (error_neuron5 * weights_neuron5[2] + error_neuron6 * weights_neuron6[2]) * activation_neuron4 * (1.0-activation_neuron4);
				error_neuron3 = (error_neuron5 * weights_neuron5[1] + error_neuron6 * weights_neuron6[1]) * activation_neuron3 * (1.0-activation_neuron3);
				error_neuron2 = (error_neuron3 * weights_neuron3[2] + error_neuron4 * weights_neuron4[2]) * activation_neuron2 * (1.0-activation_neuron2);
				error_neuron1 = (error_neuron3 * weights_neuron3[1] + error_neuron4 * weights_neuron4[1]) * activation_neuron1 * (1.0-activation_neuron1);
			
				
				
				
				cummulated_error = cummulated_error + Math.abs(error_neuron7);
				
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
				
				//delta's neuron 4
				
				delta_neuron4[0] = delta_neuron4[0] + error_neuron4;
				delta_neuron4[1] = delta_neuron4[1] + error_neuron4*activation_neuron1;
				delta_neuron4[2] = delta_neuron4[2] + error_neuron4*activation_neuron2;
				
				//delta's neuron 5
				delta_neuron5[0] = delta_neuron5[0] + error_neuron5;
				delta_neuron5[1] = delta_neuron5[1] + error_neuron5*activation_neuron3;
				delta_neuron5[2] = delta_neuron5[2] + error_neuron5*activation_neuron4;
				
				//delta's neuron 6
				delta_neuron6[0] = delta_neuron6[0] + error_neuron6;
				delta_neuron6[1] = delta_neuron6[1] + error_neuron6*activation_neuron3;
				delta_neuron6[2] = delta_neuron6[2] + error_neuron6*activation_neuron4;
				
			
				//delta's neuron 7
				delta_neuron7[0] = delta_neuron7[0] + error_neuron7;
				delta_neuron7[1] = delta_neuron7[1] + error_neuron7*activation_neuron5;
				delta_neuron7[2] = delta_neuron7[2] + error_neuron7*activation_neuron6;
				
					
			}
			
			
			if(iteration % 1000 == 0)
				System.out.println(iteration + ";" +cummulated_error+";");
//			if(cummulated_error < lowest_cummulated_error) 
//				lowest_cummulated_error = cummulated_error;
//			else
//				return lowest_cummulated_error;
		
			
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
			
			//calculate gradient3
			gradient_neuron3[0] = 1.0/(double)number_of_training_examples* delta_neuron3[0] ;
			gradient_neuron3[1] = 1.0/(double)number_of_training_examples* delta_neuron3[1] + lambda*weights_neuron3[1];
			gradient_neuron3[2] = 1.0/(double)number_of_training_examples* delta_neuron3[2] + lambda*weights_neuron3[2];
			
			//calculate gradient4
			gradient_neuron4[0] = 1.0/(double)number_of_training_examples* delta_neuron4[0] ;
			gradient_neuron4[1] = 1.0/(double)number_of_training_examples* delta_neuron4[1] + lambda*weights_neuron4[1];
			gradient_neuron4[2] = 1.0/(double)number_of_training_examples* delta_neuron4[2] + lambda*weights_neuron4[2];
			
			//calculate gradient5
			gradient_neuron5[0] = 1.0/(double)number_of_training_examples* delta_neuron5[0] ;
			gradient_neuron5[1] = 1.0/(double)number_of_training_examples* delta_neuron5[1] + lambda*weights_neuron5[1];
			gradient_neuron5[2] = 1.0/(double)number_of_training_examples* delta_neuron5[2] + lambda*weights_neuron5[2];
			
			//calculate gradient6
			gradient_neuron6[0] = 1.0/(double)number_of_training_examples* delta_neuron6[0] ;
			gradient_neuron6[1] = 1.0/(double)number_of_training_examples* delta_neuron6[1] + lambda*weights_neuron6[1];
			gradient_neuron6[2] = 1.0/(double)number_of_training_examples* delta_neuron6[2] + lambda*weights_neuron6[2];
			
			//calculate gradient7
			gradient_neuron7[0] = 1.0/(double)number_of_training_examples* delta_neuron7[0] ;
			gradient_neuron7[1] = 1.0/(double)number_of_training_examples* delta_neuron7[1] + lambda*weights_neuron7[1];
			gradient_neuron7[2] = 1.0/(double)number_of_training_examples* delta_neuron7[2] + lambda*weights_neuron7[2];
			
			

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
		
			weights_neuron4[0] = weights_neuron4[0]  - alpha*gradient_neuron4[0];
			weights_neuron4[1] = weights_neuron4[1]  - alpha*gradient_neuron4[1];
			weights_neuron4[2] = weights_neuron4[2]  - alpha*gradient_neuron4[2];
		
			weights_neuron5[0] = weights_neuron5[0]  - alpha*gradient_neuron5[0];
			weights_neuron5[1] = weights_neuron5[1]  - alpha*gradient_neuron5[1];
			weights_neuron5[2] = weights_neuron5[2]  - alpha*gradient_neuron5[2];
		
			weights_neuron6[0] = weights_neuron6[0]  - alpha*gradient_neuron6[0];
			weights_neuron6[1] = weights_neuron6[1]  - alpha*gradient_neuron6[1];
			weights_neuron6[2] = weights_neuron6[2]  - alpha*gradient_neuron6[2];
		
			weights_neuron7[0] = weights_neuron7[0]  - alpha*gradient_neuron7[0];
			weights_neuron7[1] = weights_neuron7[1]  - alpha*gradient_neuron7[1];
			weights_neuron7[2] = weights_neuron7[2]  - alpha*gradient_neuron7[2];
		
			
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
		
		System.out.println("NEURON 4");
		System.out.println("0 : "+ weights_neuron4[0]);
		System.out.println("1 : "+ weights_neuron4[1]);
		System.out.println("2 : "+ weights_neuron4[2]);
		
		System.out.println("NEURON 5");
		System.out.println("0 : "+ weights_neuron5[0]);
		System.out.println("1 : "+ weights_neuron5[1]);
		System.out.println("2 : "+ weights_neuron5[2]);
		
		System.out.println("NEURON 6");
		System.out.println("0 : "+ weights_neuron6[0]);
		System.out.println("1 : "+ weights_neuron6[1]);
		System.out.println("2 : "+ weights_neuron6[2]);
		
		System.out.println("NEURON 7");
		System.out.println("0 : "+ weights_neuron7[0]);
		System.out.println("1 : "+ weights_neuron7[1]);
		System.out.println("2 : "+ weights_neuron7[2]);
	}
	
	
	

}
