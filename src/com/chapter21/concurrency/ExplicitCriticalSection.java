package com.chapter21.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Using explicit Lock objects to create critical sections.
 * @author xqk
 * @date 2017年8月11日 下午5:09:11 
 * @version V1.0
 */
class ExplicitPairManager1 extends PairManager {
	private Lock lock = new ReentrantLock();
	
	@Override
	public synchronized void increment() {
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		} finally {
			lock.unlock();
		}
	}
}

// Use a critical section:
class ExplicitPairManager2 extends PairManager {
	private Lock lock = new ReentrantLock();
	
	@Override
	public void increment() {
		Pair temp;
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		} finally {
			lock.unlock();
		}
		store(temp);
	}
	
}

public class ExplicitCriticalSection {
	public static void main(String[] args) {
		PairManager 
			pman1 = new ExplicitPairManager1(),
			pman2 = new ExplicitPairManager2();
		CriticalSection.testApproaches(pman1, pman2);
	}
}
