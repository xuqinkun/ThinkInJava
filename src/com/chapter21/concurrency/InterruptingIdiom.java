package com.chapter21.concurrency;
/**
 * General idiom for interrupting a task
 * @author xqk
 * @date 2017年8月11日 下午8:33:29 
 * @version V1.0
 */
import static util.Print.*;

import java.util.concurrent.TimeUnit;

class NeedsCleanup {
	private final int id;
	
	public NeedsCleanup(int ident) {
		id = ident;
		print("NeedsCleaup " + id);
	}
	
	public void cleanup() {
		print("Cleaning up " + id);
	}
}

class Blocked3 implements Runnable {
	private volatile double d= 0.0;

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// Point1
				NeedsCleanup n1 = new NeedsCleanup(1);
				// Start try-finally immediately after definiton
				// of n1, to guarantee proper cleanup of n1:
				try {
					print("sleeping");
					TimeUnit.SECONDS.sleep(1);
					// Point2
					NeedsCleanup n2 = new NeedsCleanup(2);
					// Guarantee proper cleanup of n2:
					try {
						print("Calculating");
						// A time-consuming, non-blocking operation:
						for(int i = 0; i < 250000; i++) 
							d = d + (Math.PI + Math.E) / d;
						print("Finished time-consuming operation");
					} finally {
						n2.cleanup();
					} // end try
				} finally {
					n1.cleanup();
				} // end try
			} // end while
			print("Exiting via while() test");
		} catch(InterruptedException e) {
			print("Exiting via InterruptedException");
		}
	}
	
}


public class InterruptingIdiom {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Blocked3());
		t.start();
		TimeUnit.MILLISECONDS.sleep(1200);
		t.interrupt();
	}
}
