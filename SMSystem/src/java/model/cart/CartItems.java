/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cart;
import model.product.ProductDTO;

/**
 *
 * @author Asus
 */
public class CartItems {
    private ProductDTO product;
    private int cartItemId;
    private int cartId;
    private int quantity;
    private double size;

    public double getSize() {
        return size;
    }
    
    public void setSize(double size) {
        this.size = size;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItems(ProductDTO product, int cartItemId, int cartId, int productId, int quantity, float size ) {
        this.product = product;
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.quantity = quantity;
        this.size=size;
    }

    public CartItems() {
        this.product = new ProductDTO();
        this.cartItemId = 0;
        this.cartId = 0;
        this.quantity = 0;
        this.size=0.0;
    }
    
}
