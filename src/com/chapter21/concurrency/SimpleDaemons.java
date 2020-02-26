package com.chapter21.concurrency;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {

	@Override
	public void run() {
		try {
			while(true) {
				TimeUnit.MICROSECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		} catch (Exception e) {
			System.out.println("Sleep() interrupted");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		for(int i = 0 ; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);	// Must call before start()
			daemon.start();
		}
		System.out.println("All daemos started");
		TimeUnit.MICROSECONDS.sleep(1000);
	}
	
}
