package elevator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import interfaces.AbstractElevator;

/**
 * Simple Elevator implementation that allows for infinite capacity. TODO: keep
 * track of # of requests to each floor! UPDATE: I just did that (see
 * floorRequests) Now we need to make sure concurrency is right: make sure doors
 * always open before rider gets on: consider the case where rider starts on
 * same as elevator
 * 
 * @author sdv4
 * 
 */
public class SimpleElevator extends AbstractElevator implements Runnable {

	private int myCurrentFloor;
	private Queue<Integer> myRequestedFloors;
	private Map<Integer, Integer> floorRequests;
	private int myCurrentRiderCount;

	private boolean doorsOpen;
	private int myID;

	// private Object myDoor = new Object();

	public SimpleElevator(int numFloors, int elevatorId,
			int maxOccupancyThreshold, int initialFloor) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		myCurrentFloor = 0;//initialFloor;
		myRequestedFloors = new LinkedList<Integer>();
		myCurrentRiderCount = 0;
		doorsOpen = false;
		myID = elevatorId;
		floorRequests = new HashMap<Integer, Integer>();

	}

	@Override
	public synchronized void OpenDoors() {

		doorsOpen = true;
		System.out.println("elevator " + myID + " opens on floor "
				+ myCurrentFloor + " for " + floorRequests.get(myCurrentFloor) + " riders");

		notifyAll();

	}

	@Override
	public synchronized void ClosedDoors() {
		
		while (floorRequests.get(myCurrentFloor) > 0) {
			notifyAll();
			try {
				
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		doorsOpen = false;
		System.out.println("elevator " + myID + " closes on floor "
				+ myCurrentFloor + " with " + myCurrentRiderCount
				+ " riders on");

	}

	@Override
	public synchronized void VisitFloor(int floor) {
		
//		while(myRequestedFloors.contains(floor)){
//			myRequestedFloors.remove(floor);
//		}
		
		if(!floorRequests.containsKey(myCurrentFloor)){
			floorRequests.put(myCurrentFloor,0);
		}
		
		while(floorRequests.get(myCurrentFloor) > 0){
			OpenDoors();
			ClosedDoors();	
		}
		myCurrentFloor = floor;
		notifyAll();
		OpenDoors();
		ClosedDoors();
	}

	@Override
	public synchronized boolean Enter() {

		while (!doorsOpen) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		floorRequests
				.put(myCurrentFloor, floorRequests.get(myCurrentFloor) - 1);
		myCurrentRiderCount += 1;
		System.out.println("Rider entered elevator " + myID);
		notifyAll();
		return true;

	}

	@Override
	public synchronized void Exit() {

		while (!doorsOpen) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Rider exited elevator " + myID);
		
		floorRequests
				.put(myCurrentFloor, floorRequests.get(myCurrentFloor) - 1);
		myCurrentRiderCount -= 1;
		notifyAll();

	}

	@Override
	public synchronized void RequestFloor(int floor) {

		System.out
				.println("Elevator " + myID + " was called to floor " + floor);
		//System.out.println(myRequestedFloors);
				
		if(!myRequestedFloors.contains(floor)){
			myRequestedFloors.add(floor);
		}
		
		if (!floorRequests.containsKey(floor)) {
			floorRequests.put(floor, 0);
		}
		floorRequests.put(floor, floorRequests.get(floor) + 1);

		while (myCurrentFloor != floor ) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(myRequestedFloors.size() > 1){
			myRequestedFloors.remove(myCurrentFloor);
		}
		
	}

	@Override
	public void run() {
		System.out.println("Elevator " + myID + " started");
		while (true) {
			synchronized(this) {
			Integer nextFloor = myRequestedFloors.poll();
			if (nextFloor != null ){//&& nextFloor != myCurrentFloor) {
				System.out.println(nextFloor + " " + myRequestedFloors);
				VisitFloor(nextFloor);
				//OpenDoors();
				//ClosedDoors();
			}
			}

		}

	}

}
