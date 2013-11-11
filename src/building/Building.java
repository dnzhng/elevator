package building;

import building.elevator_controller.Direction;
import building.elevator_controller.ElevatorControl;
import building.elevator_controller.RandomElevatorControl;
import building.elevator_controller.SmartElevatorControl;

import interfaces.AbstractBuilding;
import interfaces.AbstractElevator;

/**
 * Simple implementation of the building in which all of the elevators exist. 
 * 
 * @author sdv4
 *
 */
public class Building extends AbstractBuilding implements Runnable{

	public static final int ELEVATOR_CAPACTITY = 10;
	
	// maybe collection of these - might need one for each floor

	
	ElevatorControl myElevatorControl;
	
	public Building(int numFloors, int numElevators, int elevatorCapacity) {
		super(numFloors, numElevators);
		//myElevatorControl = new RandomElevatorControl(numFloors, numElevators, elevatorCapacity);
		myElevatorControl = new SmartElevatorControl(numFloors, numElevators, elevatorCapacity);
	}

	@Override
	public AbstractElevator CallUp(int fromFloor) {
		return handleElevator(fromFloor, Direction.UP);
		
	}

	@Override
	public AbstractElevator CallDown(int fromFloor) {
		return handleElevator(fromFloor, Direction.DOWN);
	}
	
	private AbstractElevator handleElevator(int fromFloor, Direction direction){
		AbstractElevator firstResponder =  myElevatorControl.getNextElevator(fromFloor, direction);
		return firstResponder;
	}

	@Override
	public void run() {
		myElevatorControl.run();
	}
	
}
