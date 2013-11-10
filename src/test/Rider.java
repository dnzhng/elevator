package test;

import test.SingleElevatorTester.SuccessCount;
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
	private SuccessCount successCount;
	
	public Rider(int currentFloor, int requestedFloor, AbstractBuilding building, int id, SuccessCount successCount){
		myCurrentFloor = currentFloor;
		myRequestedFloor = requestedFloor;
		myBuilding = building;
		myID = id;
		this.successCount = successCount;
	}
	
	
	@Override
	public void run() {
		pressButton();
	}

	private void rideElevator() {
		if(!myElevator.Enter()){
			pressButton();
			return;
		}
		myElevator.RequestFloor(myRequestedFloor);
		myElevator.Exit();
		
		synchronized(successCount){
			
			successCount.increment();
			System.out.println("Rider"+ myID +" satisfied. " + successCount.getCount() + " done!");			

		}
		

	}


	private void pressButton() {
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
	}
	}
}
