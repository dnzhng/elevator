package building.elevator_controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import elevator.SimpleElevator;

import interfaces.AbstractElevator;

public class RandomElevatorControl implements ElevatorControl, Runnable{

	List<SimpleElevator> myElevators;
	Random myRandom;
	
	public RandomElevatorControl(int numFloors, int numElevators, int elevatorCap){
		constructElevators(numFloors, numElevators, elevatorCap);
		myRandom = new Random();
	}
	
	
	private void constructElevators(int numFloors, int numElevators, int elevatorCap) {
		myElevators = new ArrayList<SimpleElevator>();
		for(int i =0; i < numElevators; ++i){
			myElevators.add(new SimpleElevator(numFloors, i, elevatorCap,0));
		}
	}


	@Override
	public AbstractElevator getNextElevator(int requestedFloor,
			Direction direction) {
		
		int currentSize = myElevators.size();
		if(currentSize > 0){
			// get the next elevator
			AbstractElevator nextElevator = myElevators.get(myRandom.nextInt(myElevators.size()));
			
			// tell elevator where to go.
			
			nextElevator.RequestFloor(requestedFloor);
			return nextElevator;
		}
		else{
			return null;
		}
	}


	@Override
	public void run() {
		for(SimpleElevator e: myElevators){
			Thread elevator = new Thread(e);
			elevator.start();
		}
		
	}

}
