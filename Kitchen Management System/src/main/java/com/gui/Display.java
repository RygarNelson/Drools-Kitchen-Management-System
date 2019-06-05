package com.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bin.FirstCourse;
import com.bin.Order;
import com.bin.OrderStatus;

public class Display extends JFrame implements Observer{
	private JPanel displayPanel;
	private ArrayList<Order> orderList;
	private JPanel kitchenPanel;
	private JFrame displayFrame;
	
	public Display() {
		this.orderList = new ArrayList<Order>();
		this.displayFrame = new JFrame();
		this.kitchenPanel = new JPanel(new GridLayout(0,1));
		this.init();
	}
	
	private void init() {
		displayFrame.setTitle("Display System");
		displayFrame.setSize(400,500);
		displayFrame.setDefaultCloseOperation(3);
		DynamicFramePosition.setLocationToBottom(displayFrame);
		displayFrame.setVisible(true);
	    //this.createPanel();
		this.setFields();
	    this.displayFrame.setContentPane(kitchenPanel);
	    
		//displayFrame.setContentPane(this.displayPanel);
	}
	
	public void createPanel() {
		this.displayPanel = new JPanel(new GridLayout(0,1));
		JButton goToKitchen = new JButton("KITCHEN");
		JButton goToBar = new JButton("BAR");
		JButton goToGrill = new JButton("GRILL");
		displayPanel.add(goToBar);
		displayPanel.add(goToKitchen);
		displayPanel.add(goToGrill);
		//this.displayFrame.add(displayPanel);
	}
	
	
	@Override
	public void update(Observable o, Object arg1) {
		HashMap<Integer, Order>map = (HashMap<Integer, Order>) arg1;
		if(map.containsKey(1)) {
			//Add order
			this.orderList.add(map.get(1));
		} else if(map.containsKey(2)) {
			this.orderList.set(this.orderList.indexOf(map.get(2)), map.get(2));
		} else if(map.containsKey(3)) {
			this.orderList.remove(map.get(3));
		}
		this.setFields();
		System.out.println(this.orderList);
	}
	
	public void setFields() {
		this.displayKitchen();
		this.displayBar();
		this.displayGrill();
		
	}
	
	public void displayKitchen() {
		this.kitchenPanel.setVisible(false);
		this.kitchenPanel.removeAll();
		for(Order order : this.orderList) {
			if(order.getStatus().equals(OrderStatus.FIRST_COURSE)){
				JLabel firstCourse = new JLabel("<html>"+
						"ID: "+order.getID() +"<br/>"+
						"Priority: "+order.getPriority() +"<br/>"+
						"First Course: "+order.getFirstCourse()+"<br/>"+
						"</html>"
				);
				this.kitchenPanel.add(firstCourse);
			} else if(order.getStatus().equals(OrderStatus.STARTER)) {
				JLabel starterCourse = new JLabel("<html>"+
						"ID: "+order.getID() +"<br/>"+
						"Priority: "+order.getPriority() +"<br/>"+
						"Starter: "+order.getStarter()+"<br/>"+
						"</html>"
				);
				this.kitchenPanel.add(starterCourse);
			}
		}
		this.kitchenPanel.revalidate();
		this.kitchenPanel.setVisible(true);

	}
	public void displayBar() {
		JPanel barPanel = new JPanel(new GridLayout(0,1));
		for(Order order : this.orderList) {
			JLabel orderLabel = new JLabel("<html>"+
					"ID: "+order.getID() +"<br/>"+
					"Type: "+order.getType() +"<br/>"+
					"Waiter ID: "+order.getWaiterID() +"<br/>"+
					"Priority: "+order.getPriority() +"<br/>"+
					"Status: "+order.getStatus() +"<br/>"+
					"Drinks: "+order.getDrink()+"<br/>"+
					"</html>"
			);
			barPanel.add(orderLabel);
		}
		barPanel.revalidate();
	}
	public void displayGrill() {
		JPanel grillPanel = new JPanel(new GridLayout(0,1));
		for(Order order : this.orderList) {
			JLabel orderLabel = new JLabel("<html>"+
					"ID: "+order.getID() +"<br/>"+
					"Type: "+order.getType() +"<br/>"+
					"Waiter ID: "+order.getWaiterID() +"<br/>"+
					"Priority: "+order.getPriority() +"<br/>"+
					"Status: "+order.getStatus() +"<br/>"+
					"Second Course: "+order.getSecondCourse()+"<br/>"+
					"</html>"
			);
			//this.orderPanel.add(orderLabel);
		}
		//this.orderPanel.revalidate();
	}
	}
	


