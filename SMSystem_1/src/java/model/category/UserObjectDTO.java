/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.category;

/**
 *
 * @author LENOVO
 */
public class UserObjectDTO {
    private int userObjectId;
    private String userObjectName;
    private String detail;
    private int productCount;

    public UserObjectDTO() {
        userObjectId = 0;
        userObjectName = "";
        detail = "";
        productCount = 0;
    }

    public UserObjectDTO(int userObjectId, String userObjetctName, String detail, int productCount) {
        this.userObjectId = userObjectId;
        this.userObjectName = userObjetctName;
        this.detail = detail;
        this.productCount = productCount;
    }

    public int getUserObjectId() {
        return userObjectId;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public void setUserObjectId(int userObjectId) {
        this.userObjectId = userObjectId;
    }

    public String getUserObjectName() {
        return userObjectName;
    }

    public void setUserObjectName(String userObjectName) {
        this.userObjectName = userObjectName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }    
}