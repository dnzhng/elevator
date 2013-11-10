package building.elevator_controller;

import java.util.List;

import elevator.Elevator;
import interfaces.AbstractElevator;

public abstract class ElevatorControl implements Runnable{

	protected List<Elevator> myElevators;
	
	public ElevatorControl(int numFloors, int numElevators, int elevatorCap){
		initializeElevators(numFloors, numElevators, elevatorCap);
	}
	
	public abstract AbstractElevator getNextElevator(int requestedFloor,Direction direction);
	
	protected abstract void initializeElevators(int numFloors, int numElevators, int elevatorCap);
	
	
	@Override
	public void run() {
		for(Elevator e: myElevators){
			Thread elevator = new Thread(e);
			elevator.start();
		}
		
	}
	
}
