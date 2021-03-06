package com.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import com.bin.OrderType;

public class Display extends JFrame implements Observer{
	private JPanel displayPanel;
	private ArrayList<Order> orderList;
	private JPanel kitchenPanel;
	private JPanel barPanel;
	private JPanel grillPanel;
	private JFrame displayFrame;
	
	public Display() {
		this.orderList = new ArrayList<Order>();
		this.displayFrame = new JFrame();
		this.displayPanel = new JPanel(new GridLayout(0,1));
		this.kitchenPanel = new JPanel(new GridLayout(0,1));
		this.barPanel = new JPanel(new GridLayout(0,1));
		this.grillPanel = new JPanel(new GridLayout(0,1));
		this.init();
	}
	
	private void init() {
		displayFrame.setTitle("Display System");
		displayFrame.setSize(400,500);
		displayFrame.setDefaultCloseOperation(3);
		DynamicFramePosition.setLocationToBottom(displayFrame);
		displayFrame.setVisible(true);
	    this.createPanel();
		this.setFields();
	    this.displayFrame.setContentPane(displayPanel);
	    
		//displayFrame.setContentPane(this.displayPanel);
	}
	
	public void createPanel() {
		JButton goToKitchen = new JButton("KITCHEN");
		JButton goToBar = new JButton("BAR");
		JButton goToGrill = new JButton("GRILL");
		goToKitchen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame();
				jf.setTitle("Kitchen panel");
				jf.setSize(400,500);
				DynamicFramePosition.setLocationToBottom(jf);
				jf.setVisible(true);
				jf.setContentPane(kitchenPanel);
				jf.setVisible(true);
				goToKitchen.setEnabled(false);
				jf.addWindowListener(new WindowAdapter() {
				  public void windowClosing(WindowEvent e) {
					  goToKitchen.setEnabled(true);
				  }
				});
			}
		});
		goToBar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame();
				jf.setTitle("Bar panel");
				jf.setSize(400,500);
				DynamicFramePosition.setLocationToBottom(jf);
				jf.setVisible(true);
				jf.setContentPane(barPanel);
				jf.setVisible(true);
				goToBar.setEnabled(false);
				jf.addWindowListener(new WindowAdapter() {
				  public void windowClosing(WindowEvent e) {
					  goToBar.setEnabled(true);
				  }
				});
				
			}
		});
		goToGrill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame();
				jf.setTitle("Grill panel");
				jf.setSize(400,500);
				DynamicFramePosition.setLocationToBottom(jf);
				jf.setVisible(true);
				jf.setContentPane(grillPanel);
				jf.setVisible(true);
				goToGrill.setEnabled(false);
				jf.addWindowListener(new WindowAdapter() {
				  public void windowClosing(WindowEvent e) {
					  goToGrill.setEnabled(true);
				  }
				});
				
			}
		});
		this.displayPanel.add(goToBar);
		this.displayPanel.add(goToKitchen);
		this.displayPanel.add(goToGrill);
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
			if(order.getType().equals(OrderType.TAKEAWAY) || order.getType().equals(OrderType.DELIVERY)) {
				JLabel firstCourse = new JLabel("<html>"+
						"ID: "+order.getID() +"<br/>"+
						"Type: "+order.getType() +"<br/>"+
						"Priority: "+order.getPriority() +"<br/>"+
						"Starter: "+order.getStarter()+"<br/>"+
						"First Course: "+order.getFirstCourse()+"<br/>"+
						"</html>"
				);
				this.kitchenPanel.add(firstCourse);
			}
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
		this.barPanel.setVisible(false);
		this.barPanel.removeAll();
		for(Order order : this.orderList) {
			if(order.getType().equals(OrderType.TAKEAWAY) || order.getType().equals(OrderType.DELIVERY)) {
				JLabel drink = new JLabel("<html>"+
						"ID: "+order.getID() +"<br/>"+
						"Type: "+order.getType() +"<br/>"+
						"Priority: "+order.getPriority() +"<br/>"+
						"Drink: "+order.getDrink()+"<br/>"+
						"Dessert: "+order.getDessert()+"<br/>"+
						"</html>"
				);
				this.barPanel.add(drink);
			}
			if(order.getStatus().equals(OrderStatus.DRINKS)){
				JLabel drink = new JLabel("<html>"+
						"ID: "+order.getID() +"<br/>"+
						"Priority: "+order.getPriority() +"<br/>"+
						"Drink: "+order.getDrink()+"<br/>"+
						"</html>"
				);
				this.barPanel.add(drink);
			} else if(order.getStatus().equals(OrderStatus.DESSERT)) {
				JLabel dessert = new JLabel("<html>"+
						"ID: "+order.getID() +"<br/>"+
						"Priority: "+order.getPriority() +"<br/>"+
						"Dessert: "+order.getDessert()+"<br/>"+
						"</html>"
				);
				this.barPanel.add(dessert);
			}
		}
		this.barPanel.revalidate();
		this.barPanel.setVisible(true);

	}
	public void displayGrill() {
		this.grillPanel.setVisible(false);
		this.grillPanel.removeAll();
		for(Order order : this.orderList) {
			if(order.getType().equals(OrderType.TAKEAWAY) || order.getType().equals(OrderType.DELIVERY)) {
				JLabel secondCourse = new JLabel("<html>"+
						"ID: "+order.getID() +"<br/>"+
						"Type: "+order.getType() +"<br/>"+
						"Priority: "+order.getPriority() +"<br/>"+
						"Second Course: "+order.getSecondCourse()+"<br/>"+
						"</html>"
				);
				this.grillPanel.add(secondCourse);
			}
			if(order.getStatus().equals(OrderStatus.SECOND_COURSE)){
				JLabel secondCourse = new JLabel("<html>"+
						"ID: "+order.getID() +"<br/>"+
						"Priority: "+order.getPriority() +"<br/>"+
						"Second Course: "+order.getSecondCourse()+"<br/>"+
						"</html>"
				);
				this.grillPanel.add(secondCourse);
			}
		}
		this.grillPanel.revalidate();
		this.grillPanel.setVisible(true);

	}
	}
	


