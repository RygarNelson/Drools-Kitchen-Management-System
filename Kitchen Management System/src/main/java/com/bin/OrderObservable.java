package com.bin;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;


public class OrderObservable extends Observable {
	
	public void addOrder(Order o) {
		Map map = new HashMap<Integer, Order>();
		map.put(1, o);
		setChanged();
		notifyObservers(map);
		
	}
	
	public void modifyOrder(Order o) {
		Map map = new HashMap<Integer, Order>();
		map.put(2, o);
		setChanged();
		notifyObservers(map);
	}
	
	
	public void deleteOrder(Order o) {
		Map map = new HashMap<Integer, Order>();
		map.put(3, o);
		setChanged();
		notifyObservers(map);
	}

}
