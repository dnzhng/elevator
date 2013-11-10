package elevator;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import elevator.SmartQueue.Node;

import building.elevator_controller.Direction;


public class DoubleSmartQueue<E extends Comparable<E>> implements Queue<E> {

	private Node<E> myHead;
	private Node<E> myTail;
	
	private SmartQueue<Integer> myUpQueue;
	private SmartQueue<Integer> myDownQueue;

	private int mySize = 0;
		
	private class Node<E>{
		E myValue;
		Node<E> myNext;
	}
	
	

	/**
	 * Is the queue in ascending or descending order?
	 * @return
	 */
	public Direction getDirection(){
		
		if(mySize < 2){
			return null;
		}
		else{
			E val1 = myHead.myValue;
			E val2 = myHead.myNext.myValue;
			
			if(val2.compareTo(val1) > 0){
				return Direction.UP;
			}
			else{
				return Direction.DOWN;
			}
		}
	}
	
	public SmartQueue<Integer> getUpQueue() {
		return myUpQueue;
	}
	
	public SmartQueue<Integer> getDownQueue() {
		return myDownQueue;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return myUpQueue.isEmpty() || myDownQueue.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		
		myUpQueue.remove(arg0);
		myDownQueue.remove(arg0);
	}
	
//	private void removeNode(Node<E> prev, Node<E> toBeRemoved){
//		
//		if (myUpQueue.contains(toBeRemoved)) 
//			myUpQueue.removeNode()
//	}
	

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getUpSize() {
		return myUpQueue.size();
	}
	
	public int getDownSize() {
		return myDownQueue.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E arg0) {
		if (arg0.compareTo((E) myUpQueue.peek())) {
			myUpQueue.add(arg0);
		}
		else {
			myDownQueue.add(arg0);
		}
	}

	
	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean offer(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E peek() {
		
		if(myHead != null){
			return myHead.myValue;
		}
		
		return null;
	}

	@Override
	public E poll() {
		if(myHead != null){
			E val = myHead.myValue;	
			myHead = myHead.myNext;
			return val;
		}
		
		return null;
	}

	@Override
	public E remove() {
		if(myHead == null){
			throw new NoSuchElementException();
		}
		else{
			E val = myHead.myValue;
			myHead = myHead.myNext;
			return val;
		}
	}

	@Override
	public String toString(){
		Node<E> current = myHead;
		StringBuffer sb = new StringBuffer();
		
		while(current != null){
			sb.append(",");
			sb.append(current.myValue.toString());
			current = current.myNext;
		}
		return sb.toString();
	}
	
}
