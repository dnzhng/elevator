package test;

import interfaces.AbstractBuilding;
import interfaces.AbstractElevator;

/**
 * Elevator rider thread. Runs by
 * requesting a floor, then terminates upon 
 * exiting the elevator at the correct floor.
 * 
 * @author sdv4
 *
 */
public class Rider implements Runnable {

	private int myCurrentFloor;
	private int myRequestedFloor;
	private AbstractBuilding myBuilding;
	private AbstractElevator myElevator;
	private int myID;
	
	public Rider(int currentFloor, int requestedFloor, AbstractBuilding building, int id){
		myCurrentFloor = currentFloor;
		myRequestedFloor = requestedFloor;
		myBuilding = building;
		myID = id;
	}
	
	
	@Override
	public void run() {
		
		int direction = myCurrentFloor - myRequestedFloor;
		System.out.println("Rider  " + myID + ": at floor: " + myCurrentFloor + " going to " + myRequestedFloor);
		if(direction == 0){
			return;
		}
		else{
			if(direction > 0){
				
				myElevator = myBuilding.CallUp(myCurrentFloor);
			}
			else{
				myElevator = myBuilding.CallDown(myCurrentFloor);
			}
			rideElevator();
			System.out.println("Rider"+ myID +" satisfied");			

		}
	}

	private void rideElevator() {
		myElevator.Enter();
		//System.out.println("Rider"+ myID +" entered elevator");
		myElevator.RequestFloor(myRequestedFloor);
		//System.out.println("Rider"+ myID +" requested floor" + myRequestedFloor);
		myElevator.Exit();	
		//System.out.println("Rider"+ myID +" exited elevator");
	}


}
