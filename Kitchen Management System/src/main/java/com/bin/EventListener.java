package com.bin;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.rule.FactHandle;

public class EventListener implements RuleRuntimeEventListener{

	@Override
	public void objectDeleted(ObjectDeletedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void objectInserted(ObjectInsertedEvent arg0) {
		//When a new order is inserted, we check the type of the order
		//Based on that, we will decide the time expected to be completed
		if(arg0.getObject().getClass() == Order.class) {
			//Is an order
			Order temp = (Order)arg0.getObject();
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
			temp.getTimer().start();
			arg0.getKieRuntime().update(handler, temp);
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
			
			if(temp.getStatus() == OrderStatus.ORDERED) {
				//It has been updated for the first time
				System.out.println("The time expected for the order "+temp.getID()+
						" has been added and is "+temp.getTimer().getTimeExpected()/1000+" seconds");
			} else {
				//It has changed status
				System.out.println("The order "+temp.getID()+" has changed status"
						+ " and now is "+temp.getStatus());
			}
		}
	}
	
}
