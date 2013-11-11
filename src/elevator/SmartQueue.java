package elevator;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import building.elevator_controller.Direction;

/**
 * Smart queue only lets one instance in and keeps the 
 * entries sorted in either ascending (or descending order)
 * depending on the direction.
 * 
 * @author Scott Valentine
 *
 * @param <E>
 */
public class SmartQueue<E extends Comparable<E>> implements Queue<E> {
	
	private Node<E> myHead;
	private Node<E> myTail;
	private int mySize = 0;
		
	
	public class Node<E>{
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
	
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		myHead = null;
		mySize = 0;
	}

	@Override
	public boolean contains(Object arg0) {
		E el = (E) arg0;
		Node<E> current = myHead;
		while(current != null){
			if(current.myValue.equals(el)){
				return true;
			}
			current = current.myNext;
		}
		
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		return myHead == null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		
		E el = (E) arg0;
		
		Node<E> current = myHead;
		Node<E> prev = null;
		
		while(current != null){
			if(el == current.myValue){
				removeNode(prev, current);
				current = prev.myNext;
				mySize--;
			}
			else{
				prev = current;
				current = current.myNext;
			}
		}
		return false;
	}
	
	private void removeNode(Node<E> prev, Node<E> toBeRemoved){
		
		if(prev == null){
			myHead = null;
			mySize --;
			return;
		}
		if(toBeRemoved == null){
			return;
		}
		prev.myNext = toBeRemoved.myNext;
		mySize --;
	}
	

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

	@Override
	public int size() {
		return mySize;
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
		
		if(!this.contains((E) arg0)){
			
			if(this.size() == 0){
				myHead = new Node<E>();
				myHead.myValue = arg0;
				myTail = myHead;
			}
			if(this.size() == 1){
				myTail.myNext = new Node<E>();
				myTail.myNext.myValue = arg0;
				myTail = myTail.myNext;
			}
			else{
				Direction currentDir = this.getDirection();
				
				if(currentDir == Direction.UP){
					addAscending(arg0);
				}
				if(currentDir == Direction.DOWN){
					addDescending(arg0);
				}
			}
			mySize ++;
			return true;
		}
		else{
			return false;
		}
	}


	private void addAscending(E arg0) {
		Node<E> current = myHead;
		
		while(current != null && current.myNext !=null){
			if(current.myValue.compareTo(arg0) < 0 && current.myNext.myValue.compareTo(arg0) > 0){
				
				Node<E> newNode = new Node<E>();
				newNode.myNext = current.myNext;
				newNode.myValue = arg0;
				current.myNext = newNode;
				
				return;
			}
			current = current.myNext;
		}
		myTail.myNext= new Node<E>();
		myTail = myTail.myNext;
		myTail.myValue = arg0;
	}

	private void addDescending(E arg0) {
		Node<E> current = myHead;
		
		while(current != null && current.myNext !=null){
			if(current.myValue.compareTo(arg0) > 0 && current.myNext.myValue.compareTo(arg0) < 0){
				
				Node<E> newNode = new Node<E>();
				newNode.myNext = current.myNext;
				newNode.myValue = arg0;
				current.myNext = newNode;
				
				// add
				return;
			}
			current = current.myNext;
		}
		myTail.myNext= new Node<E>();
		myTail = myTail.myNext;
		myTail.myValue = arg0;
		
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
			mySize--;
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
			mySize --;
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
