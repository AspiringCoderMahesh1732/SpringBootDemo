package com.example.SpringConcepts.event;

import com.example.SpringConcepts.service.WashingMachineService;

public class WashingMachineOrderEvent {
    private final int orderId;
    private final double amount;
    public WashingMachineOrderEvent(){
        this.orderId = 1;
        this.amount = 100;
    }

    public WashingMachineOrderEvent(int orderId, double amount){
        this.orderId = orderId;
        this.amount = amount;
    }


    public int getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }
}
