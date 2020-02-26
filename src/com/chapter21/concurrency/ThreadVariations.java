package com.chapter21.concurrency;
/**
 * Creating threads with inner classes.
 * @author xqk
 * @date 2017年8月11日 上午9:42:13 
 * @version V1.0
 */

import static util.Print.*;

import java.util.concurrent.TimeUnit;

class InnerThread1 {
	private int countDown = 5;
	private Inner inner;
	
	private class Inner extends Thread {
		Inner(String name) {
			super(name);
			start();
		}
		
		@Override
		public void run() {
			try {
				while(true) {
					print(this);
					if(--countDown == 0) return;
					sleep(10);
				}
			} catch (Exception e) {
				print("Interrupted");
			}
		}
		
		@Override
		public String toString() {
			return getName() + ": " + countDown;
		}
	}
	public InnerThread1(String name) {
		inner = new Inner(name);
	}
	
}

class InnerThread2 {
	private int countDown = 5;
	private Thread t;
	
	public InnerThread2(String name) {
		t = new Thread(name) {
			public void run() {
				try {
					while(true) {
						print(this);
						if(--countDown == 0) return;
						sleep(10);
					}
				} catch (Exception e) {
					print("Sleep() interrupted");
				}
			}
		};
		t.start();
	}
}

class InnerRunnable1 {
	private int countDown = 5;
	private Inner inner;
	public InnerRunnable1(String name) {
		inner = new Inner(name);
	}
	
	private class Inner implements Runnable {
		Thread t;
		Inner(String name) {
			t = new Thread(name);
			t.start();
		}
		
		@Override
		public void run() {
			try {
				while(true) {
					print(this);
					if(--countDown == 0)	
						return;
					TimeUnit.MILLISECONDS.sleep(10);
				}
			} catch (Exception e) {
				print("sleep interrupted");
			}
		}
		
		@Override
		public String toString() {
			return t.getName() + ": " + countDown;
		}
	}
}

class InnerRunnable2 {
	private int countDown = 5;
	private Thread t;
	public InnerRunnable2(String name) {
		t = new Thread(new Runnable(){
			
			@Override
			public void run() {
				try {
					while(true) {
						print(this);
						if(--countDown == 0) 	return;
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (InterruptedException e) {
					print("sleep interrupted");
				}
			}
			
			@Override
			public String toString() {
				return Thread.currentThread().getName() + 
						": " + countDown;
			}
			
		}, name);
		t.start();
	}
}

// A separate method to run some code as a task
class ThreadMethod {
	private int countDown = 5;
	private Thread t;
	private String name;
	public ThreadMethod(String name) {
		this.name = name;
	}
	public void runTask() {
		if(t == null) {
			t = new Thread(name) {
				public void run() {
					try {
						while(true) {
							print(this);
							if(--countDown == 0) 	return;
							TimeUnit.MILLISECONDS.sleep(10);
						}
					} catch (InterruptedException e) {
						print("sleep interrupted");
					}
				}
				@Override
				public String toString() {
					return getName() + ": " + countDown;
				}
			};
			t.start();
		}
	}
}

public class ThreadVariations {
	public static void main(String[] args) {
		new InnerThread1("InnerThread1");
		new InnerThread2("InnerThread2");
		new InnerRunnable1("InnerRunnable1");
		new InnerRunnable2("InnerRunnable2");
		new ThreadMethod("ThreadMethod").runTask();
	}

}
