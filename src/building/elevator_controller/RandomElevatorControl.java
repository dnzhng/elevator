package building.elevator_controller;

import java.util.ArrayList;
import java.util.Random;
import elevator.Elevator;
import elevator.SimpleElevator;
import interfaces.AbstractElevator;

public class RandomElevatorControl extends ElevatorControl{

	Random myRandom;
	
	public RandomElevatorControl(int numFloors, int numElevators, int elevatorCap){
		super(numFloors, numElevators, elevatorCap);
		myRandom = new Random();
	}
	
	
	@Override
	protected void initializeElevators(int numFloors, int numElevators, int elevatorCap) {
		myElevators = new ArrayList<Elevator>();
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

}
