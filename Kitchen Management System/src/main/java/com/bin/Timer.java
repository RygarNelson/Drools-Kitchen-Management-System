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
	
	public long getTime() {
		return this.stopwatch.getTime();
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
	
	@Override
	public String toString() {
		return "Timer [orderID=" + orderID + ", timeExpected=" + timeExpected + ", time=" + stopwatch.getTime() + "]";
	}



	private class StopWatch implements Runnable{
		private long start;
		private boolean expired = false;
		private boolean stop = false;
		private long time;
		
		public void run(){
			this.start = System.currentTimeMillis();
			while(!stop) {
				if(System.currentTimeMillis() - this.start > timeExpected) {
					this.expired = true;
				}
				if(this.stop) {
					this.stop = true;
				}
				this.time = System.currentTimeMillis() - this.start;
			}
		}
		
		public void stop() {
			this.stop = true;
		}
		
		public boolean isStopped() {
			return this.stop;
		}
		
		public boolean isExpired() {
			return expired;
		}
		
		public long getTime() {
			return this.time;
		}
	}
}
