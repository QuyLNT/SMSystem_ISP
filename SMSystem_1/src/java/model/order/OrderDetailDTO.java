/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

/**
 *
 * @author LENOVO
 */
public class OrderDetailDTO {
    private int orderDetailId;
    private int productId;
    private int orderId;
    private int quantity;
    private double unitPrice;

    public OrderDetailDTO() {
        this.orderDetailId = 0;
        this.productId = 0;
        this.orderId = 0;
        this.quantity = 0;
        this.unitPrice = 0.0;
    }

    public OrderDetailDTO(int orderDetailId, int productId, int orderId, int quantity, double unitPrice) {
        this.orderDetailId = orderDetailId;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
}
