package eventbarrier;

import interfaces.AbstractEventBarrier;

public class EventBarrier extends AbstractEventBarrier {

	private boolean isOpen;

	private int myUncompleted;

	public EventBarrier() {
		// starts off closed
		isOpen = false;
		// no one is waiting for gate to open at t=0
		myUncompleted = 0;
	}

	@Override
	public synchronized void arrive() {
		myUncompleted += 1;
		if (isOpen) {
			
			return;
		} else {
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: what happens when we can't wait?
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized void raise() {

		if (myUncompleted == 0 || isOpen) {
			// can't raise if it is already raised
			// don't bother opening if no one is waiting
			return;
		}
		notifyAll();
		isOpen = true;
	}

	@Override
	public synchronized void complete() {
		myUncompleted -= 1;

		if (myUncompleted == 0) {
			isOpen = false;
			notifyAll();
		} else {
			try {
				wait();
			} catch (InterruptedException e) {
				// what happens when we can't wait.
				e.printStackTrace();
			}
		}

	}

	@Override
	public synchronized int waiters() {
		return myUncompleted;
	}

}
