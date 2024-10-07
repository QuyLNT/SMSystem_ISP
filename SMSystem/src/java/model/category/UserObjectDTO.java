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

    public UserObjectDTO() {
        userObjectId = 0;
        userObjectName = "";
        detail = "";
    }

    public UserObjectDTO(int userObjectId, String userObjetctName, String detail) {
        this.userObjectId = userObjectId;
        this.userObjectName = userObjetctName;
        this.detail = detail;
    }

    public int getUserObjectId() {
        return userObjectId;
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