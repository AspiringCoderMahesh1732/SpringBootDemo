package com.example.SpringConcepts.listener;

import com.example.SpringConcepts.event.WashingMachineOrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListenerSms {
    @EventListener
    @Async
    public void sendSms(WashingMachineOrderEvent e) throws InterruptedException {
        System.out.println("sending sms: "+e.getOrderId());
        Thread.sleep(1000);
        System.out.println("sms sent successfully");
    }
}
