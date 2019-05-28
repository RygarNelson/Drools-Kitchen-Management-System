package com.bin;

import java.util.concurrent.CompletableFuture;

public class Timer{
	
	private final int orderID;
	protected long timeExpected;
	private StopWatch stopwatch;
	
	public Timer(int orderID) {
		this.orderID = orderID;
		this.stopwatch = new StopWatch();
	}
	
	public void start() {
		CompletableFuture.runAsync(() -> this.stopwatch.run());
	}
	
	public void stop() {
		this.stopwatch.stop();
	}
	
	public long getTimeExpected() {
		return timeExpected;
	}
	
	public void setTimeExpected(long timeExpected) {
		this.timeExpected = timeExpected;
	}

	public int getOrderID() {
		return orderID;
	}
	
	public boolean isStopped() {
		return this.stopwatch.isStopped();
	}
	
	public boolean isExpired() {
		return this.stopwatch.isExpired();
	}
	
	private class StopWatch implements Runnable{
		private long start;
		private boolean expired = false;
		private boolean stop = false;
		
		public void run(){
			this.start = System.currentTimeMillis();
			while(!stop && (System.currentTimeMillis() - this.start < timeExpected)) {
				//I do nothing
			}
			if(this.stop) {
				this.stop = true;
			} else {
				this.expired = true;
			}
		}
		
		public void stop() {
			if(this.expired) {
				//I do nothing
			} else {
				this.stop = true;
			}
		}
		
		public boolean isStopped() {
			return this.stop;
		}
		
		public boolean isExpired() {
			return expired;
		}
	}
}
