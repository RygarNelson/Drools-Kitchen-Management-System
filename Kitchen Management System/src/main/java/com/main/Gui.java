package com.main;

import org.kie.api.runtime.KieSession;

import com.gui.*;

public class Gui{
	
	private final NewOrder newOrderFrame;
	private final OrderStatus orderStatusFrame;

	public Gui(KieSession kSession){
		this.newOrderFrame = new NewOrder(kSession);
		this.orderStatusFrame = new OrderStatus();
	}

	public NewOrder getNewOrderFrame() {
		return newOrderFrame;
	}

	public OrderStatus getOrderStatusFrame() {
		return orderStatusFrame;
	}

}
