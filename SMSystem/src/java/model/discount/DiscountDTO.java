/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.discount;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class DiscountDTO {
    private String discountId;
    private String discountCode;
    private String detail;
    private float discountAmount;
    private Date startDay;
    private Date endDay;
    private int usageLimit;
    private boolean discountStatus;

    public DiscountDTO() {
        discountId = "";
        discountCode = "";
        discountAmount = 0;
        detail="";
        startDay = new Date();
        endDay = new Date();
        usageLimit = 0;
        discountStatus = false;
    }

    public DiscountDTO(String discountId, String discountCode, String detail, float discountAmount, Date startDay, Date endDay, int usageLimit, boolean discountStatus) {
        this.discountId = discountId;
        this.discountCode = discountCode;
        this.detail = detail;
        this.discountAmount = discountAmount;
        this.startDay = startDay;
        this.endDay = endDay;
        this.usageLimit = usageLimit;
        this.discountStatus = discountStatus;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
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

    public boolean isDiscountStatus() {
        return discountStatus;
    }

    
    public void setDiscountStatus(boolean discountStatus) {
        this.discountStatus = discountStatus;
    }
    
    
    
}
    
