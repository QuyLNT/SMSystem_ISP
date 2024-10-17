/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class OrderDTO {
    private int orderId;
    private int customerId;
    private String street;
    private String district;
    private String city;
    private int discountId;
    private int paymentMethodId;
    private int shippingMethodId;            
    private Date createdAt;
    private String orderStatus;
    
    public OrderDTO() {
        this.orderId = 0;
        this.customerId = 0;
        this.street = "";
        this.district = "";
        this.city = "";
        this.discountId = 0;
        this.paymentMethodId = 0;
        this.shippingMethodId = 0;
        this.createdAt = new Date();
        this.orderStatus = "Waiting for accept";
    }

    public OrderDTO(int orderId, int customerId, String street, String district, String city, int discountId, int paymentMethodId, int shippingMethodId, Date createdAt, String orderStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.street = street;
        this.district = district;
        this.city = city;
        this.discountId = discountId;
        this.paymentMethodId = paymentMethodId;
        this.shippingMethodId = shippingMethodId;
        this.createdAt = createdAt;
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public int getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(int shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
