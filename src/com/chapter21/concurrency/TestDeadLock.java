package com.chapter21.concurrency;

public class TestDeadLock implements Runnable {
	public int flag = 1;
	static Object o1 = new Object(), o2 = new Object();
	
	@Override
	public void run() {
		System.out.println("flag = " + flag);
		if(flag == 1) {
			synchronized(o1) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(o2) {
					System.out.println(1);
				}
			}
		}
		if(flag == 0) {
			synchronized(o2) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(o1) {
					System.out.println(0);
				}
			}
		}
	}

	public static void main(String[] args) {
		TestDeadLock test1 = new TestDeadLock();
		TestDeadLock test2 = new TestDeadLock();
		test1.flag = 1;
		test2.flag = 0;
		Thread t1 = new Thread(test1);
		Thread t2 = new Thread(test2);
		t1.start();
		t2.start();
	}
}
