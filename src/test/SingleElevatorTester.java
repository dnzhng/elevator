package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import building.Building;




public class SingleElevatorTester implements Runnable{

	public class SuccessCount{
		int successCount = 0;
		
		public synchronized void increment(){
			successCount +=1;
		}
		
		public synchronized int getCount(){
			return successCount;
		}
		
	}
	
	Collection<Rider> myRiders;
	Building myBuilding;
	Random myRandom;
	SuccessCount mySuccessCount;
	int riderCount;
	
	public SingleElevatorTester(){
		int numFloors = 2;
		int numElevators = 1;
		riderCount = 2;
		int elevatorCapacity = 1;
		
		myRandom = new Random();
		
		myBuilding = new Building(numFloors, numElevators, elevatorCapacity);
		
		myRiders = new ArrayList<Rider>();
		
		mySuccessCount = new SuccessCount();
		
		for(int i =0; i < riderCount; ++i){
			
			int cFloor = 0;
			int fFloor = 0;
			
			
			while(cFloor == fFloor){
				cFloor = myRandom.nextInt(numFloors);
				fFloor = myRandom.nextInt(numFloors);
			}
			Rider r = new Rider(0, 1, myBuilding, i, mySuccessCount);
			myRiders.add(r);
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingleElevatorTester set = new SingleElevatorTester();
		set.run();

	}

	@Override
	public void run() {
		myBuilding.run();
		ArrayList<Thread> riderList = new ArrayList<Thread>();
		for(Rider r: myRiders){
			Thread t = new Thread(r);
			riderList.add(t);
			t.start();
		}
		
		
		while(mySuccessCount.getCount() != riderCount){
			
		}
		System.exit(0);
		
//		while(!areDead(riderList)) {
//			
//		}
//		
//		System.exit(0);

	}
	
	public boolean areDead(ArrayList<Thread> riderList) {
		for (Thread r: riderList) {
			if(r.isAlive()) 
				return false;
		}
		
		return true;
	}

}
