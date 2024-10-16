/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author LENOVO
 */
public class CartDTO {
    private int cartId;
    private int customerId;
    private Date createdAt;
    private List<CartItems> cartItemsList;

    public CartDTO(int cartId, int customerId, Date createdAt, ArrayList<CartItems> cartItemsList) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.cartItemsList = cartItemsList != null ? cartItemsList : new ArrayList<>();
    }

    public CartDTO() {
        this.cartId = 0;
        this.customerId = 0;
        this.createdAt = new Date();
        this.cartItemsList = new ArrayList<>();
    }

    // Getters and Setters
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<CartItems> getCartItemsList() {
        return cartItemsList;
    }

    public void setCartItemsList(ArrayList<CartItems> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }

    // Get a specific CartItem by id
    private CartItems getItemById(int id) {
        for (CartItems item : cartItemsList) {
            if (item.getCartItemId() == id) {
                return item;
            }
        }
        return null; // Return null if item not found
    }

    // Check if product with specific size already exists in the cart
    public boolean existedProduct(int id, double size) {
        for (CartItems cartItem : cartItemsList) {
            if (cartItem.getProduct().getProductId() == id && cartItem.getSize() == size) {
                return true;
            }
        }
        return false;
    }

    // Get quantity by item id
    public int getQuantityById(int id) {
        CartItems item = getItemById(id);
        return (item != null) ? item.getQuantity() : 0;
    }

    // Add a new item to the cart
    public void addItem(CartItems item) {
        cartItemsList.add(item);
    }

    // Add quantity to an existing item in the cart
    public void addItemExist(CartItems newItem) {
        for (CartItems item : cartItemsList) {
            if (item.getProduct().getProductId() == newItem.getProduct().getProductId()
                && item.getSize() == newItem.getSize()) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                break;
            }
        }
    }

    // Update the quantity of an item in the cart
    public void updateQuantity(int id, int newQuantity) {
        CartItems item = getItemById(id);
        if (item != null) {
            item.setQuantity(item.getQuantity() + newQuantity);
        }
    }

    // Remove an item from the cart
    public void removeItem(int id) {
        CartItems item = getItemById(id);
        if (item != null) {
            cartItemsList.remove(item);
        }
    }

    // Calculate the total price of items in the cart
    public double getTotalPrice() {
        double subTotal = 0;
        for (CartItems item : cartItemsList) {
            subTotal += item.getQuantity() * item.getProduct().getPrice();
        }
        return subTotal;
    }

    // Get the size of the cart
    public int getSize() {
        return (cartItemsList != null) ? cartItemsList.size() : 0;
    }
}

