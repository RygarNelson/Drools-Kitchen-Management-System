package com.bin;

import java.util.ArrayList;

import org.kie.api.runtime.KieSession;

public class OrderAverage {
	
	private static final int averageMax = 4;
	
	private ArrayList<Order> orderList;
	private int orderAverage;
	private KieSession kSession;
	
	public OrderAverage(KieSession kSession) {
		this.kSession = kSession;
		this.orderList = new ArrayList<Order>();
		this.orderAverage = 0;
	}
	
	public void addCompleted(Order order) {
		this.orderList.add(order);
		if(this.orderList.size() >= averageMax) {
			for(Order e : this.orderList) {
				this.orderAverage += e.getTimer().getTime();
			}
			this.orderAverage = this.orderAverage / this.averageMax;
			kSession.insert(this);
		}
	}
	
	public void writeAverage() {
		Printer.printAvg(this.orderAverage);
	}

	public int getOrderAverage() {
		return orderAverage;
	}
	
	public void reset() {
		this.orderAverage = 0;
		this.orderList.clear();
	}
	
	
}
