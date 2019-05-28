package com.bin;

public class Starter implements OrderPartInterface {
	
	private String content;
	private int orderID;
	
	public Starter(int orderID, String content) {
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

	@Override
	public String toString() {
		return "Starter [content=" + content + ", orderID=" + orderID + "]";
	}
	
}
