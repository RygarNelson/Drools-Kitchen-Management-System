package com.bin;

public class Timer extends Thread{
	
	private final int orderID;
	private long start;
	private long timeExpected;
	private boolean expired = false;
	private boolean stop = false;
	
	public Timer(int orderID, long timeExpected) {
		this.orderID = orderID;
		this.timeExpected = timeExpected;
	}
	
	public void run() {
		this.start = System.currentTimeMillis()/1000;
		while(!stop && (this.start + (System.currentTimeMillis()/1000)<this.timeExpected)) {
			//I do nothing
		}
		if(this.stop) {
			//I do nothing
		} else {
			this.expired = true;
		}
    }

	public long getTimeExpected() {
		return timeExpected;
	}

	public void setTimeExpected(long timeExpected) {
		this.timeExpected = timeExpected;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public int getOrderID() {
		return orderID;
	}

	public boolean isExpired() {
		return expired;
	}
	
}
