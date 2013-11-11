package elevator;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import interfaces.AbstractElevator;

public abstract class Elevator extends AbstractElevator implements Runnable {

	private int myCurrentFloor;
	private Queue<Integer> myRequestedFloors;
	private Map<Integer, Integer> floorRequests;
	private int myCurrentRiderCount;

	private int myMaxOccupancy;

	private boolean doorsOpen;
	private int myID;

	// private Object myDoor = new Object();

	public Elevator(int numFloors, int elevatorId, int maxOccupancy,
			int initialFloor) {
		super(numFloors, elevatorId, maxOccupancy);
		myCurrentFloor = initialFloor;
		myRequestedFloors = initializeQueue();
		myCurrentRiderCount = 0;
		doorsOpen = false;
		myID = elevatorId;
		floorRequests = new HashMap<Integer, Integer>();
		myMaxOccupancy = maxOccupancy;

	}
	public abstract Queue<Integer> initializeQueue();
	
	public synchronized int getCurrentFloor(){
		return myCurrentFloor;
	}
	
	public synchronized int getCurrentRiderCount(){
		return myCurrentRiderCount;
	}
	
	public synchronized Queue<Integer> getRequestedFloors(){
		return myRequestedFloors;
	}
	
	
	@Override
	public synchronized void OpenDoors() {

		doorsOpen = true;
		System.out.println("elevator " + myID + " opens on floor "
				+ myCurrentFloor + " for " + floorRequests.get(myCurrentFloor)
				+ " riders");

		notifyAll();

	}

	@Override
	public synchronized void ClosedDoors() {

		while (floorRequests.get(myCurrentFloor) > 0) {
			notifyAll();
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		doorsOpen = false;
		System.out.println("elevator " + myID + " closes on floor "
				+ myCurrentFloor + " with " + myCurrentRiderCount
				+ " riders on");
		notifyAll();

	}

	@Override
	public synchronized void VisitFloor(int floor) {

		if (!floorRequests.containsKey(myCurrentFloor)) {
			floorRequests.put(myCurrentFloor, 0);
		}
		
		while (floorRequests.get(myCurrentFloor) > 0) {
			OpenDoors();
			ClosedDoors();
		}
		
		
		myCurrentFloor = floor;
		
		System.out.println("ELEVATOR " + myID + " NOW AT FLOOR " + myCurrentFloor);
		
		notifyAll();
		
		if(floorRequests.get(myCurrentFloor) != 0){
			OpenDoors();
			ClosedDoors();
		}


	}

	@Override
	public synchronized boolean Enter() {
		int floor = myCurrentFloor;
		while (!doorsOpen) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		floorRequests
				.put(myCurrentFloor, floorRequests.get(myCurrentFloor) - 1);

		//System.out.println("waiting on door + " + floorRequests.get(myCurrentFloor));
		
		while (myCurrentRiderCount == myMaxOccupancy && floorRequests.get(myCurrentFloor) > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (floor != myCurrentFloor || myCurrentRiderCount == myMaxOccupancy) {

			System.out.println("Rider unable to enter elevator " + myID
					+ " because of capacity ");
			notifyAll();
			try {
				// just wait until next event.
				wait();
			} catch (InterruptedException e) {
				//TODO
			}
			return false;
		}

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
		if (!myRequestedFloors.contains(floor)) {
			myRequestedFloors.add(floor);
		}

		if (!floorRequests.containsKey(floor)) {
			floorRequests.put(floor, 0);
		}
		floorRequests.put(floor, floorRequests.get(floor) + 1);

		while (myCurrentFloor != floor) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (myRequestedFloors.size() > 1) {
			myRequestedFloors.remove(myCurrentFloor);
		}

	}

	@Override
	public void run() {
		System.out.println("Elevator " + myID + " started");
		while (true) {
			synchronized (this) {
				Integer nextFloor = myRequestedFloors.poll();
				if (nextFloor != null) {
					System.out.println(nextFloor + " " + myRequestedFloors);
					VisitFloor(nextFloor);
				}
			}

		}

	}

}
