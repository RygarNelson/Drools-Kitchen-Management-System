package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.bin.Order;

public class OrderStatus implements Observer {
	private ArrayList<Order> orderList;
	private JPanel orderPanel;
	private int lastOrderChanged = -1;
	private String required = "admin";
	
	
	public OrderStatus() {
		this.orderList = new ArrayList<Order>();
		this.orderPanel = new JPanel(new BorderLayout());
		//this.orderPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		
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
			this.lastOrderChanged = map.get(2).getID();
		} else if(map.containsKey(3)) {
			this.orderList.remove(map.get(3));
		}
		this.writeOrders();
	}
	
	private void init() {
		JFrame frame = new JFrame();
		frame.setTitle("Password Required");
		frame.setDefaultCloseOperation(3);
		JPasswordField pass = new JPasswordField();
		pass.setBounds(100,75,80,30);  
		JLabel l1=new JLabel("Password:");  
		l1.setBounds(20,75, 80,30);           
        frame.add(pass);  
        frame.add(l1);  
        frame.setSize(300,300);    
        frame.setLayout(null);    
        frame.setVisible(true);
        JButton login = new JButton("login");
        login.setBounds(100,120, 80,30);
        frame.add(login);
        
		pass.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == 10) {
					char[] passChar = pass.getPassword();
		    		String inserted = new String(passChar);
		    		
		    		LoginAction(frame, inserted);
				}
			}
		});
		
        login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				char[] passChar = pass.getPassword();
	    		String inserted = new String(passChar);
	    		
	    		LoginAction(frame, inserted);
			}
		});
        
	}
	
	private void LoginAction(JFrame frame, String inserted) {
		if(inserted.equals(required)) {
			frame.setTitle("Order Status");
			frame.setSize(800,600);
			frame.setDefaultCloseOperation(3);
			//DynamicFramePosition.setLocationToRight(frame);
		    frame.setVisible(true);
			writeOrders();
			frame.setContentPane(orderPanel);
		}
		else {
			System.out.println(inserted);
			System.out.println(required);
		}
	}
	
	private void writeOrders() {
		this.orderPanel.setVisible(false);
		this.orderPanel.removeAll();
		 Object[][] rowData = {};
		 Object[] columnNames = { "ID", "Type", "Waiter ID", "Priority", "Status", "Drinks", "Starter", "First Course", "Second Course", "Dessert" };
		DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
		JTable table = new JTable(tableModel) {
			@Override public boolean isCellEditable(int arg0, int arg1) {
				return false; 
				}
		};
		table.setFillsViewportHeight(true);
		for(Order order : this.orderList) {			
				Object[] objs = {order.getID(), order.getType(), order.getWaiterID(), order.getPriority(), order.getStatus(),
						order.getDrink().getContent(), order.getStarter().getContent(), order.getFirstCourse().getContent(),
						order.getSecondCourse(), order.getDessert().getContent()};
				tableModel.addRow(objs);

				table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
				    @Override
				    public Component getTableCellRendererComponent(JTable table,
				            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

				        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				        Integer status = (Integer)table.getModel().getValueAt(row,0);
				        if (lastOrderChanged == status) {
				            setBackground(Color.GREEN);
				        } else {
				            setBackground(table.getBackground());
				            setForeground(table.getForeground());
				        }       
				        return this;
				    }   
				});
		}
		
		
		this.orderPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		//this.orderPanel.add(table);
		this.orderPanel.revalidate();
		this.orderPanel.setVisible(true);
	}

}
