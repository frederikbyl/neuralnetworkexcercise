package com.frederik.network;

import java.util.ArrayList;
import java.util.Random;

public class HousePriceTrainingSetGenerator {
	
	public static ArrayList<HousePriceTrainingItem> generateTrainingsExamples(int trainingSize) {
		
		ArrayList<HousePriceTrainingItem> list = new ArrayList<HousePriceTrainingItem>();
		Random rand = new Random();
		
		for(int i = 0 ; i<trainingSize; i++) {
			HousePriceTrainingItem item = new HousePriceTrainingItem();
			
			double numberOfBathRooms = rand.nextDouble();
			double numberOfRooms = rand.nextDouble();
			double size = rand.nextDouble();
			HousePriceInput input = new HousePriceInput();
			input.setNumberOfBathrooms(numberOfBathRooms);
			input.setNumberOfRooms(numberOfRooms);
			input.setSize(size);
			item.setInput(input);
			item.setPrice((Math.pow(  (Math.pow(numberOfBathRooms,2.0) + numberOfRooms + size)  , 2.0)) / 9.0);
			
			list.add(item);
			
		}
		
		return list;
		
		
	}
	
	
	public static double shouldReturn(HousePriceInput input) {
		
		return (Math.pow(  (Math.pow(input.getNumberOfBathrooms(),2.0) + input.getNumberOfRooms() + input.getSize())  , 2.0)) / 9.0;
		
	}

}
