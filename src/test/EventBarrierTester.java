package test;

import java.util.ArrayList;
import java.util.List;
import eventbarrier.EventBarrier;

public class EventBarrierTester {

	EventBarrier eventBarrier;

	public class ProdThread extends Thread {
		EventBarrier myEB;
		Thread a;
		Thread b;
		Thread c;

		public ProdThread(String str, EventBarrier eb) {
			super(str);
			myEB = eb;
		}

		public void run() {

			List<Thread> threadPool = new ArrayList<Thread>();
			for (int i = 65; i < 68; ++i) {
				threadPool.add(new ConsumerThread("" + (char) (i), myEB));
			}

			List<Thread> threadPool2 = new ArrayList<Thread>();
			for (int i = 68; i < 71; ++i) {
				threadPool2.add(new ConsumerThread("" + (char) (i), myEB));
			}

			for (Thread t : threadPool) {
				t.start();
			}

			while (threadStillLeft(threadPool)) {

				myEB.raise();
			}

			for (Thread t : threadPool2) {
				t.start();
			}

			while (threadStillLeft(threadPool2)) {

				myEB.raise();
			}

		}

		private boolean threadStillLeft(List<Thread> threadPool) {
			for (Thread t : threadPool) {
				if (t.isAlive()) {
					return true;
				}
			}
			return false;
		}

	}

	public class ConsumerThread extends Thread {
		EventBarrier myEB;

		public ConsumerThread(String str, EventBarrier eb) {
			super(str);
			myEB = eb;
		}

		public void run() {
			for (int i = 0; i < 1; ++i) {
				myEB.arrive();
				
				System.out.println(getName() + " did some shit.");
				myEB.complete();
			}
		}

	}

	public EventBarrierTester() {
		eventBarrier = new EventBarrier();
		Thread commander = new ProdThread("command", eventBarrier);
		commander.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventBarrierTester ebt = new EventBarrierTester();
	}

}
