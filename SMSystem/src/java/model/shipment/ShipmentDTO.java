/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.shipment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dell
 */
public class ShipmentDTO {

    int shipmentId;
    int shipperId;
    int orderId;
    int shippingMethodId;
    private String methodName;
    Date shippedDate;
    String shipmentStatus;
    Date estimatedArrival;

    public ShipmentDTO() {
        this.shipmentId = 0;
        this.shipperId = 0;
        this.orderId = 0;
        this.shippingMethodId = 0;
        this.methodName = "";
        this.shippedDate = new Date();;
        this.shipmentStatus = "Your order is currently pending and will be delivered as soon as possible.";
        this.estimatedArrival = new Date();;
    }

    public String getFormattedShippedDate() {
        return formatDate(shippedDate);
    }

    public String getFormattedEstimatedArrival() {
        return formatDate(estimatedArrival);
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    public ShipmentDTO(int shipmentId, int shipperId, int orderId, int shippingMethodId, String methodName, Date shippedDate, String shipmentStatus, Date estimatedArrival) {
        this.shipmentId = shipmentId;
        this.shipperId = shipperId;
        this.orderId = orderId;
        this.shippingMethodId = shippingMethodId;
        this.methodName = methodName;
        this.shippedDate = shippedDate;
        this.shipmentStatus = shipmentStatus;
        this.estimatedArrival = estimatedArrival;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(int shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public Date getEstimatedArrival() {
        return estimatedArrival;
    }

    public void setEstimatedArrival(Date estimatedArrival) {
        this.estimatedArrival = estimatedArrival;
    }

}
