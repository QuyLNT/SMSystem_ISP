/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.methods;

/**
 *
 * @author LENOVO
 */
public class ShippingMethodDTO {
    private int id;
    private String name;

    public ShippingMethodDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ShippingMethodDTO() {
        this.id = 0;
        this.name="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}