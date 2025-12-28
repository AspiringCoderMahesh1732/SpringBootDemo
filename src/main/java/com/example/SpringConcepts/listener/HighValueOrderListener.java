package com.example.SpringConcepts.listener;

import com.example.SpringConcepts.event.WashingMachineOrderEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HighValueOrderListener {
    @EventListener(condition = "#e.getAmount() > 1500")
    public void highValueOrder(WashingMachineOrderEvent e){
        System.out.println("this order is of high value");
    }
}
