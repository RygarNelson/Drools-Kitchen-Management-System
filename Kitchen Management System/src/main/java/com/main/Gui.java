package com.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.bin.Dessert;
import com.bin.Drink;
import com.bin.FirstCourse;
import com.bin.Order;
import com.bin.OrderPriority;
import com.bin.OrderStatus;
import com.bin.OrderType;
import com.bin.SecondCourse;
import com.bin.Starter;

public class Gui extends JFrame{

private JPanel neworderPanel = new JPanel();
private JPanel checkstatusPanel = new JPanel();
private JPanel panel3 = new JPanel();
private KieSession kc;

	public Gui(KieSession kSession){
		kc = kSession;
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLayout(new FlowLayout());
	    initview();
	}

	private void initview() {
		JPanel firstpanel = new JPanel();
		JButton neworder = new JButton("Create new order");
		JButton checkstatus = new JButton("Check order status");
		
		firstpanel.add(neworder);
		firstpanel.add(checkstatus);
	    
	    add(firstpanel);
	    
	    neworder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setContentPane(getNewOrderPanel());
            	setLayout(new FlowLayout());
            	setVisible(true);
            }
        });
	    
	    checkstatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setLayout(new FlowLayout());
            	setVisible(true);
            }
        });
	    
	
	}
	
	private Container getNewOrderPanel() {
		JPanel panel = new JPanel();
		
		JLabel typeL = new JLabel("Type");
		JComboBox typeB = new JComboBox();
	    for (OrderType s : OrderType.values()) { 
	    	typeB.addItem(s);
        } 
	    panel.add(typeL);
	    panel.add(typeB);
		
		JLabel drinkL = new JLabel("Drink");
		JComboBox drinkB = new JComboBox();
	    drinkB.addItem("Water");
	    drinkB.addItem("Wine");
	    drinkB.addItem("Coca Cola");
	    drinkB.addItem("Beer");
//		comboBox.addActionListener(new ActionListener() {
//	        public void actionPerformed(ActionEvent arg0) {
//	        }
//	    });
	    panel.add(drinkL);
	    panel.add(drinkB);
	    
	    JLabel starterL = new JLabel("Starter");
		JComboBox starterB = new JComboBox();
		starterB.addItem("Mix");
		starterB.addItem("Ham");
		starterB.addItem("Grilled cheese");
//		comboBox.addActionListener(new ActionListener() {
//	        public void actionPerformed(ActionEvent arg0) {
//	        }
//	    });
	    panel.add(starterL);
	    panel.add(starterB);
	    
		JLabel firstCourseL = new JLabel("First Course");
		JComboBox firstCourseB = new JComboBox();
		firstCourseB.addItem("Spaghetti");
		firstCourseB.addItem("Tortellini");
		firstCourseB.addItem("Lasagna");
//		comboBox.addActionListener(new ActionListener() {
//	        public void actionPerformed(ActionEvent arg0) {
//	        }
//	    });
	    panel.add(firstCourseL);
	    panel.add(firstCourseB);
	    
	    JLabel secondCourseL = new JLabel("Second Course");
		JComboBox secondCourseB = new JComboBox();
		secondCourseB.addItem("Fish 'n Chips");
		secondCourseB.addItem("Chicken");
		secondCourseB.addItem("Pulled pork");
//		comboBox.addActionListener(new ActionListener() {
//	        public void actionPerformed(ActionEvent arg0) {
//	        }
//	    });
	    panel.add(secondCourseL);
	    panel.add(secondCourseB);
	    
	    JLabel dessertL = new JLabel("Dessert");
		JComboBox dessertB = new JComboBox();
		dessertB.addItem("Apple pie");
		dessertB.addItem("Carrot pie");
		dessertB.addItem("Ice cream");
//		comboBox.addActionListener(new ActionListener() {
//	        public void actionPerformed(ActionEvent arg0) {
//	        }
//	    });
	    panel.add(dessertL);
	    panel.add(dessertB);
		 
	    JButton btnSubmit = new JButton("Submit");
	    btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {   
            	Random rand = new Random(); 
            	int id = rand.nextInt(500); 
            	Order order = new Order(id, (OrderType) typeB.getSelectedItem(), OrderStatus.ORDERED, 
					new Starter(id, starterB.getSelectedItem().toString()), 
					new FirstCourse(id, firstCourseB.getSelectedItem().toString()), 
					new SecondCourse(id, secondCourseB.getSelectedItem().toString()), 
					new Dessert(id, dessertB.getSelectedItem().toString()),
					new Drink(id, drinkB.getSelectedItem().toString())
				);
            	JOptionPane.showMessageDialog(null, "Item inserted correctly");
            	FactHandle w1 = kc.insert(order);
            	kc.fireAllRules();
            }
        });
	    
	    panel.add(btnSubmit);
	    
		return panel;
			
	}
	
	
	


	
}
