/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.payment;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.util.converter.LocalDateTimeStringConverter;
import model.order.OrderDTO;

/**
 *
 * @author LENOVO
 */
public class OnlinePaymentDTO {
    private String paymentId;
    private int orderId;
    private String description;
    private String status;
    private float amount;
    private LocalDateTime  createAt;
    private LocalDateTime updateAt;

    public OnlinePaymentDTO() {
        this.paymentId = "";
        this.orderId = 0;
        this.amount = 0;
        this.description="";
        this.status = "Pending";
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public OnlinePaymentDTO(String paymentId, int orderId, String description, String status, float amount, LocalDateTime createAt, LocalDateTime updateAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.description = description;
        this.status = status;
        this.amount = amount;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }



    
}
