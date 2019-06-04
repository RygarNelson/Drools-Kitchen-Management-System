package com.bin;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.rule.FactHandle;

import com.main.Gui;

public class EventListener implements RuleRuntimeEventListener{
	
	OrderObservable orderObservable;
	
	public EventListener(Gui frame) {
		this.orderObservable = new OrderObservable();
		this.orderObservable.addObserver(frame.getOrderStatusFrame());
	}
	
	@Override
	public void objectDeleted(ObjectDeletedEvent arg0) {
		/*
		 * Whenever an order has been completed, it will be removed
		 * from the working memory
		 */
		if(arg0.getOldObject().getClass() == Order.class) {
			Order temp = (Order)arg0.getOldObject();
			System.out.println("The order "+temp.getID()+" has been deleted"
					+ " since it has been completed");
			this.orderObservable.deleteOrder(temp);
		}
	}

	@Override
	public void objectInserted(ObjectInsertedEvent arg0) {
		//When a new order is inserted, we check the type of the order
		//Based on that, we will decide the time expected to be completed
		if(arg0.getObject().getClass() == Order.class) {
			//Is an order
			Order temp = (Order)arg0.getObject();
			if(temp.getWaiterID() == -1) {
				FactHandle handler = arg0.getKieRuntime().getFactHandle(temp);
				switch(temp.getType()) {
					case LOCAL:{
						//3
						temp.getTimer().setTimeExpected(3000);
						break;
					}
					case TAKEAWAY:{
						//5
						temp.getTimer().setTimeExpected(5000);
						break;
					}
					case DELIVERY:{
						//10
						temp.getTimer().setTimeExpected(10000);
						break;
					}
				}
				temp.setPriority(OrderPriority.NORMAL);
				this.orderObservable.addOrder(temp);
				temp.getTimer().start();
				arg0.getKieRuntime().update(handler, temp);
			}
		}
	}

	@Override
	public void objectUpdated(ObjectUpdatedEvent arg0) {
		/*
		 * When an order has been updated for the first time, it means
		 * that the timeExpected has been added to it
		 * 
		 * In all other cases, it has changed status
		 */
		
		if(arg0.getObject().getClass() == Order.class) {
			//Is an order
			Order temp = (Order)arg0.getObject();
			
			if(temp.getStatus() == OrderStatus.ORDERED && temp.getWaiterID() == -1) {
				//It has been updated for the first time
				System.out.println("The time expected for the order "+temp.getID()+
						" has been added and is "+temp.getTimer().getTimeExpected()/1000+" seconds");
			} else {
				//Check if a waiter has been assigned or it has changed status
				if(temp.getWaiterID() == -1) {
					//Has changed status
					System.out.println("The order "+temp.getID()+" has changed status"
							+ " and now is "+temp.getStatus());
				}
			}
			this.orderObservable.modifyOrder(temp);
		}
	}
	
}
