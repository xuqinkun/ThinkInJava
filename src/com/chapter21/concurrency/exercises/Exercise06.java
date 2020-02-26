package com.chapter21.concurrency.exercises;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise06 implements Runnable {
	static Random rand = new Random(100);
	private static int count = 0;
	private final int id = count++;
	
	@Override
	public void run() {
		System.out.println(id +" Start");
		long start = System.nanoTime();
		int timeout = Math.abs(rand.nextInt()) % 10 + 1;
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		long duration = System.nanoTime() - start;
		System.out.println(id + " SleepTime: " + duration / 1e9);
		System.out.println(id + " End");
	}
	
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 10; i++) {
			
			exec.execute(new Exercise06());
			
		}
		exec.shutdown();
	}
}
