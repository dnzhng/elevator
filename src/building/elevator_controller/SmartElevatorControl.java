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
	public AbstractElevator getNextElevator(int requestedFloor,
			Direction direction) {
		// TODO Auto-generated method stub
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
	
	public AbstractElevator findShortestPath(int requestedFloor, Direction direction) {
		AbstractElevator closestElevator = null;
		int pathReference = 500; //Picked an arbitrary number (wanted to use numFloors*2 because the longest path would be up and down the whole building.) 
		for(Elevator e: myElevators) {
			//int pathCount = 0;
			SmartQueue<Integer> requestedFloors = (SmartQueue<Integer>) e.getRequestedFloors(); //Not sure if this could be any less ugly.
			//requestedFloors.add(requestedFloor);
			//SmartQueue<Integer> upQueue = requestedFloors.getUpQueue();
			//SmartQueue<Integer> downQueue = requestedFloors.getDownQueue();
			if (requestedFloors.size() < pathReference) {
				closestElevator = e;
			}
		}
		return closestElevator;
	}

	@Override
	protected void initializeElevators(int numFloors, int numElevators,
			int elevatorCap) {
		// TODO Auto-generated method stub
		myElevators = new ArrayList<Elevator>();
		for(int i =0; i < numElevators; ++i){
			myElevators.add(new SmartElevator(numFloors, i, elevatorCap,0));
		}
		
	}



}
