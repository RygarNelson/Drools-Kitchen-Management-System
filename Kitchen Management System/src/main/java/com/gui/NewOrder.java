package com.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import javax.swing.*;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.bin.Dessert;
import com.bin.Drink;
import com.bin.FirstCourse;
import com.bin.Order;
import com.bin.OrderStatus;
import com.bin.OrderType;
import com.bin.SecondCourse;
import com.bin.Starter;

public class NewOrder extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private KieSession kc;
	
	public NewOrder(KieSession kSession) {
		this.kc = kSession;
		this.init();
	}
	
	private void init() {
		JFrame frame = new JFrame();
		frame.setTitle("New Order");
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridLayout(0, 1));
		
		JLabel typeL = new JLabel("Type");
		JComboBox typeB = new JComboBox();
	    for (OrderType s : OrderType.values()) { 
	    	typeB.addItem(s);
        } 
	    panel.add(typeL);
	    panel.add(typeB);
		
		JLabel drinkL = new JLabel("Drink");
		JComboBox drinkB = new JComboBox();
		drinkB.addItem("-");
	    drinkB.addItem("Water");
	    drinkB.addItem("Wine");
	    drinkB.addItem("Coca Cola");
	    drinkB.addItem("Beer");

	    panel.add(drinkL);
	    panel.add(drinkB);
	    
	    JLabel starterL = new JLabel("Starter");
		JComboBox starterB = new JComboBox();
		starterB.addItem("-");
		starterB.addItem("Mix");
		starterB.addItem("Ham");
		starterB.addItem("Grilled cheese");

	    panel.add(starterL);
	    panel.add(starterB);
	    
		JLabel firstCourseL = new JLabel("First Course");
		JComboBox firstCourseB = new JComboBox();
		firstCourseB.addItem("-");
		firstCourseB.addItem("Spaghetti");
		firstCourseB.addItem("Tortellini");
		firstCourseB.addItem("Lasagna");

	    panel.add(firstCourseL);
	    panel.add(firstCourseB);
	    
	    JLabel secondCourseL = new JLabel("Second Course");
		JComboBox secondCourseB = new JComboBox();
		secondCourseB.addItem("-");
		secondCourseB.addItem("Fish 'n Chips");
		secondCourseB.addItem("Chicken");
		secondCourseB.addItem("Pulled pork");

	    panel.add(secondCourseL);
	    panel.add(secondCourseB);
	    
	    JLabel dessertL = new JLabel("Dessert");
		JComboBox dessertB = new JComboBox();
		dessertB.addItem("-");
		dessertB.addItem("Apple pie");
		dessertB.addItem("Carrot pie");
		dessertB.addItem("Ice cream");

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
					new Drink(id, drinkB.getSelectedItem().toString()),
					kc
				);
            	//CompletableFuture.runAsync(() -> JOptionPane.showMessageDialog(null, "Item inserted correctly"));
            	FactHandle w1 = kc.insert(order);
            	CompletableFuture.runAsync(() -> kc.fireAllRules());
            }
        });
	    panel.add(btnSubmit);
	    frame.setContentPane(panel);
	    
	    //We need to call this after all components are loaded into the window, otherwise
	    //it will load an empty window and, in order to show correctly all the components, the user has to resize the window
	    frame.setVisible(true);
	}
}
