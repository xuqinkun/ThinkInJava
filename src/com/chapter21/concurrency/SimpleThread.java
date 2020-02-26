package com.chapter21.concurrency;
/**
 * Inheriting directly from the Thread class.
 * @author xqk
 * @date 2017年8月11日 上午9:26:26 
 * @version V1.0
 */
public class SimpleThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;
	public SimpleThread() {
		// Store the thread name
		super("Thread" + Integer.toString(++threadCount));
		start();
	}
	
	public String toString() {
		return "#" + getName() + "(" + countDown +"), ";
	}
	@Override
	public void run() {
		while(true) {
			System.out.print(this);
			if(--countDown == 0) 
				return;
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 5; i++)
			new SimpleThread();
	}
}
