package com.chapter21.concurrency;


class Timer implements Runnable {
	int b = 100;
	public synchronized void m1() throws InterruptedException {
		b = 1000;
		Thread.sleep(500);
		System.out.println("m1: b = " + b);
	}
	
	public synchronized void m2() throws InterruptedException {
		Thread.sleep(200);
		b = 2000;
		System.out.println("m2: b = " + b);
	}
	@Override
	public void run() {
		try {
			m1();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class TestSync {
	
	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		Thread t = new Thread(timer);
		t.start();
		
		timer.m2();
		
		System.out.println("main: b = " + timer.b);
	}
}
