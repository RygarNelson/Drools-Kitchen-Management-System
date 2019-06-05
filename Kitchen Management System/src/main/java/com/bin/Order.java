package com.bin;

import java.util.concurrent.CompletableFuture;

import org.kie.api.runtime.KieSession;

public class Order {
	
	private final int ID;
	private final OrderType type;
	private OrderStatus status;
	private OrderPriority priority;
	private Starter starter;
	private FirstCourse firstCourse;
	private SecondCourse secondCourse;
	private Dessert dessert;
	private Drink drink;
	private Timer timer;
	private int waiterID;
	private KieSession kSession;
	
	public Order(int id, OrderType type, OrderStatus status, Starter starter, FirstCourse firstCourse,
			SecondCourse secondCourse, Dessert dessert, Drink drink, KieSession kSession) {
		this.ID = id;
		this.type = type;
		this.status = status;
		this.starter = starter;
		this.firstCourse = firstCourse;
		this.secondCourse = secondCourse;
		this.dessert = dessert;
		this.drink = drink;
		this.timer = new Timer(id);
		this.waiterID = -1;
		this.kSession = kSession;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public OrderPriority getPriority() {
		return priority;
	}

	public void setPriority(OrderPriority priority) {
		this.priority = priority;
	}

	public Starter getStarter() {
		return starter;
	}

	public void setStarter(Starter starter) {
		this.starter = starter;
	}

	public FirstCourse getFirstCourse() {
		return firstCourse;
	}

	public void setFirstCourse(FirstCourse firstCourse) {
		this.firstCourse = firstCourse;
	}

	public SecondCourse getSecondCourse() {
		return secondCourse;
	}

	public void setSecondCourse(SecondCourse secondCourse) {
		this.secondCourse = secondCourse;
	}

	public Dessert getDessert() {
		return dessert;
	}

	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public int getID() {
		return ID;
	}

	/**
	 * Since the timer is a static object, I don't need to change it,
	 * so I will not write the setter
	 * @return The timer object associated to the order
	 */
	public OrderType getType() {
		return type;
	}
	
	public Timer getTimer() {
		return timer;
	}

	public int getWaiterID() {
		return waiterID;
	}

	public void setWaiterID(int waiterID) {
		this.waiterID = waiterID;
	}
	
	public void runAsyncTaskDeliveryTakeway(int time, Order order) {
		long start = System.currentTimeMillis();
				
		CompletableFuture.runAsync(() -> {
		        //Wait
				while(System.currentTimeMillis() - start < time){
				//I do nothing
				}
				
				order.setStatus(OrderStatus.COMPLETED);
				kSession.update(kSession.getFactHandle(order), order);
		});
	}

	@Override
	public String toString() {
		return "Order [ID=" + ID + ", type=" + type + ", status=" + status + ", priority=" + priority + ", starter="
				+ starter + ", firstCourse=" + firstCourse + ", secondCourse=" + secondCourse + ", dessert=" + dessert
				+ ", drink=" + drink +", waiter="+ waiterID+"]";
	}
	
}
