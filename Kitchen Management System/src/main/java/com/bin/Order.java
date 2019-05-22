package com.bin;

public class Order {
	
	private final int ID;
	private final int type;
	private int status;
	private int priority;
	private Starter starter;
	private FirstCourse firstCourse;
	private SecondCourse secondCourse;
	private Dessert dessert;
	private Drink drink;
	
	public Order(int id, int type, int status, Starter starter, FirstCourse firstCourse,
			SecondCourse secondCourse, Dessert dessert, Drink drink) {
		this.ID = id;
		this.type = type;
		this.status = status;
		this.starter = starter;
		this.firstCourse = firstCourse;
		this.secondCourse = secondCourse;
		this.dessert = dessert;
		this.drink = drink;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getType() {
		return type;
	}
	
}
