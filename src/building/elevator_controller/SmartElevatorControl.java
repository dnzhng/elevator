package building.elevator_controller;

import java.util.ArrayList;
import java.util.List;

import elevator.SmartElevator;
import interfaces.AbstractElevator;

public class SmartElevatorControl implements ElevatorControl, Runnable{

	List<SmartElevator> myElevators = new ArrayList<SmartElevator>();
	
	public SmartElevatorControl(int numFloors, int numElevators, int elevatorCap){
		constructElevators(numFloors, numElevators, elevatorCap);
	}
	
	
	private void constructElevators(int numFloors, int numElevators, int elevatorCap) {
		myElevators = new ArrayList<SmartElevator>();
		for(int i =0; i < numElevators; ++i){
			myElevators.add(new SmartElevator(numFloors, i, elevatorCap,0));
		}
	}


	@Override
	public void run() {
		for(SmartElevator e: myElevators){
			Thread elevator = new Thread(e);
			elevator.start();
		}
	}

	@Override
	public AbstractElevator getNextElevator(int requestedFloor,
			Direction direction) {
		
		return null;
	}

}
