/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

import java.util.Date;
import model.user.UserDTO;

/**
 *
 * @author LENOVO
 */
public class OrderDTO {
    private int orderId;
    private UserDTO customer;
    private String street;
    private String district;
    private String city;
    private String discountCode;
    private String paymentName;
    private String shippingMethodName;            
    private Date createdAt;
    private String orderStatus;

    public OrderDTO() {
         this.orderId = 0;
        this.customer = new UserDTO();
        this.street = "";
        this.district = "";
        this.city = "";
        this.discountCode = "";
        this.paymentName = "";
        this.shippingMethodName = "";
        this.createdAt = new Date();
        this.orderStatus = "";
        
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public UserDTO getCustomer() {
        return customer;
    }

    public void setCustomer(UserDTO customer) {
        this.customer = customer;
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

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getShippingMethodName() {
        return shippingMethodName;
    }

    public void setShippingMethodName(String shippingMethodName) {
        this.shippingMethodName = shippingMethodName;
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

    public OrderDTO(int orderId, UserDTO customer, String street, String district, String city, String discountCode, String paymentName, String shippingMethodName, Date createdAt, String orderStatus) {
        this.orderId = orderId;
        this.customer = customer;
        this.street = street;
        this.district = district;
        this.city = city;
        this.discountCode = discountCode;
        this.paymentName = paymentName;
        this.shippingMethodName = shippingMethodName;
        this.createdAt = createdAt;
        this.orderStatus = orderStatus;
    }

  
    
}