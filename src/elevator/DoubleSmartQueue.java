package elevator;

import java.util.Collection;
import java.util.Iterator;
//import java.util.NoSuchElementException;
import java.util.Queue;

//import building.elevator_controller.Direction;


public class DoubleSmartQueue<E extends Comparable<E>> implements Queue<E> {

	
	private SmartQueue<E> myUpQueue;
	private SmartQueue<E> myDownQueue;


	/**
	 * Is the queue in ascending or descending order?
	 * @return
	 */
	
	public SmartQueue<E> getUpQueue() {
		return myUpQueue;
	}
	
	public SmartQueue<E> getDownQueue() {
		return myDownQueue;
	}

	@Override
	public void clear() {
		myUpQueue.clear();
		myDownQueue.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		
		return myUpQueue.contains(arg0) || myDownQueue.contains(arg0);

	}

	@Override
	public boolean isEmpty() {
		return myUpQueue.isEmpty() || myDownQueue.isEmpty();
	}

	@Override
	public boolean remove(Object arg0) {
		
		return myUpQueue.remove(arg0) || myDownQueue.remove(arg0);

	}

	public int getUpSize() {
		return myUpQueue.size();
	}
	
	public int getDownSize() {
		return myDownQueue.size();
	}

	@Override
	public boolean add(E arg0) {
		if (arg0.compareTo(myUpQueue.peek()) >= 0 ) {
			return myUpQueue.add(arg0);
		}
		else {
			return myDownQueue.add(arg0);
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return myUpQueue.size() + myDownQueue.size();
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
