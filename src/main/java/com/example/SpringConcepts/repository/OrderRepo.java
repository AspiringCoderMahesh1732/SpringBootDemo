package com.example.SpringConcepts.repository;

import com.example.SpringConcepts.entity.Order;
import com.example.SpringConcepts.status.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Integer> {
    public List<Order> findByOrderStatus(OrderStatus orderStatus);
}
