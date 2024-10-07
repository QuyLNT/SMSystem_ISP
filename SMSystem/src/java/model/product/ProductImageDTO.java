/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

/**
 *
 * @author LENOVO
 */
public class ProductImageDTO {
    private int imageId;
    private int productId;
    private String imagePath;
    private boolean isAvatar;

    public ProductImageDTO() {
        this.imageId = 0;
        this.productId = 0;
        this.imagePath = " ";
        this.isAvatar = false;
    }

    public ProductImageDTO(int imageId, int productId, String imagePath, boolean isAvatar) {
        this.imageId = imageId;
        this.productId = productId;
        this.imagePath = imagePath;
        this.isAvatar = isAvatar;
    }

    public int getImageId() {
        return imageId;
    }

    public int getProductId() {
        return productId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean getIsAvatar(){
        return isAvatar;
    }
    
    public boolean isIsAvatar() {
        return isAvatar;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setIsAvatar(boolean isAvatar) {
        this.isAvatar = isAvatar;
    }
    
}
