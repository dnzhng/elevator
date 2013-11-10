package test;

import static org.junit.Assert.*;

import java.util.Queue;

import org.junit.Test;

import elevator.SmartQueue;

public class SmartQueueTest {

	
	public Queue<Integer> setupUP(){
		Queue<Integer> ret = new SmartQueue<Integer>();
		
		ret.add(0);
		ret.add(1);
		ret.add(2);
		ret.add(0);
		ret.add(3);
		ret.add(1);
		ret.add(1);
		ret.add(1);
		ret.add(1);
		ret.add(1);

		return ret;
	}
	
	@Test 
	public void testSize(){
		Queue<Integer> qu = setupUP();
		assertEquals(4,qu.size());
	}
	
	@Test
	public void testOrderUp() {
		Queue<Integer> qu = setupUP();
		int a = qu.poll();
		int b = qu.poll();
		int c = qu.poll();
		
		assertEquals(0,a);
		assertEquals(1,b);
		assertEquals(2,c);
		
		
	}
	
	@Test
	public void testOrderDown(){
		Queue<Integer> qu = setupDOWN();
		
		int a = qu.poll();
		int b = qu.poll();
		int c = qu.poll();
		int d = qu.poll();
		
		
		assertEquals(10, a);
		assertEquals(9, b);
		assertEquals(7, c);
		assertEquals(5, d);
		
		qu.add(0);
		qu.add(5);
		qu.add(3);
		qu.add(6);
		qu.add(2);
		
		a = qu.poll();
		b = qu.poll();
		c = qu.poll();
		d = qu.poll();
		int e = qu.poll();
		
		
		
	}

	private Queue<Integer> setupDOWN() {
		Queue<Integer> ret = new SmartQueue<Integer>();
		
		ret.add(10);
		ret.add(9);
		ret.add(5);
		ret.add(7);
		
		return ret;
	}

}
