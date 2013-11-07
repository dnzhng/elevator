package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import building.Building;

public class SingleElevatorTester implements Runnable{

	Collection<Rider> myRiders;
	Building myBuilding;
	Random myRandom;
	
	
	public SingleElevatorTester(){
		int numFloors = 3;
		int numElevators = 1;
		int numRiders = 10;
		
		myRandom = new Random();
		
		myBuilding = new Building(numFloors, numElevators);
		
		myRiders = new ArrayList<Rider>();
		for(int i =0; i < numRiders; ++i){
			
			int cFloor = 0;
			int fFloor = 0;
			
			while(cFloor == fFloor){
				cFloor = myRandom.nextInt(numFloors);
				fFloor = myRandom.nextInt(numFloors);
			}
			Rider r = new Rider(cFloor, fFloor, myBuilding, i);
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
		
		while(!areDead(riderList)) {
			
		}
		
		System.exit(0);

	}
	
	public boolean areDead(ArrayList<Thread> riderList) {
		for (Thread r: riderList) {
			if(r.isAlive()) 
				return false;
		}
		
		return true;
	}

}
