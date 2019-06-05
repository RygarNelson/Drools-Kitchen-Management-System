package com.main;

import org.kie.api.runtime.KieSession;

import com.gui.*;

public class Gui{
	
	private final NewOrder newOrderFrame;
	private final OrderStatus orderStatusFrame;
	private final Display displayFrame;

	public Gui(KieSession kSession){
		this.newOrderFrame = new NewOrder(kSession);
		this.orderStatusFrame = new OrderStatus();
		this.displayFrame = new Display();
	}

	public NewOrder getNewOrderFrame() {
		return newOrderFrame;
	}

	public OrderStatus getOrderStatusFrame() {
		return orderStatusFrame;
	}
	
	public Display getDisplayFrame() {
		return displayFrame;
	}

}
