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
        	
        	kSession.setGlobal("maxRandomTime", 2000);
        	kSession.addEventListener(new EventListener());
        	
    		Gui frame = new Gui(kSession);
    		frame.setSize(500,300);
    	    frame.setVisible(true);
	     	
    	    //Waiter creation
        	Waiter waiter1 = new Waiter(1, "Bob");
        	Waiter waiter2 = new Waiter(2, "Alice");
        	
    		FactHandle w1 = kSession.insert(waiter1);
    		FactHandle w2 = kSession.insert(waiter2);
    		
            kSession.fireAllRules();

    }
}


