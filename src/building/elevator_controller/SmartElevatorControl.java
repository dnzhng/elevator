package building.elevator_controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import elevator.DoubleSmartQueue;
import elevator.Elevator;
import elevator.SimpleElevator;
import elevator.SmartElevator;
import elevator.SmartQueue;
import interfaces.AbstractElevator;

public class SmartElevatorControl extends ElevatorControl{

	public SmartElevatorControl(int numFloors, int numElevators, int elevatorCap) {
		super(numFloors, numElevators, elevatorCap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized AbstractElevator getNextElevator(int requestedFloor,
			Direction direction) {
		int currentSize = myElevators.size();
		if(currentSize > 0){
			// get the next elevator
			AbstractElevator nextElevator = findShortestPath(requestedFloor, direction);
			
			// tell elevator where to go.
			
			nextElevator.RequestFloor(requestedFloor);
			return nextElevator;
		}
		else{
			return null;
		}
	}
	
	private AbstractElevator findShortestPath(int requestedFloor, Direction direction) {
		AbstractElevator closestElevator = null;
		int pathReference = Integer.MAX_VALUE; //Picked an arbitrary number (wanted to use numFloors*2 because the longest path would be up and down the whole building.) 
		for(Elevator e: myElevators) {

			SmartQueue<Integer> requestedFloors = (SmartQueue<Integer>) e.getRequestedFloors(); //Not sure if this could be any less ugly.

			
			if (requestedFloors.size() < pathReference) {
				closestElevator = e;
				pathReference = requestedFloors.size();
			}
		}
		return closestElevator;
	}

	@Override
	protected void initializeElevators(int numFloors, int numElevators,
			int elevatorCap) {
		
		myElevators = new ArrayList<Elevator>();
		for(int id =0; id < numElevators; ++id){
			myElevators.add(new SmartElevator(numFloors, id, elevatorCap,0));
		}
		
	}



}
