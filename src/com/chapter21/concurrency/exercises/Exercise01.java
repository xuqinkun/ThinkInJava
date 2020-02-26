package com.chapter21.concurrency.exercises;

class TestRunnable implements Runnable {
	private static int count = 0;
	private final int id = count++;
	public TestRunnable() {
		System.out.println("***** " + id + " Constructor ******");
	}
	
	@Override
	public void run() {
		System.out.println("******" + id +" Run *******");
		Thread.yield();
		System.out.println("******" + id +" Run *******");
		Thread.yield();
		System.out.println("******" + id +" Run *******");
		Thread.yield();
		System.out.println("Printer ended, ID = " + id);
	}
	
}

public class Exercise01 {
	public static void main(String[] args) {
		for(int i = 0; i < 100; i++)
			new Thread(new TestRunnable()).start();
	}
}
