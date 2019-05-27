package com.bin.interfaces;

public interface OrderPartInterface {

	String getContent();

	void setContent(String content);

	/**
	 * Since the orderID cannot be changed, it cannot have a set method
	 * @return The order ID
	 */
	int getOrderID();
	
	String toString();
}