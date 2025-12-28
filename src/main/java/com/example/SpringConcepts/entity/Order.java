package com.example.SpringConcepts.entity;

import com.example.SpringConcepts.status.OrderStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "order_id")
    private Integer orderId;
    private double amount;
    @Enumerated(EnumType.STRING)

    private String fileName;
    private String fileType;
    @Lob
    private byte[] imageData;

    private OrderStatus orderStatus = OrderStatus.PENDING;
    public Order(){
        this.amount = 0;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
