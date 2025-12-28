package com.example.SpringConcepts.listener;

import com.example.SpringConcepts.event.WashingMachineOrderEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListenerMail {
    @EventListener
    @Async
    public void sendMail(WashingMachineOrderEvent event) throws InterruptedException {
        System.out.println("sending email:"+event.getOrderId());
        Thread.sleep(3000);
        System.out.println("email sent successfully");
    }
}
