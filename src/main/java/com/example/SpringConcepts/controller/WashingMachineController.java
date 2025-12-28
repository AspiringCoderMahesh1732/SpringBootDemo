package com.example.SpringConcepts.controller;

import com.example.SpringConcepts.component.UserInfo;
import com.example.SpringConcepts.component.WashingMachine;
import com.example.SpringConcepts.entity.Order;
import com.example.SpringConcepts.exception.UnknownCredentials;
import com.example.SpringConcepts.service.CacheInspectionService;
import com.example.SpringConcepts.service.WashingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WashingMachineController {
    private final WashingMachine washingMachine;
    @Autowired
    private UserInfo userInfo;
    @Autowired
    private WashingMachineService washingMachineService;
    @Autowired
    private CacheInspectionService cacheInspectionService;
    public WashingMachineController(@Qualifier("manualWashingMachine") WashingMachine washingMachine){
        this.washingMachine = washingMachine;
    }
    @GetMapping("/get")
    public String getDetails(){
        System.out.println("in get details method");
        return washingMachine.run();
    }
    @GetMapping("/details")
    public UserInfo getUserInfo() throws UnknownCredentials{
        if(userInfo.getUsername().equals("devUser")){
            throw new UnknownCredentials("credentials which are set are unknown");
        }
        return userInfo;
    }
    @GetMapping("/env")
    public String getEnv() {
        return System.getenv("USERNAME");
    }

    @GetMapping("/test")
    public String test(){
        washingMachineService.test();
        return "test completed";
    }

    @GetMapping("/demo-interceptor")
    public String demoInterceptorRequest(@RequestParam Integer id){
        System.out.println("id: "+id);
        return "";
    }

    @PostMapping("/my_order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return washingMachineService.createOrder(order);
    }

    @GetMapping("/my_order")
    public ResponseEntity<List<Order>> allOrders(){
        return washingMachineService.allOrders();
    }


    @GetMapping("/get/my_order")
    public ResponseEntity<Order> getMyOrder(@RequestParam Integer id){
        return washingMachineService.getMyOrder(id);
    }

    @GetMapping("/show_cache")
    public void getCache(){
        cacheInspectionService.printContents();
    }

    @PatchMapping("/update_order")
    public ResponseEntity<Order> updateOrderValue(@RequestBody Order order){
        return washingMachineService.updateOrderValue(order.getOrderId(),order.getAmount());
    }

    @PatchMapping("/update/amount/{amount}")
    public ResponseEntity<Order> updateOrderAmount(@RequestParam Integer orderId,@PathVariable double amount) {
        return washingMachineService.updateOrderAmount(orderId,amount);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOrderById(@RequestParam Integer orderId){
        return washingMachineService.deleteOrderById(orderId);
    }
}
