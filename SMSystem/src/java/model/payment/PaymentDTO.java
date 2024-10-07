/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.payment;

/**
 *
 * @author LENOVO
 */
public class PaymentDTO {
    private int paymentMethod;
    private String paymentName;
    private int totalPayment;

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public PaymentDTO(int paymentMethod, String paymentName, int totalPayment) {
        this.paymentMethod = paymentMethod;
        this.paymentName = paymentName;
        this.totalPayment = totalPayment;
    }

    public PaymentDTO() {
        this.paymentMethod=0;
        this.paymentName="";
        this.totalPayment=0;
    }

 
    
}

   