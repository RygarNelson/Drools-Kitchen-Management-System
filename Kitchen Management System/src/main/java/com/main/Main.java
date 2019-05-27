package com.main;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.bin.*;
import com.bin.enumerators.OrderStatus;
import com.bin.enumerators.OrderType;
import com.bin.order.parts.Dessert;
import com.bin.order.parts.Drink;
import com.bin.order.parts.FirstCourse;
import com.bin.order.parts.SecondCourse;
import com.bin.order.parts.Starter;

public class Main {
	
	public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	
            // go !
        	//Order creation
    		int iDOrder1 = 0;
    		int iDOrder2 = 1;
    		Order order1 = new Order(iDOrder1, OrderType.LOCAL, OrderStatus.ORDERED, 
    				new Starter(iDOrder1, "Mixed starters"), 
    				new FirstCourse(iDOrder1, "Spaghetti"), 
    				null, 
    				new Dessert(iDOrder1, "Apple Pie"),
    				new Drink(iDOrder1, "Water"),
    				1000);
    		Order order2 = new Order(iDOrder2, OrderType.LOCAL, OrderStatus.ORDERED, 
    				new Starter(iDOrder2, "Mixed starters"), 
    				new FirstCourse(iDOrder2, "Spaghetti"), 
    				new SecondCourse(iDOrder2, "Fish 'n Chips"), 
    				new Dessert(iDOrder2, "Apple Pie"),
    				new Drink(iDOrder2, "Water"),
    				2000);
   
    		order1.getTimer().start();
    		order2.getTimer().start();
    		FactHandle o1 = kSession.insert(order1);
    		FactHandle o2 = kSession.insert(order2);
            order1.getTimer().stop();
            kSession.update(o1, order1);
    		Thread.sleep(2100);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
