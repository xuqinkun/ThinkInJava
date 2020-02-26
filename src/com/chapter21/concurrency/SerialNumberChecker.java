package com.chapter21.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Operations that may seem safe are not,
 * when threads are present.
 * @author xqk
 * @date 2017��8��11�� ����4:03:16 
 * @version V1.0
 */

class CircularSet {
	private int[] array;
	private int len;
	private int index = 0;
	
	public CircularSet(int size) {
		array = new int[size];
		len = size;
		// Initialize to a value not produced
		// by the SerialNumberGenerator
		for(int i = 0; i < size; i++)
			array[i] = -1;
	}
	public synchronized void add(int i) {
		array[index] = i;
		index = ++index % len;
	}
	public synchronized boolean contains(int val) {
		for(int i = 0; i < len; i++)
			if(array[i] == val) return true;
		return false;
	}
}


public class SerialNumberChecker {
	private static final int SIZE = 10;
	private static CircularSet serials = 
			new CircularSet(1000);
	private static ExecutorService exec = 
			Executors.newCachedThreadPool();
	
	static class SerialChecker implements Runnable {

		@Override
		public void run() {
			while(true) {
				int serial = SerialNumberGenerator.nextSerialNumber();
				if(serials.contains(serial)) {
					System.out.println("Duplicate: " + serial);
					System.exit(0);
				}
				serials.add(serial);
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		for(int i = 0; i < SIZE; i++) {
			exec.execute(new SerialChecker());
		}
		TimeUnit.SECONDS.sleep(4);
		System.out.println("No duplicates detected");
		System.exit(0);
	}
}
