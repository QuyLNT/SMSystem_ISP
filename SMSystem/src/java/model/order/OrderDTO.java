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
    private String paymentMethod;
    private String shippingMethod;
    private Date createdAt;
    private String orderStatus;
    private float totalPrice;
    private  int totalOrder;

    public OrderDTO() {
        this.orderId = 0;
        this.customer = new UserDTO();
        this.street = "";
        this.district = "";
        this.city = "";
        this.discountCode = "";
        this.paymentMethod = "";
        this.shippingMethod = "";
        this.createdAt = new Date();
        this.orderStatus = "Waiting for accept";
        this.totalPrice = 0;
        this.totalOrder =0;
    }

    public OrderDTO(int orderId, int totalOrder) {
        this.orderId = orderId;
        this.totalOrder = totalOrder;
    }

    public OrderDTO(int orderId, UserDTO customer, String street, String district, String city,String discountCode, String paymentMethod, String shippingMethod, Date createdAt, String orderStatus) {
        this.orderId = orderId;
        this.customer = customer;
        this.street = street;
        this.district = district;
        this.city = city;
        this.discountCode = discountCode;
        this.paymentMethod = paymentMethod;
        this.shippingMethod = shippingMethod;
        this.createdAt = createdAt;
        this.orderStatus = orderStatus;
    }
    
    public OrderDTO(int orderId, UserDTO customer, String street, String district, String city,String discountCode, String paymentMethod, String shippingMethod, Date createdAt, String orderStatus,float totalPrice) {
        this.orderId = orderId;
        this.customer = customer;
        this.street = street;
        this.district = district;
        this.city = city;
        this.discountCode = discountCode;
        this.paymentMethod = paymentMethod;
        this.shippingMethod = shippingMethod;
        this.createdAt = createdAt;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;

    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
public String getShippingMethod() {
        return shippingMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
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