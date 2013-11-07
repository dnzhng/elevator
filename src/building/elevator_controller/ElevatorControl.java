package building.elevator_controller;

import interfaces.AbstractElevator;

public interface ElevatorControl extends Runnable{

	public AbstractElevator getNextElevator(int requestedFloor,Direction direction);
	
}
