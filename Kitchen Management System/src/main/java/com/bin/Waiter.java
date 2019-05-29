package com.bin;

public class Waiter {
	private final int waiterID;
	private String name;
	private boolean isavailable;
	
	public int getWaiterID() {
		return waiterID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isIsavailable() {
		return isavailable;
	}
	public void setIsavailable(boolean isavailable) {
		this.isavailable = isavailable;
	}

	public Waiter(int waiterID, String name) {
		this.waiterID = waiterID;
		this.name = name;
		this.isavailable = true;
	}

	@Override
	public String toString() {
		return "Waiter [waiterID=" + waiterID + ", name=" + name + ", isavailable=" + isavailable + "]";
	}
	
}
