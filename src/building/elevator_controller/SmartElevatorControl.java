package building.elevator_controller;

import java.util.ArrayList;
import java.util.List;

import elevator.SmartElevator;
import interfaces.AbstractElevator;

public class SmartElevatorControl extends ElevatorControl{

	public SmartElevatorControl(int numFloors, int numElevators, int elevatorCap) {
		super(numFloors, numElevators, elevatorCap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractElevator getNextElevator(int requestedFloor,
			Direction direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initializeElevators(int numFloors, int numElevators,
			int elevatorCap) {
		// TODO Auto-generated method stub
		
	}



}
