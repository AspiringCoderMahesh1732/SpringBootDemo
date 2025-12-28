package com.example.SpringConcepts.service;

import com.example.SpringConcepts.entity.Order;
import com.example.SpringConcepts.repository.OrderRepo;
import com.example.SpringConcepts.status.OrderStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSchedular {
    private final OrderRepo orderRepo;
    public OrderSchedular(OrderRepo orderRepo){
        this.orderRepo = orderRepo;
    }
//    @Scheduled(cron = "*/5 12-20 * ? * ?")
    public void processPendingOrder(){
        List<Order> pendingOrders = orderRepo.findByOrderStatus(OrderStatus.PENDING);
        pendingOrders.forEach(order -> {
            order.setOrderStatus(OrderStatus.SUCCESS);
            orderRepo.save(order);
        });
        System.out.print("pending orders count:"+pendingOrders.size());
    }
}
