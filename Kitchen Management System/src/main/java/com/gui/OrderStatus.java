package com.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import org.mvel2.sh.command.basic.Exit;

import com.bin.Order;

public class OrderStatus implements Observer {
	
	private ArrayList<Order> orderList;
	private JPanel orderPanel;
	
	public OrderStatus() {
		this.orderList = new ArrayList<Order>();
		this.orderPanel = new JPanel();
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
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(3);
		frame.setLayout(new GridLayout());
		DynamicFramePosition.setLocationToRight(frame);
	    frame.setVisible(true);
		this.writeOrders();
		frame.setContentPane(this.orderPanel);
	}
	
	private void writeOrders() {
		this.orderPanel.removeAll();
		for(Order order : this.orderList) {
			JLabel orderLabel = new JLabel(order.toString());
			this.orderPanel.add(orderLabel);
		}
		this.orderPanel.revalidate();
	}

}
