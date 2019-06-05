package com.bin;

public class Waiter {
	private final int waiterID;
	private String name;
	private boolean isAvailable;
	
	public Waiter(int waiterID, String name) {
		this.waiterID = waiterID;
		this.name = name;
		this.isAvailable = true;
	}
	
	public int getWaiterID() {
		return waiterID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Waiter [waiterID=" + waiterID + ", name=" + name + "]";
	}
	
}
