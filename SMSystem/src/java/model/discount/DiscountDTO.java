/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.discount;

import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class DiscountDTO {
    private int discountId;
    private String discountCode;
    private String detail;
    private float discountAmount;
    private Date startDay;
    private Date endDay;
    private int usageLimit;
    private int used;
    private String status;

    public DiscountDTO() {
        discountId = 0;
        discountCode = "";
        discountAmount = 0;
        detail="";
        startDay = new Date(System.currentTimeMillis()); 
        endDay = new Date(System.currentTimeMillis());
        usageLimit = 0;
        used=0;
    }

    public DiscountDTO(int discountId, String discountCode, String detail, float discountAmount, Date startDay, Date endDay, int usageLimit, int usage, String status) {
        this.discountId = discountId;
        this.discountCode = discountCode;
        this.detail = detail;
        this.discountAmount = discountAmount;
        this.startDay = startDay;
        this.endDay = endDay;
        this.usageLimit = usageLimit;
        this.used = usage;
        this.status= status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }



    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

     
}
    
