package com.chapter21.concurrency;

public class EvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	
	@Override
	public synchronized int next() {
		++currentEvenValue;	// Danger point here
		Thread.yield();
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}
}
