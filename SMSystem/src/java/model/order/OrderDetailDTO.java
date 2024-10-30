/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

import model.product.ProductDTO;

/**
 *
 * @author LENOVO
 */
public class OrderDetailDTO {

    private int orderDetailId;
    private ProductDTO product;
    private int orderId;
    private int quantity;
    private float unitPrice;

    public OrderDetailDTO() {
        this.orderDetailId = 0;
        this.product = new ProductDTO();
        this.orderId = 0;
        this.quantity = 0;
        this.unitPrice = 0.f;
    }

    public OrderDetailDTO(int orderDetailId, ProductDTO product, int orderId, int quantity, float unitPrice) {
        this.orderDetailId = orderDetailId;
        this.product = product;
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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
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

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

}
