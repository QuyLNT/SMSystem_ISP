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
public class ProductVariantDTO {

    private int variantId;
    private int productId;
    private float size;
    private int stock;

    // Constructor
    public ProductVariantDTO(int variantId, int productId, float size, int stock) {
        this.variantId = variantId;
        this.productId = productId;
        this.size = size;
        this.stock = stock;
    }

    public ProductVariantDTO(int productId, int stock) {
        this.productId = productId;
        this.stock = stock;
    }

    public ProductVariantDTO(int productId, float size, int stock) {
        this.productId = productId;
        this.size = size;
        this.stock = stock;
    }

    // Default constructor
    public ProductVariantDTO() {
        this.variantId = 0;
        this.productId = 0;
        this.size = 0;
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

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
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
