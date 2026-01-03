package com.example.SpringConcepts.service;

import com.example.SpringConcepts.entity.Order;
import com.example.SpringConcepts.event.WashingMachineOrderEvent;
import com.example.SpringConcepts.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class WashingMachineService {
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private OrderRepo orderRepo;
    public WashingMachineService(){}
    public void test(){
        publisher.publishEvent(new WashingMachineOrderEvent(2,2000));
    }
    public ResponseEntity<Order> createOrder(Order order){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRepo.save(order));
    }

    public ResponseEntity<List<Order>> allOrders(){
        List<Order> listOfOrders = orderRepo.findAll();
        return ResponseEntity.ok(listOfOrders);
    }

    public ResponseEntity<Order> updateOrderValue(Integer id,double value){
        Optional<Order> orderOptional = orderRepo.findById(id);
        if(orderOptional.isEmpty()){
            return null;
        }
        Order order = orderOptional.get();
        order.setAmount(value);
        return ResponseEntity.ok(orderRepo.save(order));
    }

    @Cacheable(value = "singleOrder",key = "#id")
    public ResponseEntity<Order> getMyOrder(Integer id){
        Optional<Order> orderOptional = orderRepo.findById(id);
        if(orderOptional.isEmpty()){
            System.out.println("no order present");
            return null;
        }
        return ResponseEntity.ok(orderOptional.get());
    }

    @CachePut(value = "singleOrder", key="#orderId")
    public ResponseEntity<Order> updateOrderAmount(Integer orderId,double amount){
        Optional<Order> orderOptional = orderRepo.findById(orderId);
        if(orderOptional.isEmpty()) return null;
        orderOptional.get().setAmount(amount);
        return ResponseEntity.ok(orderRepo.save(orderOptional.get()));
    }

    @CacheEvict(value = "singleOrder", key="#orderId")
    public ResponseEntity<String> deleteOrderById(Integer orderId){
        orderRepo.deleteById(orderId);
        return ResponseEntity.ok("deleted order with given id");
    }


    public ResponseEntity<?> addImage(MultipartFile file,Order order) throws IOException {
        order.setFileName(file.getOriginalFilename());
        order.setFileType(file.getContentType());
        order.setImageData(file.getBytes());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRepo.save(order));
    }
}
