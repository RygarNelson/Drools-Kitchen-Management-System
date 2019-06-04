package com.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import com.bin.Order;

public class OrderStatus implements Observer {
	
	private ArrayList<Order> orderList;
	private JPanel orderPanel;
	
	public OrderStatus() {
		this.orderList = new ArrayList<Order>();
		this.orderPanel = new JPanel(new GridLayout(0,2));
		this.orderPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		this.init();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		HashMap<Integer, Order>map = (HashMap<Integer, Order>) arg1;
		if(map.containsKey(1)) {
			//Add order
			this.orderList.add(map.get(1));
		} else if(map.containsKey(2)) {
			this.orderList.set(this.orderList.indexOf(map.get(2)), map.get(2));
		} else if(map.containsKey(3)) {
			this.orderList.remove(map.get(3));
		}
		this.writeOrders();
	}
	
	private void init() {
		JFrame frame = new JFrame();
		frame.setTitle("Order Status");
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(3);
		DynamicFramePosition.setLocationToRight(frame);
	    frame.setVisible(true);
		this.writeOrders();
		frame.setContentPane(this.orderPanel);
	}
	
	private void writeOrders() {
		this.orderPanel.removeAll();
		for(Order order : this.orderList) {
			JLabel orderLabel = new JLabel("<html>"+
					"ID: "+order.getID() +"<br/>"+
					"Type: "+order.getType() +"<br/>"+
					"Waiter ID: "+order.getWaiterID() +"<br/>"+
					"Priority: "+order.getPriority() +"<br/>"+
					"Status: "+order.getStatus() +"<br/>"+
					"Drinks: "+order.getDrink()+"<br/>"+
					"Starter: "+order.getStarter()+"<br/>"+
					"First Course: "+order.getFirstCourse()+"<br/>"+
					"Second Course: "+order.getSecondCourse()+"<br/>"+
					"Dessert: "+order.getDessert()+
					"</html>"
			);
			this.orderPanel.add(orderLabel);
		}
		this.orderPanel.revalidate();
	}

}
