/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class UserDTO {
    private int userId;
    private String fullName;
    private String userName;
    private String password;
    private String phoneNumber;
    private String sex;
    private String email;
    private boolean isActive;
    private String roleId;
    private Date createdAt;

    // Constructors
    public UserDTO() {
        userId =0;
        fullName="";
        userName="";
        password="";
        phoneNumber="";
        sex="";
        email="";
        isActive= false;
        roleId="";
        createdAt=new Date();    
    }

    public UserDTO(int userId, String fullName, String userName, String password, String phoneNumber, String sex, String email, boolean isActive, String role, Date createdAt) {
        this.userId = userId;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.email = email;
        this.isActive = isActive;
        this.roleId = role;
        this.createdAt = createdAt;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userId=" + userId + ", fullName=" + fullName + ", userName=" + userName + ", password=" + password + ", phoneNumber=" + phoneNumber + ", sex=" + sex + ", email=" + email + ", isActive=" + isActive + ", roleId=" + roleId + ", createdAt=" + createdAt + '}';
    }


}
