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
//			double numberOfBathRooms = i/((double)trainingSize);
//			double numberOfRooms = i/((double)trainingSize);
//			double size = i/((double)trainingSize);
			HousePriceInput input = new HousePriceInput();
			input.setNumberOfBathrooms(numberOfBathRooms);
			input.setNumberOfRooms(numberOfRooms);
			input.setSize(size);
			item.setInput(input);
			item.setPrice((Math.pow(  (Math.pow(numberOfBathRooms,1.0/2.0) + Math.pow(numberOfRooms, 1.0/2.0) + Math.pow(size,1.0/2.0))  , 2.0)) / 9.0);
			//item.setPrice(Math.sin(  numberOfBathRooms + numberOfRooms + size) );
			//item.setPrice(Math.pow(  numberOfBathRooms + numberOfRooms + size , 2.0) / 9.0);
			//item.setPrice((numberOfBathRooms + numberOfRooms + size) / 3.0);
			
			list.add(item);
			
		}
		
		return list;
		
		
	}
	
	
	public static double shouldReturn(HousePriceInput input) {
		//return Math.sin(  input.getNumberOfBathrooms() + input.getNumberOfRooms() + input.getSize()) ;
		return (Math.pow(  (Math.pow(input.getNumberOfBathrooms(),1.0/2.0) + Math.pow(input.getNumberOfRooms(),1.0/2.0) + Math.pow(input.getSize(),1.0/2.0))  , 2.0)) / 9.0;
		//return Math.pow(input.getNumberOfBathrooms() + input.getNumberOfRooms() + input.getSize(),2.0)/ 9.0;
		//return (input.getNumberOfBathrooms() + input.getNumberOfRooms() + input.getSize())/ 3.0;
		
	}

}
