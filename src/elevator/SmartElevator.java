package elevator;

import java.util.Queue;


public class SmartElevator extends Elevator{
	
	//private SmartQueue<Integer> upQueue;
	//private SmartQueue<Integer> downQueue;

	// private Object myDoor = new Object();
	public SmartElevator(int numFloors, int elevatorId, int maxOccupancy,
			int initialFloor) {
		super(numFloors, elevatorId, maxOccupancy, initialFloor);
	}


	@Override
	public Queue<Integer> initializeQueue() {
		return new SmartQueue<Integer>();
	}


}
