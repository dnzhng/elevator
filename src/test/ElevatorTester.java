package test;

import interfaces.AbstractBuilding;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

import building.Building;

public class ElevatorTester {

	public class SuccessCount {
		int successCount = 0;

		public synchronized void increment() {
			successCount += 1;
		}

		public synchronized int getCount() {
			return successCount;
		}

	}

	private static final String DEFAULT_INPUT_FILE = "default_input.txt";

	Collection<Rider> myRiders;
	Building myBuilding;
	Random myRandom;
	SuccessCount mySuccessCount;
	int myRiderCount;

	public ElevatorTester() {
		 this(DEFAULT_INPUT_FILE);
	}

	public ElevatorTester(String inputFile) {
		myRiders = new ArrayList<Rider>();
		mySuccessCount = new SuccessCount();

		try {
			createBuildingAndRiders(inputFile);
		} catch (FileNotFoundException e) {
			System.err.println("Unable to locate file.");
			System.exit(-1);

		} catch (IOException e) {
			System.err.println("Problem reading file.");
			System.exit(-1);
		}
	}

	private void createBuildingAndRiders(String inputFile) throws IOException {
		Scanner in = new Scanner(new FileReader(inputFile));
		try {
			String line = in.nextLine();
			initBuilding(line);

			while (in.hasNextLine()) {
				
				line = in.nextLine();
				initRider(line, myBuilding);
				myRiderCount ++;
			}
		} finally {
			in.close();
		}

	}

	private void initRider(String line, AbstractBuilding building) {
		String[] args = line.split(" ");

		
		
		int riderID = Integer.parseInt(args[0]);
		int riderEnterFloor = Integer.parseInt(args[1]);
		int riderExitFloor = Integer.parseInt(args[2]);

		myRiders.add(new Rider(riderEnterFloor, riderExitFloor, building,
				riderID, mySuccessCount));

	}

	private void initBuilding(String line) {
		String[] args = line.split(" ");

		int numFloors = Integer.parseInt(args[0]);
		int numElevators = Integer.parseInt(args[1]);
		
		// this is redundant - we just keep reading the file to get more riders
		// int numRiders = Integer.parseInt(args[2]);
		int elevatorCapacity = Integer.parseInt(args[3]);
		
		myBuilding = new Building(numFloors, numElevators, elevatorCapacity);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ElevatorTester set;
		if(args.length > 0){
			String inputfile = args[0];
			set = new ElevatorTester(inputfile);
		}
		else{
			set = new ElevatorTester();
		}
		set.start();
	}

	
	public void start() {
		
		myBuilding.run();
		ArrayList<Thread> riderList = new ArrayList<Thread>();
		for (Rider r : myRiders) {
			Thread t = new Thread(r);
			riderList.add(t);
			t.start();
		}

		while (mySuccessCount.getCount() != myRiderCount) {

		}
		System.exit(0);



	}
}
