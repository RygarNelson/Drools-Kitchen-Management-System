package com.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

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

public class Gui extends JFrame implements Observer{

private KieSession kc;
private ArrayList<Order> orderList;
private JPanel orderPanel;

	public Gui(KieSession kSession){
		kc = kSession;
		this.orderList = new ArrayList<Order>();
		this.orderPanel = new JPanel();
	    initview();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		HashMap<Integer, Order>map = (HashMap<Integer, Order>) arg;
		if(map.containsKey(1)) {
			//Add order
			System.out.println("Aggiungo ordine");
			this.orderList.add(map.get(1));
			System.out.println("Dimensioni lista: "+this.orderList.size());
		} else if(map.containsKey(2)) {
			System.out.println("Modifico ordine");
			System.out.println(map.get(2).getID());
			this.orderList.set(this.orderList.indexOf(map.get(2)), map.get(2));
		} else if(map.containsKey(3)) {
			System.out.println("Elimino ordine");
			System.out.println(map.get(3).getID());
			this.orderList.remove(map.get(3));
		}
		this.writeOrders();
	}
	
	private void writeOrders() {
		this.orderPanel.removeAll();
		for(Order order : this.orderList) {
			JLabel orderLabel = new JLabel(order.toString());
			this.orderPanel.add(orderLabel);
		}
		this.orderPanel.revalidate();
	}

	private void initview() {
//		JPanel firstpanel = new JPanel();
//		JButton neworder = new JButton("Create new order");
//		JButton checkstatus = new JButton("Check order status");
//		
//		firstpanel.add(neworder);
//		firstpanel.add(checkstatus);
//	    
//	    add(firstpanel);
//	    
//	    neworder.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            	getNewOrderPanel();
//            	setLayout(new FlowLayout());
//            	setVisible(true);
//            }
//        });
//	    
//	    checkstatus.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            	setContentPane(getCheckStatusPanel());
//            	setLayout(new GridLayout(2, 1));
//            	setVisible(true);
//            }
//        });
		getCheckStatusPanel();
		getNewOrderPanel();
	
	}
	
	private void getCheckStatusPanel() {
		JFrame frame = new JFrame();
		frame.setTitle("Order Status");
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());
		this.setLocationToRight(frame);
	    frame.setVisible(true);
		this.writeOrders();
		frame.setContentPane(this.orderPanel);
	}
	
	private void getNewOrderPanel() {
		JFrame frame = new JFrame();
		frame.setTitle("New Order");
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    frame.setLayout(new FlowLayout());
		
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

	    panel.add(drinkL);
	    panel.add(drinkB);
	    
	    JLabel starterL = new JLabel("Starter");
		JComboBox starterB = new JComboBox();
		starterB.addItem("Mix");
		starterB.addItem("Ham");
		starterB.addItem("Grilled cheese");

	    panel.add(starterL);
	    panel.add(starterB);
	    
		JLabel firstCourseL = new JLabel("First Course");
		JComboBox firstCourseB = new JComboBox();
		firstCourseB.addItem("Spaghetti");
		firstCourseB.addItem("Tortellini");
		firstCourseB.addItem("Lasagna");

	    panel.add(firstCourseL);
	    panel.add(firstCourseB);
	    
	    JLabel secondCourseL = new JLabel("Second Course");
		JComboBox secondCourseB = new JComboBox();
		secondCourseB.addItem("Fish 'n Chips");
		secondCourseB.addItem("Chicken");
		secondCourseB.addItem("Pulled pork");

	    panel.add(secondCourseL);
	    panel.add(secondCourseB);
	    
	    JLabel dessertL = new JLabel("Dessert");
		JComboBox dessertB = new JComboBox();
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
					new Drink(id, drinkB.getSelectedItem().toString())
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
	
	/**
	 * Calculate dynamically the max borders of a screen based on the resolution
	 * @param frame The frame to move
	 * @return The max bounds of the screen
	 */
	private static Rectangle getMaxWindowBounds(JFrame frame) {
        GraphicsConfiguration config = frame.getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);
        bounds.x += insets.left;
        bounds.y += insets.top;
        bounds.width -= insets.left + insets.right;
        bounds.height -= insets.top + insets.bottom;
        return bounds;
    }

    private static void setLocationToTop(JFrame frame) {
        frame.setLocation(frame.getX(), getMaxWindowBounds(frame).y);
    }

    private static void setLocationToLeft(JFrame frame) {
        frame.setLocation(getMaxWindowBounds(frame).x, frame.getY());
    }

    private static void setLocationToBottom(JFrame frame) {
        Rectangle bounds = getMaxWindowBounds(frame);
        frame.setLocation(frame.getX(), bounds.y + bounds.height - frame.getHeight());
    }

    private static void setLocationToRight(JFrame frame) {
        Rectangle bounds = getMaxWindowBounds(frame);
        frame.setLocation(bounds.x + bounds.width - frame.getWidth(), frame.getY());
    }

}
