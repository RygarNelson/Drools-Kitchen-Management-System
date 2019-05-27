package com.bin;

import com.bin.enumerators.OrderStatus;
import com.bin.enumerators.OrderType;
import com.bin.order.parts.Dessert;
import com.bin.order.parts.Drink;
import com.bin.order.parts.FirstCourse;
import com.bin.order.parts.SecondCourse;
import com.bin.order.parts.Starter;

public class Order {
	
	private final int ID;
	private final OrderType type;
	private OrderStatus status;
	private int priority;
	private Starter starter;
	private FirstCourse firstCourse;
	private SecondCourse secondCourse;
	private Dessert dessert;
	private Drink drink;
	private Timer timer;
	
	public Order(int id, OrderType type, OrderStatus status, Starter starter, FirstCourse firstCourse,
			SecondCourse secondCourse, Dessert dessert, Drink drink, int timeExpected) {
		this.ID = id;
		this.type = type;
		this.status = status;
		this.starter = starter;
		this.firstCourse = firstCourse;
		this.secondCourse = secondCourse;
		this.dessert = dessert;
		this.drink = drink;
		this.timer = new Timer(id, timeExpected);
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
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

	@Override
	public String toString() {
		return "Order [ID=" + ID + ", type=" + type + ", status=" + status + ", priority=" + priority + ", starter="
				+ starter + ", firstCourse=" + firstCourse + ", secondCourse=" + secondCourse + ", dessert=" + dessert
				+ ", drink=" + drink +"]";
	}
	
}
