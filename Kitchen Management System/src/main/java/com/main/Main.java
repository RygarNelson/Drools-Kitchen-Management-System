package com.main;

import java.awt.*;

import javax.swing.*;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.bin.*;

public class Main {
	
	public static final void main(String[] args) {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	
        	kSession.setGlobal("maxRandomTime", 7000);
        	kSession.setGlobal("highPriorityTime", 2500);
        	kSession.setGlobal("minimumRandomGeneratorTime", 3000);
        	
    		Gui frame = new Gui(kSession);
    	    
    	    kSession.addEventListener(new EventListener(frame));
	     	
    	    //Waiter creation
        	Waiter waiter1 = new Waiter(1, "Bob");
        	Waiter waiter2 = new Waiter(2, "Alice");
        	//Waiter waiter3 = new Waiter(2, "Mark");
        	//Waiter waiter4 = new Waiter(2, "Jules");
        	
        	//Order order1 = new Order(12, OrderType.TAKEAWAY, OrderStatus.ORDERED, null, null, null, null, null);
        	
    		FactHandle w1 = kSession.insert(waiter1);
    		FactHandle w2 = kSession.insert(waiter2);
    		//FactHandle w3 = kSession.insert(waiter3);
    		//FactHandle w4 = kSession.insert(waiter4);
    		
    		//FactHandle o1 = kSession.insert(order1);
    		
            kSession.fireAllRules();

    }
}


