/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

/**
 *
 * @author Asus
 */
public class ProductVariants {
        private int variantId;
    private int productId;
    private double size;
    private int stock;

    // Constructor
    public ProductVariants(int variantId, int productId, double size, int stock) {
        this.variantId = variantId;
        this.productId = productId;
        this.size = size;
        this.stock = stock;
    }

    // Default constructor
    public ProductVariants() {
        this.variantId = 0;
        this.productId = 0;
        this.size = 0.0;
        this.stock = 0;
    }

    // Getters and Setters
    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
    }
}
