package com.chapter21.concurrency;
/**
 * One thread can reacquire the same lock
 * @author xqk
 * @date 2017年8月11日 下午8:20:46 
 * @version V1.0
 */
public class MultiLock {
	public synchronized void f1(int count) {
		if(count-- > 0) {
			System.out.println("f1() calling f2() with count " + count);
			f2(count);
		}
	}
	
	public synchronized void f2(int count) {
		if(count-- > 0) {
			System.out.println("f2() calling f1() with count " + count);
			f1(count);
		}
	}
	
	public static void main(String[] args) {
		final MultiLock multiLock = new MultiLock();
		new Thread() {
			public void run() {
				multiLock.f1(10);
			}
		}.start();
	}
}
