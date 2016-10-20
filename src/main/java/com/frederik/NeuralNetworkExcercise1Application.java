package com.frederik;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.frederik.network.HousePriceInput;
import com.frederik.network.HousePriceTrainingItem;
import com.frederik.network.HousePriceTrainingSetGenerator;
import com.frederik.network.SimpleNeuralNetwork;
import com.frederik.network.SimpleNeuralNetworkActivationFunction;
import com.frederik.network.SimpleNeuralNetworkImproved;

@SpringBootApplication
public class NeuralNetworkExcercise1Application {

	/**
	 * 
	 * 
	 * Should be around 0.03486760674322228 : 0.035444006223815716
	 * 
	 * NEURON 1 0 : 0.2800570193696473 1 : 0.3918807153208074 2 :
	 * 0.8382650102907442 3 : 0.9296224125186053 NEURON 2 0 :
	 * 0.22678423856502292 1 : 0.14083806088504036 2 : 0.5252229731584357 3 :
	 * 0.006920687619326545 NEURON 3 0 : -0.06698099650446017 1 :
	 * -0.14930495693926304 2 : 0.7069627900297095
	 * 
	 * 
	 * Should be around 0.03486760674322228 : 0.0354641636317312 NEURON 1 0 :
	 * 0.7504700604854139 1 : 0.578516289487535 2 : 0.8326874907482502 3 :
	 * 0.7329127866300033 NEURON 2 0 : -0.07041783302597344 1 :
	 * 0.603398516237194 2 : 0.011250405041184637 3 : -0.04118043198997722
	 * NEURON 3 0 : 0.0024369132838052525 1 : 0.01149001464478417 2 :
	 * -0.15870509673524438
	 * 
	 * 
	 * 
	 * Should be around 0.03486760674322228 : 0.025810116902427738 NEURON 1 0 :
	 * 0.7584672370278652 1 : 6.668421069464305E-8 2 : 6.668421069464305E-8 3 :
	 * 6.668421069464305E-8 NEURON 2 0 : 0.7076417472425536 1 :
	 * 1.3353627478743926E-18 2 : 1.3353627478743926E-18 3 :
	 * 1.3353627478743926E-18 NEURON 3 0 : 0.02581011690203572 1 :
	 * 5.16852040616743E-13 2 : -1.2293767097751624E-17
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(NeuralNetworkExcercise1Application.class, args);
		runSimpleImproved(8000,150000, 0.7);
		//runSimpleWithActivationFunction();

	}

	private static void displayTrainingSet(ArrayList<HousePriceTrainingItem> trainingSet) {
		// TODO Auto-generated method stub
		for (HousePriceTrainingItem item : trainingSet) {
			System.out.println("Trainingsitem : " + item.getInput().getNumberOfBathrooms() + " "
					+ item.getInput().getNumberOfRooms() + " " + item.getInput().getSize() + " , price: "
					+ item.getPrice());
		}
	}

	private static void runSimpleWithActivationFunction(int trainingSize) {
		ArrayList<HousePriceTrainingItem> trainingSet = HousePriceTrainingSetGenerator.generateTrainingsExamples(trainingSize);

		displayTrainingSet(trainingSet);

		// SimpleNeuralNetwork network = new SimpleNeuralNetwork();
		SimpleNeuralNetworkActivationFunction network = new SimpleNeuralNetworkActivationFunction();
		network.initialise();

		network.train(trainingSet, 150000, 0.1, 1);

		System.out.println("TEST: ");
		HousePriceInput inputTest = new HousePriceInput();
		inputTest.setNumberOfBathrooms(0.8105);
		inputTest.setNumberOfRooms(0.8105);
		inputTest.setSize(0.8105);

		System.out.println("Should be around " + HousePriceTrainingSetGenerator.shouldReturn(inputTest) + " : "
				+ network.process(inputTest));

		System.out.println("TEST2: ");

		inputTest.setNumberOfBathrooms(0.386);
		inputTest.setNumberOfRooms(0.386);
		inputTest.setSize(0.386);

		System.out.println("Should be around " + HousePriceTrainingSetGenerator.shouldReturn(inputTest) + " : "
				+ network.process(inputTest));

		System.out.println("TEST3: ");

		inputTest.setNumberOfBathrooms(0.99);
		inputTest.setNumberOfRooms(0.99);
		inputTest.setSize(0.99);

		System.out.println("Should be around " + HousePriceTrainingSetGenerator.shouldReturn(inputTest) + " : "
				+ network.process(inputTest));
		network.outputWeights();

	}
	
	private static void runSimpleImproved(int trainingSize, int numberOfIterations, double learningRate) {
		ArrayList<HousePriceTrainingItem> trainingSet = HousePriceTrainingSetGenerator.generateTrainingsExamples(trainingSize);

		displayTrainingSet(trainingSet);

		// SimpleNeuralNetwork network = new SimpleNeuralNetwork();
		SimpleNeuralNetworkImproved network = new SimpleNeuralNetworkImproved();
		network.initialise();

		network.train(trainingSet, numberOfIterations, learningRate);

		HousePriceInput inputTest = new HousePriceInput();
	
		
		Random rand = new Random();
		double networkOutput = 0.0;
		double realOutput = 0.0;
		double totalError = 0.0;
		
		for(int i = 1; i<101; i++) {
			inputTest.setNumberOfBathrooms(rand.nextDouble());
			inputTest.setNumberOfRooms(rand.nextDouble());
			inputTest.setSize(rand.nextDouble());
			realOutput = HousePriceTrainingSetGenerator.shouldReturn(inputTest);
			networkOutput = network.process(inputTest);
			System.out.println("TEST"+i+" Should be around " + realOutput*1000000 + " : "
					+ networkOutput*1000000);
			
			totalError += Math.abs(realOutput - networkOutput);

		}
	

		System.out.println("YOUR NETWORK PERFORMNCE IS: "+totalError*10000);
		network.outputWeights();

	}

}
