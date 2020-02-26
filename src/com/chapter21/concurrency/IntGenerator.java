package com.chapter21.concurrency;

public abstract class IntGenerator {
	private volatile boolean canceled = false;
	public abstract int next();
	// Allow athis to be canceld:
	public void cancel() {
		canceled = true;
	}
	public boolean isCanceled() {
		return canceled;
	}
}
