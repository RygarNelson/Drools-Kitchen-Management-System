package com.bin;

public class FirstCourse implements OrderPartInterface {
	
	private String content;
	private int orderID;
	
	public FirstCourse(int orderID, String content) {
		this.orderID = orderID;
		this.content = content;
	}
	
	public int getOrderID() {
		return this.orderID;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
