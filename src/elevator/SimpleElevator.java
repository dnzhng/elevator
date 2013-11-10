package elevator;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple Elevator implementation that allows for FINITE capacity. TODO: keep
 * track of # of requests to each floor! UPDATE: I just did that (see
 * floorRequests) Now we need to make sure concurrency is right: make sure doors
 * always open before rider gets on: consider the case where rider starts on
 * same as elevator
 * 
 * @author sdv4
 * 
 */
public class SimpleElevator extends Elevator {


	// private Object myDoor = new Object();

	public SimpleElevator(int numFloors, int elevatorId, int maxOccupancy,
			int initialFloor) {
		super(numFloors, elevatorId, maxOccupancy, initialFloor);
	}

	@Override
	public Queue<Integer> initializeQueue() {
		return new LinkedList<Integer>();
	}


}
