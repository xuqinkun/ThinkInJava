package com.chapter21.concurrency;

import java.util.concurrent.TimeUnit;

public class ADaemon implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("Starting ADaemon");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println(e);
		} finally {
			System.out.println("This should always run?");
		}
	}

	public static void main(String[] args) {
		Thread t = new Thread(new ADaemon());
		//t.setDaemon(true);
		t.start();
	}
}
